package webshop.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import webshop.contracts.ReportMessageContract;
import webshop.models.base.CrudJpaService;
import webshop.models.entities.ReportMessageEntity;
import webshop.models.requests.ReportMessageRequest;
import webshop.models.responses.ReportMessageResponse;
import webshop.repositories.ReportMessageRepository;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ReportMessageService extends CrudJpaService<ReportMessageEntity, Integer> implements ReportMessageContract {

    private final ReportMessageRepository repository;
    private final AccountService accountService;

    private final ModelMapper modelMapper;
    public ReportMessageService(ReportMessageRepository repository, AccountService accountService, ModelMapper modelMapper) {
        super(repository, modelMapper, ReportMessageEntity.class);
        this.repository = repository;
        this.accountService = accountService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void insertReportMessage(ReportMessageRequest request) {
        request.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        request.setAccountId(accountService.getCurrentAccountId());
        request.setIsSeen(false);
        request.setBelongsToAdmin(false);
        super.insert(request, ReportMessageEntity.class);
    }

    @Override
    public List<ReportMessageResponse> getReportMessagesByAccountId(Integer id) {
        return repository
                .getByAccountId(id)
                .stream()
                .map(x -> {
                    ReportMessageResponse response = new ReportMessageResponse();
                    response.setId(x.getId());
                    response.setMessageText(x.getMessageText());
                    response.setCreatedTime(x.getCreatedTime());
                    response.setIsSeen(x.getIsSeen());
                    response.setAccountId(x.getAccountId());
                    response.setAccountUsername(accountService.getCurrentAccount().getUsername());
                    response.setBelongsToAdmin(x.getBelongsToAdmin());
                    return response;
                }).toList();
    }
}
