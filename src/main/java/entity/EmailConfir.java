package entity;

public class EmailConfir {
    private long id;
    private String email;

    public EmailConfir(long id, String email) {
        this.id = id;
        this.email = email;
    }

    public EmailConfir(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
