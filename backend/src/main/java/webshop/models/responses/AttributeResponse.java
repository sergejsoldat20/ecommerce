package webshop.models.responses;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class AttributeResponse {
    private Integer id;
    private String name;
   //  private String type;
    private Integer categoryId;
}
