package webshop.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webshop.models.requests.Attribute;
import webshop.models.responses.AttributeResponse;
import webshop.models.responses.Category;
import webshop.services.AttributeService;
import webshop.services.CategoryService;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/categories")
public class CategoriesController {

    private final CategoryService service;
    private final AttributeService attributeService;

    public CategoriesController(CategoryService service, AttributeService attributeService) {
        this.service = service;
        this.attributeService = attributeService;
    }

    @GetMapping("all-categories")
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(service.findAll(Category.class));
    }

    @GetMapping("attributes-by-category/{id}")
    public ResponseEntity<List<AttributeResponse>> getAllAttributesByCategoryId(@PathVariable Integer id) {
        return ResponseEntity.ok(attributeService.getAllByCategoryId(id));
    }
}
