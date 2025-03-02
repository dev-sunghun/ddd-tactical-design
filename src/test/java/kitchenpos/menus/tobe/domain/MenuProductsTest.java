package kitchenpos.menus.tobe.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import kitchenpos.menus.tobe.domain.menuproduct.MenuProduct;
import kitchenpos.menus.tobe.domain.menuproduct.MenuProductQuantity;
import kitchenpos.menus.tobe.domain.menuproduct.MenuProducts;
import kitchenpos.mock.fixture.ProductFixture;
import kitchenpos.products.tobe.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuProductsTest {

    @DisplayName("메뉴 상품 리스트를 생성할 수 있다.")
    @Test
    void create() {
        // given
        final long seq = 1L;
        final BigDecimal price = new BigDecimal(100);
        final long quantity = 1L;
        final Product product = ProductFixture.create(price);

        List<MenuProduct> menuProductList = List.of(
            new MenuProduct(seq, product, new MenuProductQuantity(quantity)),
            new MenuProduct((seq + 1), product, new MenuProductQuantity(quantity))
        );
        
        // when
        MenuProducts menuProducts = new MenuProducts(menuProductList);

        // then
        assertThat(menuProducts).isNotNull();
        assertThat(menuProducts.getValue()).hasSize(2);
        assertThat(menuProducts.getValue()).containsExactlyElementsOf(menuProductList);
        
        // 각 MenuProduct의 속성 검증
        menuProducts.getValue().forEach(menuProduct -> {
            assertThat(menuProduct.getProduct()).isEqualTo(product);
            assertThat(menuProduct.getQuantity()).isEqualTo(quantity);
            assertThat(menuProduct.calculatePrice()).isEqualTo(price.multiply(BigDecimal.valueOf(quantity)));
        });
    }
}
