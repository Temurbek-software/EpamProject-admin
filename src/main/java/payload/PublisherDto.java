package payload;

public class PublisherDto {
    private String username;
    private String password;

    public PublisherDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public PublisherDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PublisherDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
