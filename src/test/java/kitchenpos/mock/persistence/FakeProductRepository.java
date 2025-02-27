package kitchenpos.mock.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import kitchenpos.mock.FakePurgomalumClient;
import kitchenpos.products.tobe.domain.Product;
import kitchenpos.products.tobe.domain.ProductId;
import kitchenpos.products.tobe.domain.TobeProductRepository;

public class FakeProductRepository implements TobeProductRepository {

    private final Map<ProductId, Product> dataMap = new ConcurrentHashMap<>();
    private final FakePurgomalumClient client = new FakePurgomalumClient();

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            UUID id = UUID.randomUUID();
            Product newProduct = new Product(UUID.randomUUID(), product.getName(), client,
                product.getPrice());
            dataMap.put(new ProductId(id), newProduct);
            return product;
        }
        UUID id = product.getId();
        dataMap.put(new ProductId(id), product);
        return product;
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        return dataMap.values()
            .stream()
            .filter(product -> product.getId().equals(id.getValue()))
            .findFirst();
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(dataMap.values());
    }

    @Override
    public List<Product> findAllByIdIn(List<ProductId> ids) {
        return dataMap.values().stream()
            .filter(
                product -> ids.stream().map(ProductId::getValue).toList().contains(product.getId()))
            .toList();
    }
}
