package webshop.mail;

public interface EmailServiceContract {
    void sendEmail(String to, String subject, String body);
}
