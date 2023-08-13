package webshop.contracts;

import webshop.models.base.CrudService;
import webshop.models.entities.CommentEntity;
import webshop.models.requests.Comment;
import webshop.models.responses.CommentResponse;

import java.util.List;

public interface CommentServiceContract extends CrudService<Integer> {

    List<CommentResponse> getAllByProductId(Integer productId);
    CommentEntity insertToDatabase(Comment comment);

}
