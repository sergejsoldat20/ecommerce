package webshop.models.requests;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReportMessageRequest {
    private String messageText;
    private Timestamp createdTime;
    private Boolean isSeen;
    private Integer accountId;
    private Boolean belongsToAdmin;
}
