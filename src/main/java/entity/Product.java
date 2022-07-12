package entity;


import java.util.Date;

public class Product {
    private Long id;
    private String titles;
    private String textData;
    private String description;
    private String sourcelinkTo;
    private byte [] photofile;
    private Date created_at;
    private Date updated_at;
    private int category_id;

    public Product() {
    }

    public Product(Long id, String titles,
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

    public Product(Long id, String titles,
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

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
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
