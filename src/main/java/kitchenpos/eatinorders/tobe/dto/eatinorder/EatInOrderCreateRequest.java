package kitchenpos.eatinorders.tobe.dto.eatinorder;

import java.util.List;
import java.util.UUID;
import kitchenpos.eatinorders.tobe.domain.eatinorder.EatInOrderLineItem;
import kitchenpos.shared.domain.OrderType;

public record EatInOrderCreateRequest(OrderType type, List<EatInOrderLineItem> orderLineItems,
                                      UUID orderTableId) {

}
