package kitchenpos.menus.tobe.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.math.BigDecimal;
import kitchenpos.menus.tobe.domain.menu.MenuPrice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuPriceTest {

    @DisplayName("가격으로 메뉴 가격을 생성할 수 있다.")
    @Test
    void create() {
        // given
        BigDecimal price = new BigDecimal(100);

        // when
        MenuPrice menuPrice = new MenuPrice(price);

        // then
        assertThat(menuPrice.getValue()).isEqualTo(price);
    }

    @DisplayName("최소 가격 미만으로 메뉴 가격을 생성 시, 예외가 발생한다.")
    @Test
    void createWithNegativePrice() {
        // given
        BigDecimal price = new BigDecimal(MenuPrice.MINIMUM_PRICE - 1);
        // when then
        assertThatThrownBy(() -> new MenuPrice(price))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(MenuPrice.ERROR_MESSAGE_PRICE);
    }
}
