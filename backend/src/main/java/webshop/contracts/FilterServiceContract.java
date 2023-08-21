package webshop.contracts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import webshop.models.base.CrudService;
import webshop.models.dto.SingleFilter;
import webshop.models.responses.ProductResponse;

import java.util.HashSet;
import java.util.List;

public interface FilterServiceContract {

    Page<ProductResponse> filterByCategoryAndAttributes(String category, List<SingleFilter> filtersList, Pageable pageable, Double minPrice, Double maxPrice);

}
