package webshop.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webshop.models.entities.ProductAttributeEntity;
import webshop.models.entities.ProductAttributeEntityPK;
import webshop.models.requests.ProductAttributeRequest;
import webshop.services.ProductAttributeService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/attribute-values")
public class ProductAttributeController {

    private final ProductAttributeService productAttributeService;


    public ProductAttributeController(ProductAttributeService productAttributeService) {
        this.productAttributeService = productAttributeService;
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insertProductAttribute(@RequestBody ProductAttributeRequest request) {
        ProductAttributeEntity entity = new ProductAttributeEntity();
        entity.setValue(request.getValue());

        // set values for PK (primary key)
        ProductAttributeEntityPK pk = new ProductAttributeEntityPK();
        pk.setAttributeId(request.getAttributeId());
        pk.setProductId(request.getProductId());

        // set PK to entity
        entity.setId(pk);

        // insert to database
        productAttributeService.addToDatabase(entity);

        return ResponseEntity.ok("Attribute value added to database");
    }

    @GetMapping("/by-product-id/{id}")
    public ResponseEntity<?> getAllAttributeValuesByProductId(@PathVariable Integer id) {
        return ResponseEntity.ok(productAttributeService.getAttributeValuesByProductId(id));
    }
}
