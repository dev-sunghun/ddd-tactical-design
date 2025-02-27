package kitchenpos.mock.fixture;

import java.math.BigDecimal;
import java.util.UUID;
import kitchenpos.mock.FakePurgomalumClient;
import kitchenpos.products.tobe.domain.Product;

public class ProductFixture {

    private static final FakePurgomalumClient CLIENT = new FakePurgomalumClient();

    public static Product create(final String name, final BigDecimal price) {
        return new Product(UUID.randomUUID(), name, CLIENT, price);
    }

    public static Product create(final BigDecimal price) {
        Product product = new Product();
        product.setPrice(price);
        return product;
    }

}
