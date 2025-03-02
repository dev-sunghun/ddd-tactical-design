package kitchenpos.mock.fixture;

import java.util.UUID;
import kitchenpos.menus.tobe.domain.menugroup.MenuGroup;

public class MenuGroupFixture {

    public static final String MENU_GROUP_NAME = "추천메뉴";


    public static MenuGroup create() {
        return new MenuGroup(UUID.randomUUID(), MENU_GROUP_NAME);
    }
}
