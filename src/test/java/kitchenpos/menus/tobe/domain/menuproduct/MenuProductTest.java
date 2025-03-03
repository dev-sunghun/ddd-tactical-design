package kitchenpos.menus.tobe.domain.menuproduct;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import kitchenpos.mock.fixture.ProductFixture;
import kitchenpos.products.tobe.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuProductTest {

    @DisplayName("메뉴 상품을 생성할 수 있다.")
    @Test
    void create() {
        // given
        final long seq = 1L;
        final BigDecimal price = new BigDecimal(100);
        final long quantity = 1L;
        final Product product = ProductFixture.create(price);

        // when
        MenuProduct menuProduct = new MenuProduct(seq, product.getId(), quantity);

        // then
        assertThat(menuProduct.getSeq()).isEqualTo(seq);
        assertThat(menuProduct.getProductId()).isEqualTo(product.getId());
        assertThat(menuProduct.getQuantity()).isEqualTo(quantity);
    }
}
