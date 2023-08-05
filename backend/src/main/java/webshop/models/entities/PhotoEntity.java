package webshop.models.entities;

import jakarta.persistence.*;
import lombok.*;
import webshop.models.base.BaseEntity;

import java.util.Objects;

@Data
@Entity
@jakarta.persistence.Table(name = "photo", schema = "public", catalog = "ecommerce_db")
public class PhotoEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "photo_url")
    private String photoUrl;

    @Basic
    @Column(name = "product_id")
    private Integer productId;

}
