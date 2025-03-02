package kitchenpos.menus.tobe.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import kitchenpos.menus.tobe.domain.menugroup.MenuGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuGroupTest {

    @DisplayName("메뉴 그룹을 생성할 수 있다.")
    @Test
    void create() {
        // given
        final String name = "추천메뉴";
        final UUID id = UUID.randomUUID();
        // when
        MenuGroup menuGroup = new MenuGroup(id, name);

        // then
        assertThat(menuGroup.getName()).isEqualTo(name);
        assertThat(menuGroup.getId()).isEqualTo(id);
    }
}
