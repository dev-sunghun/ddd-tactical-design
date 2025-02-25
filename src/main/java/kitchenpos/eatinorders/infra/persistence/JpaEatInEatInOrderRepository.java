package kitchenpos.eatinorders.infra.persistence;

import java.util.UUID;
import kitchenpos.eatinorders.domain.EatInOrder;
import kitchenpos.eatinorders.domain.EatInOrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEatInEatInOrderRepository extends EatInOrderRepository, JpaRepository<EatInOrder, UUID> {

}
