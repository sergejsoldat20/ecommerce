package webshop.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
@Data
@Embeddable
public class ProductAttributeEntityPK implements Serializable {

    @Column(name = "attribute_id")
    private Integer attributeId;

    @Column(name = "product_id")
    private Integer productId;

}
