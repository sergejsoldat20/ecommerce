package webshop.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webshop.models.entities.CommentEntity;
import webshop.models.requests.Comment;
import webshop.models.responses.CommentResponse;
import webshop.services.CommentService;

import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/comments")
public class CommentsController {

    private final CommentService commentService;

    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/by-product-id/{id}")
    public ResponseEntity<List<CommentResponse>> getCommentsByProductId(@PathVariable Integer id){

        List<CommentResponse> response = commentService.getAllByProductId(id);
        if(response != null) {
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody Comment comment) {
        comment.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        CommentEntity result = commentService.insertToDatabase(comment);
        return ResponseEntity.ok(result);
    }
}
