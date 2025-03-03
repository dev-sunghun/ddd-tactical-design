package kitchenpos.menus.tobe.dto.menugroup;

import java.util.UUID;
import kitchenpos.menus.tobe.domain.menugroup.MenuGroup;

public record MenuGroupResponse(UUID id, String name) {

    public static MenuGroupResponse from(MenuGroup menuGroup) {
        return new MenuGroupResponse(menuGroup.getId(), menuGroup.getName());
    }
}
