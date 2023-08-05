package webshop.models.entities;

import jakarta.persistence.*;
import lombok.*;
import webshop.models.base.BaseEntity;

import java.util.Objects;

@Data
@Entity
@jakarta.persistence.Table(name = "attribute", schema = "public", catalog = "ecommerce_db")
public class AttributeEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "type")
    private String type;

    @Basic
    @Column(name = "CategoryId")
    private Integer categoryId;

}
