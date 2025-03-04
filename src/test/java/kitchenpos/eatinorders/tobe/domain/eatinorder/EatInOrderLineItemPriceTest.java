package kitchenpos.eatinorders.tobe.domain.eatinorder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EatInOrderLineItemPriceTest {

    @DisplayName("매장 식사 주문 항목 가격을 생성할 수 있다.")
    @Test
    void create() {
        // given
        BigDecimal price = new BigDecimal(100);

        // when
        EatInOrderLineItemPrice eatInOrderLineItemPrice = new EatInOrderLineItemPrice(price);

        // then
        assertThat(eatInOrderLineItemPrice.getValue()).isEqualTo(price);
    }

    @DisplayName("최소 가격 미만으로 매장 식사 주문 항목 가격을 생성 시, 예외가 발생한다.")
    @Test
    void createWithNegativePrice() {
        // given
        BigDecimal price = new BigDecimal(EatInOrderLineItemPrice.MINIMUM_PRICE - 1);
        // when then
        assertThatThrownBy(() -> new EatInOrderLineItemPrice(price))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(EatInOrderLineItemPrice.ERROR_MESSAGE_PRICE);
    }
}
