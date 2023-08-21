package webshop.models.entities;

import jakarta.persistence.*;
import lombok.*;
import webshop.models.base.BaseEntity;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "report_message", schema = "public", catalog = "ecommerce_db")
public class ReportMessageEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "message_text")
    private String messageText;
    @Basic
    @Column(name = "created_time")
    private Timestamp createdTime;
    @Basic
    @Column(name = "is_seen")
    private Boolean isSeen;
    @Basic
    @Column(name = "account_id")
    private Integer accountId;
    @Basic
    @Column(name = "belongs_to_admin")
    private Boolean belongsToAdmin;

    public ReportMessageEntity() {
    }

    public Boolean getSeen() {
        return isSeen;
    }

    public void setSeen(Boolean seen) {
        isSeen = seen;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ReportMessageEntity;
    }

}
