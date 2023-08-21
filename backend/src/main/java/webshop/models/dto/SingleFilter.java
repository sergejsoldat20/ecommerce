package webshop.models.dto;

import lombok.Data;

@Data
public class SingleFilter {
    String valueFrom;
    String valueTo;
    String attributeName;
    // String type;
}
