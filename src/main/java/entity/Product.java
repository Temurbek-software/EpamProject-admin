package entity;


import java.util.Date;

public class Product {
    private Long id;
    private String titles;
    private String description;
    private String sourcelinkTo;
    private Date createdTime;
    private String photofile;

    public Product() {
    }

    public Product(String titles, String description, String sourcelinkTo, Date createdTime, String photofile) {
        this.titles = titles;
        this.description = description;
        this.sourcelinkTo = sourcelinkTo;
        this.createdTime = createdTime;
        this.photofile = photofile;
    }

    public Product(Long id, String titles,
                   String description,
                   String sourcelinkTo,
                   Date createdTime,
                   String photofile) {
        this.id = id;
        this.titles = titles;
        this.description = description;
        this.sourcelinkTo = sourcelinkTo;
        this.createdTime = createdTime;
        this.photofile = photofile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getPhotofile() {
        return photofile;
    }

    public void setPhotofile(String photofile) {
        this.photofile = photofile;
    }
}
