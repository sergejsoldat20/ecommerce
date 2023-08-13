package webshop.models.requests;

import lombok.Data;

@Data
public class Photo {
    private Integer id;
    private Integer productId;
    private String photoUrl;
}
