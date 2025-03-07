package kitchenpos.products.tobe.domain;

import java.util.List;
import java.util.Optional;

public interface TobeProductRepository {

    Product save(Product product);

    Optional<Product> findById(ProductId id);

    List<Product> findAll();

    List<Product> findAllByIdIn(List<ProductId> ids);
}

