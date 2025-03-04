package kitchenpos.eatinorders.tobe.application.eatinorder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;
import kitchenpos.eatinorders.tobe.application.restaurant.TobeRestaurantTableService;
import kitchenpos.eatinorders.tobe.domain.eatinorder.EatInOrder;
import kitchenpos.eatinorders.tobe.domain.eatinorder.EatInOrderLineItem;
import kitchenpos.eatinorders.tobe.domain.eatinorder.EatInOrderStatus;
import kitchenpos.eatinorders.tobe.domain.eatinorder.TobeEatInOrderRepository;
import kitchenpos.eatinorders.tobe.domain.restaurant.RestaurantTable;
import kitchenpos.eatinorders.tobe.dto.eatinorder.EatInOrderCreateRequest;
import kitchenpos.eatinorders.tobe.dto.eatinorder.EatInOrderResponse;
import kitchenpos.menus.tobe.application.menu.TobeMenuService;
import kitchenpos.menus.tobe.domain.menu.Menu;
import kitchenpos.shared.domain.OrderType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TobeEatInOrderService {

    private final TobeEatInOrderRepository eatInOrderRepository;
    private final TobeMenuService menuService;
    private final TobeRestaurantTableService restaurantTableService;

    public TobeEatInOrderService(final TobeEatInOrderRepository eatInOrderRepository,
        final TobeMenuService menuService, final TobeRestaurantTableService restaurantTableService

    ) {
        this.eatInOrderRepository = eatInOrderRepository;
        this.menuService = menuService;
        this.restaurantTableService = restaurantTableService;
    }

    @Transactional(readOnly = true)
    public EatInOrder getById(UUID eatInOrderId) {
        return eatInOrderRepository.findById(eatInOrderId).orElseThrow(
            () -> new NoSuchElementException("EatInOrder not found with id: " + eatInOrderId));
    }

    @Transactional
    public EatInOrderResponse create(final EatInOrderCreateRequest request) {
        final OrderType type = request.type();
        if (Objects.isNull(type)) {
            throw new IllegalArgumentException();
        }

        final List<EatInOrderLineItem> eatInOrderLineItemRequests = request.orderLineItems();
        if (Objects.isNull(eatInOrderLineItemRequests) || eatInOrderLineItemRequests.isEmpty()) {
            throw new IllegalArgumentException();
        }

        final List<Menu> menus = menuService.getAllByIdIn(
            eatInOrderLineItemRequests.stream().map(EatInOrderLineItem::getMenuId).toList());
        if (menus.size() != eatInOrderLineItemRequests.size()) {
            throw new IllegalArgumentException();
        }

        final List<EatInOrderLineItem> eatInOrderLineItems = new ArrayList<>();
        for (final EatInOrderLineItem eatInOrderLineItemRequest : eatInOrderLineItemRequests) {
            final long quantity = eatInOrderLineItemRequest.getQuantity();
            if (type != OrderType.EAT_IN) {
                if (quantity < 0) {
                    throw new IllegalArgumentException();
                }
            }
            final Menu menu = menuService.getById(eatInOrderLineItemRequest.getMenuId());
            if (!menu.isDisplayed()) {
                throw new IllegalStateException();
            }
            if (menu.getPrice().compareTo(eatInOrderLineItemRequest.getPrice()) != 0) {
                throw new IllegalArgumentException();
            }
            final EatInOrderLineItem eatInOrderLineItem = new EatInOrderLineItem(menu.getId(),
                quantity);
            eatInOrderLineItems.add(eatInOrderLineItem);
        }

        final RestaurantTable restaurantTable = restaurantTableService.getById(
            request.orderTableId());
        if (!restaurantTable.isOccupied()) {
            throw new IllegalStateException();
        }

        EatInOrder eatInOrder = new EatInOrder(UUID.randomUUID(), type, EatInOrderStatus.WAITING,
            LocalDateTime.now(), eatInOrderLineItems, restaurantTable.getId());

        return EatInOrderResponse.from(eatInOrderRepository.save(eatInOrder));
    }

    @Transactional
    public EatInOrderResponse accept(final UUID orderId) {
        final EatInOrder eatInOrder = getById(orderId);
        if (eatInOrder.getStatus() != EatInOrderStatus.WAITING) {
            throw new IllegalStateException();
        }
        eatInOrder.changeStatus(EatInOrderStatus.ACCEPTED);
        return EatInOrderResponse.from(eatInOrder);
    }

    @Transactional
    public EatInOrderResponse serve(final UUID orderId) {
        final EatInOrder eatInOrder = getById(orderId);
        if (eatInOrder.getStatus() != EatInOrderStatus.ACCEPTED) {
            throw new IllegalStateException();
        }
        eatInOrder.changeStatus(EatInOrderStatus.SERVED);
        return EatInOrderResponse.from(eatInOrder);
    }

    @Transactional
    public EatInOrderResponse complete(final UUID orderId) {
        final EatInOrder eatInOrder = getById(orderId);
        final OrderType type = eatInOrder.getType();
        final EatInOrderStatus status = eatInOrder.getStatus();
        if (type == OrderType.TAKEOUT || type == OrderType.EAT_IN) {
            if (status != EatInOrderStatus.SERVED) {
                throw new IllegalStateException();
            }
        }
        eatInOrder.changeStatus(EatInOrderStatus.COMPLETED);
        if (!eatInOrderRepository.existsByOrderTableIdAndStatusNot(
            eatInOrder.getRestaurantTableId(), EatInOrderStatus.COMPLETED)) {
            RestaurantTable restaurantTable = restaurantTableService.getById(
                eatInOrder.getRestaurantTableId());
            restaurantTable.clear();
        }
        return EatInOrderResponse.from(eatInOrder);
    }

    @Transactional(readOnly = true)
    public List<EatInOrder> findAll() {
        return eatInOrderRepository.findAll();
    }
}
