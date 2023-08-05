package webshop.models.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "report_message", schema = "public", catalog = "ecommerce_db")
public class ReportMessageEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "message_text")
    private String messageText;
    @Basic
    @Column(name = "created_time")
    private Object createdTime;
    @Basic
    @Column(name = "is_seen")
    private Boolean isSeen;
    @Basic
    @Column(name = "account_id")
    private Integer accountId;
    @Basic
    @Column(name = "product_id")
    private Integer productId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Object getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Object createdTime) {
        this.createdTime = createdTime;
    }

    public Boolean getSeen() {
        return isSeen;
    }

    public void setSeen(Boolean seen) {
        isSeen = seen;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportMessageEntity that = (ReportMessageEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(messageText, that.messageText) && Objects.equals(createdTime, that.createdTime) && Objects.equals(isSeen, that.isSeen) && Objects.equals(accountId, that.accountId) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, messageText, createdTime, isSeen, accountId, productId);
    }
}
