package entity;

import java.util.Date;
import java.util.UUID;

public class PushlisherChat {
    private UUID id;
    private String msgText;
    private Date postDate;
    private Integer publisher_id;
    private Integer user_id;

    public PushlisherChat() {
    }

    public PushlisherChat(UUID id, String msgText,
                          Date postDate, Integer publisher_id,
                          Integer user_id)
    {
        this.id = id;
        this.msgText = msgText;
        this.postDate = postDate;
        this.publisher_id = publisher_id;
        this.user_id = user_id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Integer getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(Integer publisher_id) {
        this.publisher_id = publisher_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
