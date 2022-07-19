package entity;

import java.util.Date;

public class Users {
    private Long id;
    private String username;
    private String fullName;
    private String password;
    private String phoneNumber;
    private String email;
    private boolean isActive;
    private boolean isDeleted;
    private boolean isBlocked;
    private Date created_at;
    private Date updated_at;

    public Users() {
    }

    public Users(Long id, String username,
                 String fullName,
                 String password, String phoneNumber,
                 String email, boolean isActive,
                 boolean isDeleted, boolean isBlocked,
                 Date created_at, Date updated_at) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.isBlocked = isBlocked;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Users(Long id, String username, String fullName, String password, String phoneNumber, String email, boolean isDeleted, Date created_at, Date updated_at) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.isDeleted = isDeleted;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Users(Long id, String username, String fullName, String password, String phoneNumber, String email) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Users(String username,
                 String fullName,
                 String password,
                 String phoneNumber,
                 String email,
                 Date created_at,
                 Date updated_at) {
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Users(String username, String fullName, String password, String phoneNumber, String email) {
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Users(Long id, String username,
                 String fullName,
                 String password,
                 String phoneNumber,
                 String email,
                 Date created_at,
                 Date updated_at) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Users other = (Users) obj;
        if (id != other.id)
            return false;
        return true;
    }
}

