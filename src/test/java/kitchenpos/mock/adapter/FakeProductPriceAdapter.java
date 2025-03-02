package kitchenpos.mock.adapter;

import java.math.BigDecimal;
import java.util.UUID;
import kitchenpos.menus.tobe.domain.menu.ProductPriceClient;
import kitchenpos.mock.TestUtil;
import kitchenpos.mock.persistence.FakeProductRepository;

public class FakeProductPriceAdapter implements ProductPriceClient {

    public static BigDecimal PRICE = TestUtil.PRICE;
    private final FakeProductRepository productRepository;

    public FakeProductPriceAdapter(FakeProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public BigDecimal getPriceByProductId(UUID productId) {
        if(productRepository == null) {
            return PRICE;
        }
        return productRepository.findById(productId).get().getPrice();
    }
}
