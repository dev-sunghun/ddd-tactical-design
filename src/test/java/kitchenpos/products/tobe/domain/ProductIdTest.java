package kitchenpos.products.tobe.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

class ProductIdTest {

    @DisplayName("UUID로 상품 이름을 생성할 수 있다.")
    @Test
    void create() {
        // given
        UUID id = UUID.randomUUID();

        // when
        ProductId productId = new ProductId(id);

        // then
        assertThat(productId.getValue()).isEqualTo(id);
    }

    @DisplayName("최소 가격 미만으로 상품 가격을 생성 시, 예외가 발생한다.")
    @ParameterizedTest
    @NullSource
    void createWithNegativePrice(UUID value) {
        // when then
        assertThatThrownBy(() -> new ProductId(value))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ProductId.ERROR_MESSAGE_VALUE_NULL);
    }
}
