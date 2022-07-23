package services;

import entity.Publisher;
import entity.Users;
import payload.PublisherDto;
import payload.UserDto;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieService {
    public Publisher getPublisher(HttpServletRequest request) {
        Publisher publisher = new Publisher();
        PublisherDto publisherDto = new PublisherDto();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Username")) {
                    publisherDto.setUsername(cookie.getValue());
                }
                if (cookie.getName().equals("Password")) {
                    publisherDto.setPassword(cookie.getValue());
                }
            }
        }
        PublisherService publisherService = new PublisherService();
        publisher = publisherService.getPublisher(publisherDto);
        return publisher;
    }
    public PublisherDto getAdminName(HttpServletRequest request)
    {
        PublisherDto publisherDto = new PublisherDto();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Username")) {
                    publisherDto.setUsername(cookie.getValue());
                }
                if (cookie.getName().equals("Password")) {
                    publisherDto.setPassword(cookie.getValue());
                }
            }
        }
        return publisherDto;
    }
}
