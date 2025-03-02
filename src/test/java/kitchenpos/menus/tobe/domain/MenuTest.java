package kitchenpos.menus.tobe.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import kitchenpos.menus.tobe.domain.menu.Menu;
import kitchenpos.mock.fixture.MenuFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {

    @DisplayName("메뉴를 생성할 수 있다.")
    @Test
    void create() {
        // given
        final String name = "반반치킨";
        final BigDecimal price = new BigDecimal(100);

        // when
        Menu menu = MenuFixture.create(name, price);

        // then
        assertThat(menu.getName()).isEqualTo(name);
        assertThat(menu.getPrice()).isEqualTo(price);
    }
}
