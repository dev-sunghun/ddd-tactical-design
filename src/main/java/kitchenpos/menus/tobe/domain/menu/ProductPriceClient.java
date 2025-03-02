package kitchenpos.menus.tobe.domain.menu;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductPriceClient {

    BigDecimal getPriceByProductId(UUID productId);
}
