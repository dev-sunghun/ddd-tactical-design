package kitchenpos.menus.tobe.infra.persistence.menu;

import java.util.UUID;
import kitchenpos.menus.tobe.domain.menu.Menu;
import kitchenpos.menus.tobe.domain.menu.TobeMenuRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TobeJpaMenuRepository extends TobeMenuRepository, JpaRepository<Menu, UUID> {

}
