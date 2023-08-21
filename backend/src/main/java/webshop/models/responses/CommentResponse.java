package webshop.models.responses;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class CommentResponse {
    private String text;
    private Timestamp createdTime;
    // private Boolean belongToUser;
    private String username;
}
