package webshop.models.responses;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ProductResponse {
    private Integer id;
    private String name;
    private String description;
    private Timestamp createdTime;
    private Integer categoryId;
    private Integer accountId;
    private Double price;
}
