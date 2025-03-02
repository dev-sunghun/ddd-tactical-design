package kitchenpos.menus.tobe.infra.acl;

import java.util.List;
import java.util.UUID;
import kitchenpos.menus.domain.Menu;
import kitchenpos.menus.domain.MenuRepository;
import kitchenpos.products.tobe.domain.MenuDisplayTranslator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

// MenuProductPort 인터페이스의 구현체
// ACL(Anti-Corruption Layer)의 일부로, 메뉴 바운디드 컨텍스트의 구현 세부사항을 캡슐화

@Component
public class MenuDisplayTranslatorImpl implements MenuDisplayTranslator {

    private final MenuRepository menuRepository;

    public MenuDisplayTranslatorImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    @Transactional
    public void updateMenusDisplayStatusByProductId(UUID productId) {
        List<Menu> menus = menuRepository.findAllByProductId(productId);
        menus.forEach(Menu::updateDisplayStatus);
    }
} 
