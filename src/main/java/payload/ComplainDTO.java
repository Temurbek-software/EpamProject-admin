package payload;

import java.util.Date;

public class ComplainDTO {
    private long id;
    private String message;
    private String username;
    private String publisherName;
    private Date created_at;
    private Date updated_at;

    public ComplainDTO(long id, String message, String username, String publisherName, Date created_at) {
        this.id = id;
        this.message = message;
        this.username = username;
        this.publisherName = publisherName;
        this.created_at = created_at;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
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
}
