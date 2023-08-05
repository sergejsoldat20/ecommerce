package webshop.models.entities;

import jakarta.persistence.*;
import lombok.*;
import webshop.models.base.BaseEntity;

import java.sql.Timestamp;
import java.util.Objects;

@Data
@Entity
@jakarta.persistence.Table(name = "comment", schema = "public", catalog = "ecommerce_db")
public class CommentEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "text")
    private String text;

    @Basic
    @Column(name = "created_time")
    private Timestamp createdTime;

    @Basic
    @Column(name = "account_id")
    private Integer accountId;

    @Basic
    @Column(name = "product_id")
    private Integer productId;

}
