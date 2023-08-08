package webshop.contracts;

import org.springframework.web.multipart.MultipartFile;
import webshop.models.base.CrudService;
import webshop.models.requests.Photo;

import java.io.IOException;
import java.util.List;

public interface PhotoServiceContract extends CrudService<Integer> {
    List<Photo> getAllPhotosByProductId(Integer id);
    Photo uploadPhoto(Integer postId, MultipartFile file) throws IOException;
}
