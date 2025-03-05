package kitchenpos.eatinorders.tobe.infra.client;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import kitchenpos.eatinorders.tobe.domain.eatinorder.MenuValidator;
import kitchenpos.menus.tobe.domain.menu.Menu;
import kitchenpos.menus.tobe.domain.menu.TobeMenuRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MenuValidatorImpl implements MenuValidator {

    private final TobeMenuRepository menuRepository;

    public MenuValidatorImpl(TobeMenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Transactional(readOnly = true)
    public Menu getById(UUID menuId) {
        return menuRepository.findById(menuId)
            .orElseThrow(() -> new NoSuchElementException("Menu not found with id: " + menuId));
    }

    @Transactional(readOnly = true)
    public void validateCount(List<UUID> menuIds, int count) {
        int menuCount = menuRepository.countAllByIdIn(menuIds);
        if (menuCount != count) {
            throw new IllegalArgumentException();
        }
    }

    @Transactional(readOnly = true)
    public void validateDisplayedAndPrice(UUID menuId, BigDecimal price) {
        Menu menu = getById(menuId);
        if (!menu.isDisplayed()) {
            throw new IllegalStateException();
        }
        if (menu.getPrice().compareTo(price) != 0) {
            throw new IllegalArgumentException();
        }
    }
}
