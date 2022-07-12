package payload;

import javax.servlet.http.Part;
import java.util.Date;

public class ProductDetails {
    private Long id;
    private String titles;
    private String textData;
    private String description;
    private String sourcelinkTo;
    private byte [] photofile;
    private Date created_at;
    private Date updated_at;
    private String name;

    public ProductDetails() {
    }

    public ProductDetails(String titles,
                          String textData,
                          String description,
                          String sourcelinkTo,
                          byte[] photofile,
                          String name) {
        this.titles = titles;
        this.textData = textData;
        this.description = description;
        this.sourcelinkTo = sourcelinkTo;
        this.photofile = photofile;
        this.name = name;
    }

    public ProductDetails(Long id,
                          String titles,
                          String textData,
                          String description,
                          String sourcelinkTo,
                          byte[] photofile,
                          Date created_at,
                          Date updated_at,
                          String name)
    {
        this.id = id;
        this.titles = titles;
        this.textData = textData;
        this.description = description;
        this.sourcelinkTo = sourcelinkTo;
        this.photofile = photofile;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitles(String titles) {
        return this.titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getTextData(String textData) {
        return this.textData;
    }

    public void setTextData(String textData) {
        this.textData = textData;
    }

    public String getDescription(String description) {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSourcelinkTo(String sourcelinkTo) {
        return this.sourcelinkTo;
    }

    public void setSourcelinkTo(String sourcelinkTo) {
        this.sourcelinkTo = sourcelinkTo;
    }

    public byte[] getPhotofile(Part filePart) {
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

    public String getName(String categoryName) {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
