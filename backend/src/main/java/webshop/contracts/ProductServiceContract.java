package webshop.contracts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import webshop.models.base.CrudService;
import webshop.models.requests.Product;

import java.util.List;

public interface ProductServiceContract extends CrudService<Integer> {

    Page<Product> getAllProductsByUserId(Pageable page, Integer accountId);

    Page<Product> filterProducts(Pageable page, Double priceFrom, Double priceTo, Boolean timeDesc);
    Page<Product> filterProductsByCategories(Pageable page, Integer categoryId, List<String> filters);
}
