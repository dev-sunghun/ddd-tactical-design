package kitchenpos.products.tobe.infra.ohs;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.UUID;
import kitchenpos.menus.tobe.domain.ohs.ProductPriceService;
import kitchenpos.products.tobe.domain.Product;
import kitchenpos.products.tobe.domain.TobeProductRepository;
import org.springframework.stereotype.Component;

// OHS(Open Host Service)
// 서비스 제공자 Product는 사용자 Menu의 요건에 최적화된 공표된 언어(published language)를 구현한다.

@Component
public class ProductPriceServiceImpl implements ProductPriceService {

    private final TobeProductRepository productRepository;

    public ProductPriceServiceImpl(TobeProductRepository productRepository) {
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
