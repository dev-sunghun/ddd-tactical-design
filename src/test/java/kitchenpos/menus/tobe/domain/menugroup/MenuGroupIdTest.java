package kitchenpos.menus.tobe.domain.menugroup;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

class MenuGroupIdTest {

    @DisplayName("UUID로 메뉴 그룹 ID를 생성할 수 있다.")
    @Test
    void create() {
        // given
        UUID id = UUID.randomUUID();

        // when
        MenuGroupId menuGroupId = new MenuGroupId(id);

        // then
        assertThat(menuGroupId.getValue()).isEqualTo(id);
    }

    @DisplayName("메뉴 그룹 ID 값이 올바르지 않으면, 예외가 발생한다.")
    @ParameterizedTest
    @NullSource
    void createWitNull(UUID value) {
        // when then
        assertThatThrownBy(() -> new MenuGroupId(value))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(MenuGroupId.ERROR_MESSAGE_VALUE_NULL);
    }
}
