package payload;

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

    public ProductDetails(Long id, String titles, String textData, String description, String sourcelinkTo, byte[] photofile, Date created_at, Date updated_at, String name) {
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
}
