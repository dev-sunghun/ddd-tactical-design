package kitchenpos.products.tobe.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

class ProductIdTest {

    @DisplayName("UUID로 상품 ID를 생성할 수 있다.")
    @Test
    void create() {
        // given
        UUID id = UUID.randomUUID();

        // when
        ProductId productId = new ProductId(id);

        // then
        assertThat(productId.getValue()).isEqualTo(id);
    }

    @DisplayName("상품 ID 값이 올바르지 않으면, 예외가 발생한다.")
    @ParameterizedTest
    @NullSource
    void createWithNull(UUID value) {
        // when then
        assertThatThrownBy(() -> new ProductId(value))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ProductId.ERROR_MESSAGE_VALUE_NULL);
    }
}
