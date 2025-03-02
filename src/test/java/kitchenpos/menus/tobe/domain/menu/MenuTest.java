package kitchenpos.menus.tobe.domain.menu;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import kitchenpos.menus.tobe.domain.menugroup.MenuGroup;
import kitchenpos.menus.tobe.domain.menuproduct.MenuProduct;
import kitchenpos.mock.adapter.FakeProductPriceAdapter;
import kitchenpos.mock.client.FakePurgomalumClient;
import kitchenpos.mock.fixture.MenuGroupFixture;
import kitchenpos.mock.fixture.MenuProductFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {

    FakePurgomalumClient purgomalumClient = new FakePurgomalumClient();
    FakeProductPriceAdapter productPriceAdapter = new FakeProductPriceAdapter(null);

    @DisplayName("메뉴를 생성할 수 있다.")
    @Test
    void create() {
        // given
        final String name = "반반치킨";
        final BigDecimal price = new BigDecimal(100);
        final boolean displayed = true;
        final MenuGroup menuGroup = MenuGroupFixture.create();
        final List<MenuProduct> menuProducts = List.of(
            MenuProductFixture.create(UUID.randomUUID(), 1L));

        // when
        Menu menu = new Menu(UUID.randomUUID(), name, purgomalumClient, price, menuGroup, displayed,
            menuProducts, productPriceAdapter);

        // then
        assertThat(menu.getName()).isEqualTo(name);
        assertThat(menu.getPrice()).isEqualTo(price);
        assertThat(menu.isDisplayed()).isEqualTo(displayed);
    }
}
