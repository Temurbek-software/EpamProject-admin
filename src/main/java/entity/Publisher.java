package entity;

import java.util.Date;
import java.util.Objects;

public class Publisher {
    private long id;
    private String username;
    private String nameOfCompany;
    private String address;
    private String phoneNumber;
    private String email;
    private String password;
    private String description;
    private boolean isActive;
    private boolean isBlocked;
    private Date created_At;
    private Date updated_At;

    public Publisher(long id, String username, String nameOfCompany, String address, String phoneNumber,
                     String email, String password, String description, boolean isBlocked) {
        this.id = id;
        this.username = username;
        this.nameOfCompany = nameOfCompany;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.description = description;
        this.isBlocked = isBlocked;
    }

    public Publisher(long id, String username,
                     String nameOfCompany, String address,
                     String phoneNumber, String email,
                     String password,
                     String description,
                     boolean isActive,
                     boolean isBlocked, Date created_At,
                     Date updated_At)
    {
        this.id = id;
        this.username = username;
        this.nameOfCompany = nameOfCompany;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.description = description;
        this.isActive = isActive;
        this.isBlocked = isBlocked;
        this.created_At = created_At;
        this.updated_At = updated_At;
    }

    public Publisher(long id, String username, String nameOfCompany, String address, String phoneNumber, String email, String password, String description, boolean isActive, Date created_At, Date updated_At) {
        this.id = id;
        this.username = username;
        this.nameOfCompany = nameOfCompany;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.description = description;
        this.isActive = isActive;
        this.created_At = created_At;
        this.updated_At = updated_At;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publisher)) return false;
        Publisher publisher = (Publisher) o;
        return username.equals(publisher.username) && nameOfCompany.equals(publisher.nameOfCompany) && address.equals(publisher.address) && phoneNumber.equals(publisher.phoneNumber) && email.equals(publisher.email) && password.equals(publisher.password) && description.equals(publisher.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, nameOfCompany, address, phoneNumber, email, password, description);
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nameOfCompany='" + nameOfCompany + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                ", created_At=" + created_At +
                ", updated_At=" + updated_At +
                '}';
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Publisher(String username, String phoneNumber, String email) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Publisher(String username, String nameOfCompany, String address,
                     String phoneNumber, String email, String password, String description, boolean isActive) {
        this.username = username;
        this.nameOfCompany = nameOfCompany;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.description = description;
        this.isActive = isActive;
    }



    public Publisher(long id, String username,
                     String nameOfCompany, String address,
                     String phoneNumber, String email,
                     String password, String description) {
        this.id = id;
        this.username = username;
        this.nameOfCompany = nameOfCompany;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.description = description;
    }

    public Publisher(long id, String username, String nameOfCompany, String address, String phoneNumber, String email, String password, String description, Date created_At, Date updated_At) {
        this.id = id;
        this.username = username;
        this.nameOfCompany = nameOfCompany;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.description = description;
        this.created_At = created_At;
        this.updated_At = updated_At;
    }

    public Publisher() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    public Date getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(Date updated_At) {
        this.updated_At = updated_At;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNameOfCompany() {
        return nameOfCompany;
    }

    public void setNameOfCompany(String nameOfCompany) {
        this.nameOfCompany = nameOfCompany;
    }
}
