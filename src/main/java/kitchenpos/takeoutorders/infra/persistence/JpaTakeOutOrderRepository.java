package kitchenpos.takeoutorders.infra.persistence;

import java.util.UUID;
import kitchenpos.takeoutorders.domain.TakeOutOrder;
import kitchenpos.takeoutorders.domain.TakeOutOrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTakeOutOrderRepository extends TakeOutOrderRepository,
    JpaRepository<TakeOutOrder, UUID> {

}
