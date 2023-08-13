package webshop.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import webshop.contracts.PhotoServiceContract;
import webshop.models.base.CrudJpaService;
import webshop.models.entities.PhotoEntity;
import webshop.models.requests.Photo;
import webshop.repositories.PhotoRepository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PhotoService extends CrudJpaService<PhotoEntity, Integer> implements PhotoServiceContract {

    private final PhotoRepository repository;
    private final Cloudinary cloudinary;
    private final ModelMapper modelMapper;
    public PhotoService(PhotoRepository repository, ModelMapper modelMapper, Cloudinary cloudinary) {
        super(repository, modelMapper, PhotoEntity.class);
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.cloudinary = cloudinary;
    }

    @Override
    public List<Photo> getAllPhotosByProductId(Integer id) {
        return repository.getPhotoEntitiesByProductId(id)
                .stream()
                .map(x -> modelMapper.map(x, Photo.class))
                .collect(Collectors.toList());
    }

    @Override
    public Photo uploadPhoto(Integer postId, MultipartFile file) throws IOException {

        Photo photo = new Photo();
        photo.setProductId(postId);
        Map uploadResult = cloudinary.uploader()
                .upload(file.getBytes(), ObjectUtils.emptyMap());

        // Build the transformation for cropping
        Transformation transformation = new Transformation()
                .crop("fill")
                .gravity("center")
                .width(270)
                .height(270);

        String croppedImageUrl = cloudinary.url()
                .transformation(transformation)
                .generate(uploadResult.get("public_id").toString());

        photo.setPhotoUrl(croppedImageUrl);
        return super.insert(photo, Photo.class);
    }
}
