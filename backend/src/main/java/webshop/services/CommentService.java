package webshop.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import webshop.contracts.CommentServiceContract;
import webshop.models.base.CrudJpaService;
import webshop.models.entities.CommentEntity;
import webshop.models.requests.Comment;
import webshop.repositories.CommentRepository;

import java.util.List;

@Service
public class CommentService extends CrudJpaService<CommentEntity, Integer> implements CommentServiceContract {

    private final CommentRepository repository;
    private final ModelMapper modelMapper;
    public CommentService(CommentRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper, CommentEntity.class);
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Comment> getAllByProductId(Integer productId) {
        return null;
    }
}
