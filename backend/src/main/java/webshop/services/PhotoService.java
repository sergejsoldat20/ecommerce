package webshop.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import webshop.contracts.PhotoServiceContract;
import webshop.models.base.CrudJpaService;
import webshop.models.entities.PhotoEntity;
import webshop.models.requests.Photo;
import webshop.repositories.PhotoRepository;

import java.io.IOException;
import java.util.List;

@Service
public class PhotoService extends CrudJpaService<PhotoEntity, Integer> implements PhotoServiceContract {

    private final PhotoRepository repository;
    private final ModelMapper modelMapper;
    public PhotoService(PhotoRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper, PhotoEntity.class);
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Photo> getAllPhotosByProductId(Integer id) {
        return null;
    }

    @Override
    public Photo uploadPhoto(Integer postId, MultipartFile file) throws IOException {
        return null;
    }
}
