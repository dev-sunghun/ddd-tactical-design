package kitchenpos.menus.tobe.infra.acl;

import java.math.BigDecimal;
import java.util.UUID;
import kitchenpos.annotations.AntiCorruptionLayer;
import kitchenpos.menus.tobe.domain.ProductPriceService;
import kitchenpos.products.tobe.infra.ohs.ProductPriceServiceImpl;
import kitchenpos.shared.domain.ProductPrice;
import org.springframework.transaction.annotation.Transactional;

@AntiCorruptionLayer(
    description = "상품 가격을 처리하는 ACL",
    upstreamContext = "ProductsContext",
    downstreamContext = "ProductsContext", translationType = "MODEL_TRANSLATION")
public class ProductPriceClient implements ProductPriceService {

    /*
     * ProductPriceClient의 역할
     * 현재는 같은 패키지 내에 있기에 @Bean을 이용한 참조 방식이다.
     * 분산 서버 였으면, API, Event Listener로 dto or event를 받고, getPriceByProductId(UUID productId)에서 BigDecimal로 변환(translate)한다.
     * */
    private final ProductPriceServiceImpl productPriceService;

    public ProductPriceClient(ProductPriceServiceImpl productPriceService) {
        this.productPriceService = productPriceService;
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getPriceByProductId(UUID productId) {
        final ProductPrice price = productPriceService.getPriceByProductId(productId);
        return price.getValue();
    }
}
