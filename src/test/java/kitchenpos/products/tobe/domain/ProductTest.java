package kitchenpos.products.tobe.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.UUID;
import kitchenpos.mock.FakePurgomalumClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTest {

    private final FakePurgomalumClient purgomalumClient = new FakePurgomalumClient();


    @DisplayName("")
    @Test
    void create() {
        // given
        final String name = "양념치킨";
        final BigDecimal price = new BigDecimal(100);
        final UUID id = UUID.randomUUID();
        // when
        Product product = new Product(id, name, purgomalumClient, price);

        // then
        assertThat(product.getName()).isEqualTo(name);
        assertThat(product.getPrice()).isEqualTo(price);
    }
}
