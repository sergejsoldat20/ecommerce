package webshop.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webshop.exceptions.AppException;
import webshop.models.requests.ProductRequest;
import webshop.models.responses.ProductResponse;
import webshop.services.AccountService;
import webshop.services.AttributeService;
import webshop.services.ProductAttributeService;
import webshop.services.ProductService;

import java.sql.Timestamp;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final AccountService accountService;
    private final AttributeService attributeService;
    private final ProductAttributeService productAttributeService;

    public ProductController(ProductService productService, AccountService accountService, AttributeService attributeService, ProductAttributeService productAttributeService) {
        this.productService = productService;
        this.accountService = accountService;
        this.attributeService = attributeService;
        this.productAttributeService = productAttributeService;
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAll(Pageable page) throws AppException {
        Page<ProductResponse> response = productService.getAllProducts(page);
        if ( response != null){
            return ResponseEntity.ok(response);
        } else {
            throw new AppException("products not found");
        }
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Integer id) throws AppException {
        ProductResponse response = productService.findById(id, ProductResponse.class);
        if ( response != null){
            return ResponseEntity.ok(response);
        } else {
            throw new AppException("Single product not found");
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<ProductResponse> insert(@RequestBody ProductRequest productRequest) throws AppException {
        // set accountid, created time and isdeleted
        productRequest.setAccountId(accountService.getCurrentAccountId());
        productRequest.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        productRequest.setIsDeleted(false);
        ProductResponse response = productService.insert(productRequest, ProductResponse.class);
        if ( response != null) {
            return ResponseEntity.ok(response);
        } else {
            throw new AppException("Product not inserted");
        }
    }


   /* @GetMapping("/test")
    public ResponseEntity<?> GetAll(){

        AttributeEntity attribute = new AttributeEntity();
        attribute.setName("Color");
        attribute.setType("String");
        attribute.setCategoryId(1);

        // Creating a ProductEntity
        ProductEntity product = new ProductEntity();
        product.setName("Sample ProductRequest");
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
    }*/
}
