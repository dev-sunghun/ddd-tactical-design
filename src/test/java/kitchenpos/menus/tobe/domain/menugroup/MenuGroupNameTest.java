package kitchenpos.menus.tobe.domain.menugroup;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class MenuGroupNameTest {

    @DisplayName("이름으로 메뉴 그룹 이름을 생성할 수 있다.")
    @Test
    void create() {
        // given
        String name = "추천메뉴";

        // when
        MenuGroupName menuName = new MenuGroupName(name);

        // then
        assertThat(menuName.getValue()).isEqualTo(name);
    }

    @DisplayName("이름 없이 메뉴 그룹 이름을 생성 시, 예외가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void createWithNullAndEmptyName(String name) {
        // when then
        assertThatThrownBy(() -> new MenuGroupName(name))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(MenuGroupName.ERROR_MESSAGE_EMPTY);
    }
}
