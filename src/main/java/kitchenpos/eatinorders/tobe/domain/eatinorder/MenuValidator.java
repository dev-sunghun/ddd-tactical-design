package kitchenpos.eatinorders.tobe.domain.eatinorder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface MenuValidator {

    void validateCount(List<UUID> menuIds, int count);

    void validateDisplayedAndPrice(UUID menuId, BigDecimal price);
}
