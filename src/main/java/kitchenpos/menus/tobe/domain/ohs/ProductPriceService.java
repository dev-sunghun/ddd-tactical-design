package kitchenpos.menus.tobe.domain.ohs;

import java.math.BigDecimal;
import java.util.UUID;

// OHS(Open Host Service)
// Product는 제공자(업스트림), Menu는 사용자(다운스트림)이다.
// 서비스 제공자 Product는 사용자 Menu의 요건에 최적화된 공표된 언어(published language)를 구현한다.

public interface ProductPriceService {

    BigDecimal getPriceByProductId(UUID productId);
}
