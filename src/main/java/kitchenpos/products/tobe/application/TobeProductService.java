package kitchenpos.products.tobe.application;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import kitchenpos.menus.domain.Menu;
import kitchenpos.menus.domain.MenuRepository;
import kitchenpos.products.tobe.domain.Product;
import kitchenpos.products.tobe.domain.ProductId;
import kitchenpos.products.tobe.domain.TobeProductRepository;
import kitchenpos.products.tobe.dto.ProductRequest;
import kitchenpos.products.tobe.dto.ProductResponse;
import kitchenpos.shared.client.PurgomalumClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TobeProductService {

    private final TobeProductRepository productRepository;
    private final MenuRepository menuRepository;
    private final PurgomalumClient purgomalumClient;

    public TobeProductService(
        final TobeProductRepository productRepository,
        final MenuRepository menuRepository,
        final PurgomalumClient purgomalumClient
    ) {
        this.productRepository = productRepository;
        this.menuRepository = menuRepository;
        this.purgomalumClient = purgomalumClient;
    }

    @Transactional
    public ProductResponse create(final ProductRequest request) {
        final Product product = new Product(UUID.randomUUID(), request.name(), purgomalumClient,
            request.price());
        return ProductResponse.from(productRepository.save(product));
    }

    @Transactional
    public ProductResponse changePrice(final UUID productId, final ProductRequest request) {
        final Product product = productRepository.findById(new ProductId(productId))
            .orElseThrow(
                () -> new NoSuchElementException("Product not found with id: " + productId));
        product.setPrice(request.price());

        updateMenusDisplayStatus(productId);
        return ProductResponse.from(product);
    }

    public void updateMenusDisplayStatus(final UUID productId) {
        final List<Menu> menus = menuRepository.findAllByProductId(productId);
        menus.forEach(Menu::updateDisplayStatus);
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> getProductResponses() {
        return findAll().stream()
            .map(ProductResponse::from).toList();
    }
}
