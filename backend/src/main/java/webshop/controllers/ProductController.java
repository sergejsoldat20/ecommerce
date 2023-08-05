package webshop.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webshop.models.entities.AttributeEntity;
import webshop.models.entities.ProductAttributeEntity;
import webshop.models.entities.ProductAttributeEntityPK;
import webshop.models.entities.ProductEntity;
import webshop.services.AttributeService;
import webshop.services.ProductAttributeService;
import webshop.services.ProductService;

import java.sql.Timestamp;

@RestController
@RequestMapping("/test")
public class ProductController {

    private final ProductService productService;
    private final AttributeService attributeService;
    private final ProductAttributeService productAttributeService;

    public ProductController(ProductService productService, AttributeService attributeService, ProductAttributeService productAttributeService) {
        this.productService = productService;
        this.attributeService = attributeService;
        this.productAttributeService = productAttributeService;
    }

    @GetMapping
    public ResponseEntity<?> GetAll(){

        AttributeEntity attribute = new AttributeEntity();
        attribute.setName("Color");
        attribute.setType("String");
        attribute.setCategoryId(1);

        // Creating a ProductEntity
        ProductEntity product = new ProductEntity();
        product.setName("Sample Product");
        product.setDescription("This is a sample product description.");
        product.setCategoryId(1);
        product.setAccountId(1);
        product.setDeleted(false);
        product.setCreatedTime(new Timestamp(System.currentTimeMillis()));

        // Creating a ProductAttributeEntity
        ProductAttributeEntity productAttribute = new ProductAttributeEntity();
        productAttribute.setValue("Red");

        // Setting the relationship between product and attribute
        //productAttribute.setProductId(product.getId());

        ProductAttributeEntityPK pk = new ProductAttributeEntityPK();
        pk.setProductId(2);
        pk.setAttributeId(1);

        productAttribute.setId(pk);
        //productAttribute.setAttributeId(attribute.getId());

        productService.insert(product, ProductEntity.class);
        attributeService.insert(attribute, AttributeEntity.class);
        productAttributeService.addToDatabase(productAttribute);


        return ResponseEntity.ok("ok");
    }
}
