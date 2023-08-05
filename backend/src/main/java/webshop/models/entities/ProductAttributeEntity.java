package webshop.models.entities;

import jakarta.persistence.*;
import lombok.*;
import webshop.models.base.BaseEntity;

import java.util.Objects;

@Data
@Entity
@jakarta.persistence.Table(name = "product_attribute", schema = "public", catalog = "ecommerce_db")
public class ProductAttributeEntity implements BaseEntity<ProductAttributeEntityPK> {

    @EmbeddedId
    private ProductAttributeEntityPK id;

   /* @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "product_id")
    private Integer productId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "attribute_id")
    private Integer attributeId;*/

    @Basic
    @Column(name = "value")
    private String value;


}
