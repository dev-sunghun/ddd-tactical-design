package kitchenpos.menus.tobe.infra.persistence;

import java.util.UUID;
import kitchenpos.menus.tobe.domain.MenuGroup;
import kitchenpos.menus.tobe.domain.TobeMenuGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TobeJpaMenuGroupRepository extends TobeMenuGroupRepository,
    JpaRepository<MenuGroup, UUID> {

}
