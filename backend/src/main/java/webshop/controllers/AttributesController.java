package webshop.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webshop.services.AttributeService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/attributes")
public class AttributesController {

    private final AttributeService attributeService;

    public AttributesController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @GetMapping("/by-category-id/{id}")
    public ResponseEntity<?> getByCategoryId(@PathVariable Integer id) {
        return ResponseEntity.ok(attributeService.getAllByCategoryId(id));
    }
}
