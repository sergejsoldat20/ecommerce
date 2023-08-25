package webshop.models.responses;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class ReportMessageResponse {
    private Integer id;
    private String messageText;
    private Object createdTime;
    private Boolean isSeen;
    private Integer accountId;
    private String accountUsername;
    private String accountEmail;
    private Boolean belongsToAdmin;
}
