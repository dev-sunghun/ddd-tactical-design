package kitchenpos.menus.tobe.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import kitchenpos.menus.tobe.domain.menuproduct.MenuProductQuantity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuProductQuantityTest {

    @DisplayName("0이상 숫자로 메뉴 상품 수량을 생성할 수 있다.")
    @Test
    void create() {
        // given
        long quantity = 1;

        // when
        MenuProductQuantity menuProductQuantity = new MenuProductQuantity(quantity);

        // then
        assertThat(menuProductQuantity.getValue()).isEqualTo(quantity);
    }

    @DisplayName("최소 개수 미만의 숫자로 메뉴 상품 수량을 생성 시, 예외가 발생한다.")
    @Test
    void createWithNegativeQuantity() {
        // given
        long quantity = MenuProductQuantity.MINIMUM_QUANTITY - 1;
        // when then
        assertThatThrownBy(() -> new MenuProductQuantity(quantity))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(MenuProductQuantity.ERROR_MESSAGE_QUANTITY);
    }
}
