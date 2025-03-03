package kitchenpos.mock.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import kitchenpos.mock.client.FakePurgomalumClient;
import kitchenpos.products.tobe.domain.Product;
import kitchenpos.products.tobe.domain.TobeProductRepository;

public class FakeProductRepository implements TobeProductRepository {

    private final Map<UUID, Product> dataMap = new ConcurrentHashMap<>();
    private final FakePurgomalumClient client = new FakePurgomalumClient();

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            UUID id = UUID.randomUUID();
            Product newProduct = new Product(UUID.randomUUID(), product.getName(), client,
                product.getPrice());
            dataMap.put(id, newProduct);
            return product;
        }
        UUID id = product.getId();
        dataMap.put(id, product);
        return product;
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return dataMap.values()
            .stream()
            .filter(product -> product.getId().equals(id))
            .findFirst();
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(dataMap.values());
    }

    @Override
    public List<Product> findAllByIdIn(List<UUID> ids) {
        return dataMap.values().stream()
            .filter(
                product -> ids.stream().toList().contains(product.getId()))
            .toList();
    }
}
