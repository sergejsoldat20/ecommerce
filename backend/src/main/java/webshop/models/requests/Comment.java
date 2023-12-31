package webshop.models.requests;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Comment {
    private String text;
    private Timestamp createdTime;
    private Integer accountId;
    private Integer productId;
}
