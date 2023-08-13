package webshop.models.requests;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
@Data
public class ProductRequest {
    private String name;
    private String description;
    private Timestamp createdTime;
    private Integer categoryId;
    private Integer accountId;
    private Boolean isDeleted;
    private Double price;
}
