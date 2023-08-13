package webshop.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import webshop.contracts.CommentServiceContract;
import webshop.models.base.CrudJpaService;
import webshop.models.entities.CommentEntity;
import webshop.models.requests.Comment;
import webshop.models.responses.CommentResponse;
import webshop.repositories.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService extends CrudJpaService<CommentEntity, Integer> implements CommentServiceContract {

    private final CommentRepository repository;
    private final AccountService accountService;
    private final ModelMapper modelMapper;
    public CommentService(CommentRepository repository, AccountService accountService, ModelMapper modelMapper) {
        super(repository, modelMapper, CommentEntity.class);
        this.repository = repository;
        this.accountService = accountService;
        this.modelMapper = modelMapper;
    }



    @Override
    public List<CommentResponse> getAllByProductId(Integer productId) {
        Integer currentUserId = accountService.getCurrentAccount().getId();
        List<CommentResponse> response = repository
                .getAllByProductId(productId)
                .stream()
                .map(x -> {
                    CommentResponse commentResponse = new CommentResponse();
                    commentResponse.setText(x.getText());
                    commentResponse.setCreatedTime(x.getCreatedTime());
                    if (x.getAccountId() == currentUserId) {
                        commentResponse.setBelongToUser(true);
                    } else {
                        commentResponse.setBelongToUser(false);
                    }
                    return commentResponse;
                }).collect(Collectors.toList());

        return response;
    }

    @Override
    public CommentEntity insertToDatabase(Comment comment) {
        return super.insert(comment, CommentEntity.class);
    }


}
