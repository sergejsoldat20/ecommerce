package webshop.contracts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import webshop.exceptions.AppException;
import webshop.models.base.CrudService;
import webshop.models.requests.ProductRequest;
import webshop.models.responses.ProductResponse;

import java.util.List;

public interface ProductServiceContract extends CrudService<Integer> {

    Page<ProductResponse> getAllProducts(Pageable page);

    List<ProductResponse> getAllProductsByUserId( Integer accountId);

    Page<ProductRequest> filterProducts(Pageable page, Double priceFrom, Double priceTo, Boolean timeDesc);
    Page<ProductResponse> filterProductsByCategories(Pageable page, String category, Double minPrice, Double maxPrice);

    void setToDeleted(Integer id) throws AppException;

}
