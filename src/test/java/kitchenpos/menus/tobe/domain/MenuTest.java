package kitchenpos.menus.tobe.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.UUID;
import kitchenpos.mock.client.FakePurgomalumClient;
import kitchenpos.products.tobe.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {

    private final FakePurgomalumClient purgomalumClient = new FakePurgomalumClient();


    @DisplayName("메뉴를 생성할 수 있다.")
    @Test
    void create() {
        // given
        final String name = "반반치킨";
        final BigDecimal price = new BigDecimal(100);
        final UUID id = UUID.randomUUID();
        // when
        Product product = new Product(id, name, purgomalumClient, price);

        // then
        assertThat(product.getName()).isEqualTo(name);
        assertThat(product.getPrice()).isEqualTo(price);
    }
}
