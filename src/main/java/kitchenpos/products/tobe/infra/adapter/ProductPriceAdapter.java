package kitchenpos.products.tobe.infra.adapter;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.UUID;
import kitchenpos.menus.tobe.domain.menu.ProductPriceClient;
import kitchenpos.products.tobe.domain.Product;
import kitchenpos.products.tobe.domain.TobeProductRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductPriceAdapter implements ProductPriceClient {

    private final TobeProductRepository productRepository;

    public ProductPriceAdapter(TobeProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public BigDecimal getPriceByProductId(UUID productId) {
        final Product product = productRepository.findById(productId)
            .orElseThrow(
                () -> new NoSuchElementException("Product not found with id: " + productId));
        return product.getPrice();
    }
}
