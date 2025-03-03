package kitchenpos.menus.tobe.infra.persistence.menugroup;

import java.util.UUID;
import kitchenpos.menus.tobe.domain.menugroup.MenuGroup;
import kitchenpos.menus.tobe.domain.menugroup.TobeMenuGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TobeJpaMenuGroupRepository extends TobeMenuGroupRepository,
    JpaRepository<MenuGroup, UUID> {

}
