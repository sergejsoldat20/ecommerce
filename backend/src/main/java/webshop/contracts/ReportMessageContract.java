package webshop.contracts;

import webshop.models.base.CrudService;
import webshop.models.requests.ReportMessageRequest;
import webshop.models.responses.ReportMessageResponse;

import java.util.List;

public interface ReportMessageContract extends CrudService<Integer>{

    void insertReportMessage(ReportMessageRequest request);
    List<ReportMessageResponse> getReportMessagesByAccountId(Integer id);
}
