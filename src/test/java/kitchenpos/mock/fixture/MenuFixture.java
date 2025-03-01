package kitchenpos.mock.fixture;

import java.math.BigDecimal;
import java.util.UUID;
import kitchenpos.menus.tobe.domain.Menu;
import kitchenpos.menus.tobe.domain.MenuGroup;
import kitchenpos.mock.client.FakePurgomalumClient;

public class MenuFixture {

    public static final String MENU_GROUP_NAME = "추천메뉴";
    private static final FakePurgomalumClient CLIENT = new FakePurgomalumClient();

    public static Menu create(final String name, final BigDecimal price) {
        return new Menu(UUID.randomUUID(), name, CLIENT, price, createMenuGroup(), true, null);
    }

    public static MenuGroup createMenuGroup() {
        return new MenuGroup(UUID.randomUUID(), MENU_GROUP_NAME);
    }
}
