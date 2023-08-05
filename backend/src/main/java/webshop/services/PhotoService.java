package webshop.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import webshop.contracts.PhotoServiceContract;
import webshop.models.base.CrudJpaService;
import webshop.models.entities.PhotoEntity;
import webshop.repositories.PhotoRepository;
@Service
public class PhotoService extends CrudJpaService<PhotoEntity, Integer> implements PhotoServiceContract {

    private final PhotoRepository repository;
    private final ModelMapper modelMapper;
    public PhotoService(PhotoRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper, PhotoEntity.class);
        this.repository = repository;
        this.modelMapper = modelMapper;
    }
}
