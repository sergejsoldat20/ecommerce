package webshop.contracts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import webshop.models.base.CrudService;
import webshop.models.requests.ProductRequest;
import webshop.models.responses.ProductResponse;

import java.util.List;

public interface ProductServiceContract extends CrudService<Integer> {

    Page<ProductResponse> getAllProducts(Pageable page);

    Page<ProductRequest> getAllProductsByUserId(Pageable page, Integer accountId);

    Page<ProductRequest> filterProducts(Pageable page, Double priceFrom, Double priceTo, Boolean timeDesc);
    Page<ProductRequest> filterProductsByCategories(Pageable page, Integer categoryId, List<String> filters);
}
