package kitchenpos.mock.fixture;

import java.math.BigDecimal;
import java.util.UUID;
import kitchenpos.mock.FakePurgomalumClient;
import kitchenpos.products.tobe.domain.Product;

public class ProductFixture {

    public static final String DEFAULT_PRODUCT_NAME = "피자";
    private static final FakePurgomalumClient CLIENT = new FakePurgomalumClient();

    public static Product create(final String name, final BigDecimal price) {
        return new Product(UUID.randomUUID(), name, CLIENT, price);
    }

    public static Product create(final BigDecimal price) {
        return new Product(UUID.randomUUID(), DEFAULT_PRODUCT_NAME, CLIENT, price);
    }

}
