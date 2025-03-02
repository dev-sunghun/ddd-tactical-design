package kitchenpos.menus.tobe.application.menugroup;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import kitchenpos.menus.tobe.domain.menugroup.MenuGroup;
import kitchenpos.menus.tobe.domain.menugroup.TobeMenuGroupRepository;
import kitchenpos.menus.tobe.dto.menugroup.MenuGroupRequest;
import kitchenpos.menus.tobe.dto.menugroup.MenuGroupResponse;
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
    public MenuGroup getById(UUID menuGroupId) {
        return menuGroupRepository.findById(menuGroupId).orElseThrow(
            () -> new NoSuchElementException("MenuGroup not found with id: " + menuGroupId));
    }

    @Transactional(readOnly = true)
    public List<MenuGroup> findAll() {
        return menuGroupRepository.findAll();
    }
}
