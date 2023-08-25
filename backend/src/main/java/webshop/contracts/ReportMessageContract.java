package webshop.contracts;

import webshop.exceptions.AppException;
import webshop.models.base.CrudService;
import webshop.models.requests.ReportMessageRequest;
import webshop.models.responses.ReportMessageResponse;

import java.util.List;

public interface ReportMessageContract extends CrudService<Integer>{

    void insertReportMessage(ReportMessageRequest request);
    List<ReportMessageResponse> getReportMessagesByAccountId(Integer id);
    void sendEmailToUser(String email, String messageText, Integer messageId);
    List<ReportMessageResponse> getUnreadMessages();

    ReportMessageResponse getMessageById(Integer id) throws AppException;
}
