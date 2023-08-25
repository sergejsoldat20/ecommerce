package webshop.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import webshop.contracts.ReportMessageContract;
import webshop.exceptions.AppException;
import webshop.mail.EmailService;
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
    private final EmailService emailService;

    private final ModelMapper modelMapper;
    public ReportMessageService(ReportMessageRepository repository, AccountService accountService, EmailService emailService, ModelMapper modelMapper) {
        super(repository, modelMapper, ReportMessageEntity.class);
        this.repository = repository;
        this.accountService = accountService;
        this.emailService = emailService;
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

    @Override
    public void sendEmailToUser(String email, String messageText, Integer messageId) {

        ReportMessageEntity reportMessageEntity = repository.getById(messageId);
        reportMessageEntity.setIsSeen(true);
        repository.save(reportMessageEntity);
        emailService.sendEmail(email, "Report message", messageText);
    }

    @Override
    public List<ReportMessageResponse> getUnreadMessages() {
        return repository
                .findAll()
                .stream()
                .filter(x -> !x.getIsSeen())
                .map(x -> {
                    ReportMessageResponse response = new ReportMessageResponse();
                    response.setId(x.getId());
                    response.setMessageText(x.getMessageText());
                    response.setCreatedTime(x.getCreatedTime());
                    response.setIsSeen(x.getIsSeen());
                    response.setAccountId(x.getAccountId());
                    try {
                        response.setAccountUsername(accountService.getAccountById(x.getAccountId()).getUsername());
                    } catch (AppException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        response.setAccountEmail(accountService.getAccountById(x.getAccountId()).getEmail());
                    } catch (AppException e) {
                        throw new RuntimeException(e);
                    }
                    response.setBelongsToAdmin(x.getBelongsToAdmin());
                    return response;
                }).toList();
    }

    @Override
    public ReportMessageResponse getMessageById(Integer id) throws AppException {
       ReportMessageEntity reportMessageEntity = super.findById(id, ReportMessageEntity.class);
       ReportMessageResponse response = new ReportMessageResponse();
        response.setId(reportMessageEntity.getId());
        response.setMessageText(reportMessageEntity.getMessageText());
        response.setCreatedTime(reportMessageEntity.getCreatedTime());
        response.setIsSeen(reportMessageEntity.getIsSeen());
        response.setAccountId(reportMessageEntity.getAccountId());
        try {
            response.setAccountUsername(accountService.getAccountById(reportMessageEntity.getAccountId()).getUsername());
        } catch (AppException e) {
            throw new RuntimeException(e);
        }

        try {
            response.setAccountEmail(accountService.getAccountById(reportMessageEntity.getAccountId()).getEmail());
        } catch (AppException e) {
            throw new RuntimeException(e);
        }
        response.setBelongsToAdmin(reportMessageEntity.getBelongsToAdmin());
        return response;
    }


}
