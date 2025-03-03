package kitchenpos.menus.tobe.domain.menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import kitchenpos.mock.client.FakePurgomalumClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

class MenuNameTest {

    private final FakePurgomalumClient purgomalumClient = new FakePurgomalumClient();

    @DisplayName("이름으로 메뉴 이름을 생성할 수 있다.")
    @Test
    void create() {
        // given
        String name = "반반치킨";

        // when
        MenuName menuName = new MenuName(name, purgomalumClient);

        // then
        assertThat(menuName.getValue()).isEqualTo(name);
    }

    @DisplayName("비속어가 포함된 이름으로 메뉴 이름을 생성 시, 예외가 발생한다.")
    @Test
    void createWithBadName() {
        // given
        String name = "bad 치킨";

        // when then
        assertThatThrownBy(() -> new MenuName(name, purgomalumClient))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(MenuName.ERROR_MESSAGE_PROFANITY);
    }

    @DisplayName("이름 없이 메뉴 이름을 생성 시, 예외가 발생한다.")
    @ParameterizedTest
    @NullSource
    @EmptySource
    void createWithNullName(String name) {
        // when then
        assertThatThrownBy(() -> new MenuName(name, purgomalumClient))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(MenuName.ERROR_MESSAGE_EMPTY);
    }
}
