package kitchenpos.eatinorders.tobe.domain.eatinorder;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EatInOrderLineItemTest {

    @DisplayName("매장 식사 주문 항목을 생성할 수 있다.")
    @Test
    void create() {
        // given
        final Long seq = 1L;
        final long quantity = 1;
        final UUID menuId = UUID.randomUUID();
        final BigDecimal price = BigDecimal.valueOf(1000);

        // when
        EatInOrderLineItem eatInOrderLineItem = new EatInOrderLineItem(seq, quantity, menuId,
            price);

        // then
        assertThat(eatInOrderLineItem.getSeq()).isEqualTo(seq);
        assertThat(eatInOrderLineItem.getQuantity()).isEqualTo(quantity);
        assertThat(eatInOrderLineItem.getMenuId()).isEqualTo(menuId);
        assertThat(eatInOrderLineItem.getPrice()).isEqualTo(price);
    }
}
