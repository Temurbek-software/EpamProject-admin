package services;

import entity.Publisher;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static services.PublisherValidation.ValidResult.*;

public interface PublisherValidation extends Function<Publisher, PublisherValidation.ValidResult> {
    PublisherService PUBLISHER_SERVICE = new PublisherService();

    static PublisherValidation isNameValid() {
        return publisher ->
        {
            System.out.println("running name validation");
            Predicate<String> predicate = (s) -> s.equals(publisher.getNameOfCompany());
            List<String> nameList = PUBLISHER_SERVICE.getAlPublishers().stream().map(s -> s.getNameOfCompany()).collect(Collectors.toList());
            boolean status = false;
            for (String nam : nameList) {
                if (predicate.test(nam)) {
                    status = true;
                    break;
                } else {
                    continue;
                }
            }
            return status ? NAME_NOT_VALID : SUCCESS;
        };
    }

    static PublisherValidation isPhoneNumberValid() {
        return publisher ->
        {
            List<String> phoneNumber = PUBLISHER_SERVICE.getAlPublishers().stream().map(s -> s.getPhoneNumber()).collect(Collectors.toList());
            boolean status = false;
            Predicate<String> stringPredicat = (n) -> n.equals(publisher.getPhoneNumber());
            for (String s : phoneNumber) {
                if (stringPredicat.test(s)) {
                    status = true;
                } else {
                    continue;
                }
            }
            return status ? PHONE_NUMBER_NOT_VALID : SUCCESS;
        };


    }

    static PublisherValidation isEmailValid() {
        List<String> email = PUBLISHER_SERVICE.getAlPublishers().stream().map(s -> s.getEmail()).collect(Collectors.toList());
        return publisher -> email.contains(publisher.getEmail()) ? EMAIL_NOT_VALID : SUCCESS;
    }
    static PublisherValidation isUsernameValid() {
        List<String> username = PUBLISHER_SERVICE.getAlPublishers().stream().map(s -> s.getUsername()).collect(Collectors.toList());
        return publisher -> username.contains(publisher.getUsername()) ? USERNAME_NOT_VALID : SUCCESS;
    }

    default PublisherValidation and(PublisherValidation validation) {
        return publisher ->
        {
            ValidResult validResult = this.apply(publisher);
            return validResult.equals(SUCCESS) ? validation.apply(publisher) : validResult;
        };
    }

    enum ValidResult {
        SUCCESS,
        NAME_NOT_VALID,
        USERNAME_NOT_VALID,
        PHONE_NUMBER_NOT_VALID,
        EMAIL_NOT_VALID
    }

}
