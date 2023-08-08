package webshop.contracts;

import webshop.models.base.CrudService;
import webshop.models.requests.Comment;

import java.util.List;

public interface CommentServiceContract extends CrudService<Integer> {

    List<Comment> getAllByProductId(Integer productId);
}
