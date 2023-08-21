package webshop.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webshop.exceptions.AppException;
import webshop.models.dto.SingleFilter;
import webshop.models.requests.ProductRequest;
import webshop.models.responses.ProductResponse;
import webshop.services.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final AccountService accountService;
    private final FilterService filterService;


    public ProductController(ProductService productService, AccountService accountService, FilterService filterService) {
        this.productService = productService;
        this.accountService = accountService;
        this.filterService = filterService;
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

    @GetMapping("/by-account-id/{id}")
    public ResponseEntity<List<ProductResponse>> getAllByAccountId(@PathVariable Integer id) {
        List<ProductResponse> response = productService.getAllProductsByUserId(id);
        if(response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) throws AppException {
        productService.setToDeleted(id);
        return ResponseEntity.ok("Product deleted");
    }

    @PostMapping("/filter")
    public ResponseEntity<?> responseEntity(
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "minPrice", required = false) Double minPrice,
            @RequestParam(name = "maxPrice", required = false) Double maxPrice,
            @RequestBody List<SingleFilter> filters, Pageable pageable) {

        if (category == null && filters.size() == 0){
            return ResponseEntity.ok(productService.getAllProducts(pageable));
        } else if(category != null && filters.size() == 0) {
            return ResponseEntity.ok(productService.filterProductsByCategories(pageable, category, minPrice, maxPrice));
        }

        return ResponseEntity.ok(filterService.filterByCategoryAndAttributes(category, filters, pageable, minPrice, maxPrice));
    }
}
