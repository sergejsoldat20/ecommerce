package webshop.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import webshop.models.requests.Photo;
import webshop.services.PhotoService;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/photos")
public class PhotosController {

    private final PhotoService service;

    public PhotosController(PhotoService service){
        this.service = service;
    }

    @GetMapping("/by_post_id/{id}")
    public ResponseEntity<List<Photo>> getPhotosByPostId(@PathVariable Integer id){
        if(service.getAllPhotosByProductId(id) != null) {
            return ResponseEntity.ok(service.getAllPhotosByProductId(id));
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/upload/{id}")
    public ResponseEntity<Photo> uploadPhoto(@RequestParam("file") MultipartFile file,
                                             @PathVariable Integer id) throws IOException {

        Photo photo = service.uploadPhoto(id, file);
        if (photo != null) {
            return ResponseEntity.ok(photo);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
