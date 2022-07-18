package entity;

import java.util.Date;

public class Complain {
    private long id;
    private String message;
    private int user_id;
    private int publisher_id;
    private Date created_at;
    private Date updated_at;

    public Complain() {
    }

    public Complain(long id, String message, int user_id, int publisher_id) {
        this.id = id;
        this.message = message;
        this.user_id = user_id;
        this.publisher_id = publisher_id;
    }

    public Complain(String message, int user_id, int publisher_id) {
        this.message = message;
        this.user_id = user_id;
        this.publisher_id = publisher_id;
    }
}
