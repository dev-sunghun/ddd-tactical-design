package kitchenpos.mock.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import kitchenpos.menus.tobe.domain.menugroup.MenuGroup;
import kitchenpos.menus.tobe.domain.menugroup.TobeMenuGroupRepository;


public class FakeMenuGroupRepository implements TobeMenuGroupRepository {

    private final Map<UUID, MenuGroup> dataMap = new ConcurrentHashMap<>();

    @Override
    public MenuGroup save(MenuGroup menuGroup) {
        if (menuGroup.getId() == null) {
            UUID id = UUID.randomUUID();
            MenuGroup newMenuGroup = new MenuGroup(UUID.randomUUID(), menuGroup.getName());
            dataMap.put(id, newMenuGroup);
            return menuGroup;
        }
        UUID id = menuGroup.getId();
        dataMap.put(id, menuGroup);
        return menuGroup;
    }

    @Override
    public Optional<MenuGroup> findById(UUID id) {
        return dataMap.values()
            .stream()
            .filter(menu -> menu.getId().equals(id))
            .findFirst();
    }

    @Override
    public List<MenuGroup> findAll() {
        return new ArrayList<>(dataMap.values());
    }
}
