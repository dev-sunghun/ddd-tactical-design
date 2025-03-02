package kitchenpos.menus.tobe.application;

import java.util.List;
import kitchenpos.menus.tobe.domain.menugroup.MenuGroup;
import kitchenpos.menus.tobe.domain.menugroup.TobeMenuGroupRepository;
import kitchenpos.menus.tobe.dto.MenuGroupRequest;
import kitchenpos.menus.tobe.dto.MenuGroupResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TobeMenuGroupService {

    private final TobeMenuGroupRepository menuGroupRepository;

    public TobeMenuGroupService(final TobeMenuGroupRepository menuGroupRepository) {
        this.menuGroupRepository = menuGroupRepository;
    }

    @Transactional
    public MenuGroupResponse create(final MenuGroupRequest request) {
        final MenuGroup menuGroup = new MenuGroup(request.id(), request.name());
        return MenuGroupResponse.from(menuGroupRepository.save(menuGroup));
    }

    @Transactional(readOnly = true)
    public List<MenuGroup> findAll() {
        return menuGroupRepository.findAll();
    }
}
