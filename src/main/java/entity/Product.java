package entity;


import java.util.Date;

public class Product {
    private long id;
    private String titles;
    private String textData;
    private String description;
    private String sourcelinkTo;
    private byte [] photofile;
    private Date created_at;
    private Date updated_at;
    private int counterOfView;
    private int category_id;
    private int publisher_id;
    private boolean isDeleted;
    public Product() {
    }

    public Product(long id, String titles, String textData, String description, String sourcelinkTo, byte[] photofile, Date created_at, Date updated_at, int counterOfView, int category_id, int publisher_id, boolean isDeleted) {
        this.id = id;
        this.titles = titles;
        this.textData = textData;
        this.description = description;
        this.sourcelinkTo = sourcelinkTo;
        this.photofile = photofile;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.counterOfView = counterOfView;
        this.category_id = category_id;
        this.publisher_id = publisher_id;
        this.isDeleted = isDeleted;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Product(long id, String titles,
                   String description,
                   String sourcelinkTo,
                   byte[] photofile,
                   Date created_at, Date updated_at) {
        this.id = id;
        this.titles = titles;
        this.description = description;
        this.sourcelinkTo = sourcelinkTo;
        this.photofile = photofile;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Product(long id, String titles,
                   String textData,
                   String description,
                   String sourcelinkTo,
                   byte[] photofile,
                   Date created_at,
                   Date updated_at)
    {
        this.id = id;
        this.titles = titles;
        this.textData = textData;
        this.description = description;
        this.sourcelinkTo = sourcelinkTo;
        this.photofile = photofile;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Product(long id, String titles, String textData, String description, String sourcelinkTo, byte[] photofile, Date created_at, Date updated_at, boolean isDeleted) {
        this.id = id;
        this.titles = titles;
        this.textData = textData;
        this.description = description;
        this.sourcelinkTo = sourcelinkTo;
        this.photofile = photofile;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.isDeleted = isDeleted;
    }

    public Product(String titles, String textData,
                   String description, String sourcelinkTo,
                   byte[] photofile, int category_id) {
        this.titles = titles;
        this.textData = textData;
        this.description = description;
        this.sourcelinkTo = sourcelinkTo;
        this.photofile = photofile;
        this.category_id = category_id;
    }

    public int getCounterOfView() {
        return counterOfView;
    }

    public void setCounterOfView(int counterOfView) {
        this.counterOfView = counterOfView;
    }

    public int getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(int publisher_id) {
        this.publisher_id = publisher_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }



    public String getTextData() {
        return textData;
    }

    public void setTextData(String textData) {
        this.textData = textData;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSourcelinkTo() {
        return sourcelinkTo;
    }

    public void setSourcelinkTo(String sourcelinkTo) {
        this.sourcelinkTo = sourcelinkTo;
    }

    public byte[] getPhotofile() {
        return photofile;
    }

    public void setPhotofile(byte[] photofile) {
        this.photofile = photofile;
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
