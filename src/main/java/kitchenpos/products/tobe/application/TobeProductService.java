package kitchenpos.products.tobe.application;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import kitchenpos.products.tobe.domain.MenuDisplayTranslator;
import kitchenpos.products.tobe.domain.Product;
import kitchenpos.products.tobe.domain.TobeProductRepository;
import kitchenpos.products.tobe.dto.ProductRequest;
import kitchenpos.products.tobe.dto.ProductResponse;
import kitchenpos.shared.client.PurgomalumClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TobeProductService {

    private final TobeProductRepository productRepository;
    private final PurgomalumClient purgomalumClient;
    private final MenuDisplayTranslator menuDisplayTranslator;

    public TobeProductService(
        final TobeProductRepository productRepository,
        final PurgomalumClient purgomalumClient,
        final MenuDisplayTranslator menuDisplayTranslator
    ) {
        this.productRepository = productRepository;
        this.purgomalumClient = purgomalumClient;
        this.menuDisplayTranslator = menuDisplayTranslator;
    }

    @Transactional
    public ProductResponse create(final ProductRequest request) {
        final Product product = new Product(UUID.randomUUID(), request.name(), purgomalumClient,
            request.price());
        return ProductResponse.from(productRepository.save(product));
    }

    @Transactional
    public ProductResponse changePrice(final UUID productId, final ProductRequest request) {
        final Product product = productRepository.findById(productId)
            .orElseThrow(
                () -> new NoSuchElementException("Product not found with id: " + productId));
        product.changePrice(request.price());

        // ACL을 통한 메뉴 표시 상태 업데이트
        menuDisplayTranslator.updateMenusDisplayStatusByProductId(productId);
        return ProductResponse.from(product);
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
