package kitchenpos.eatinorders.tobe.domain.eatinorder;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import kitchenpos.eatinorders.tobe.domain.restaurant.RestaurantTableId;
import kitchenpos.shared.domain.OrderType;

@Table(name = "orders")
@Entity(name = "TobeEatInOrder")
public class EatInOrder {

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "id"))
    private EatInOrderId id;

    @Column(name = "type", nullable = false, columnDefinition = "varchar(255)")
    @Enumerated(EnumType.STRING)
    private OrderType type = OrderType.EAT_IN;

    @Column(name = "status", nullable = false, columnDefinition = "varchar(255)")
    @Enumerated(EnumType.STRING)
    private EatInOrderStatus status;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "order_date_time"))
    private EatInOrderDateTime orderDateTime;

    @Embedded
    private EatInOrderLineItems eatInOrderLineItems;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "order_table_id"))
    private RestaurantTableId restaurantTableId;

    public EatInOrder(UUID id, OrderType type, EatInOrderStatus status, LocalDateTime orderDateTime,
        List<EatInOrderLineItem> eatInOrderLineItems, UUID restaurantTableId) {
        this.id = new EatInOrderId(id);
        this.type = type;
        this.status = status;
        this.orderDateTime = new EatInOrderDateTime(orderDateTime);
        this.eatInOrderLineItems = new EatInOrderLineItems(eatInOrderLineItems);
        this.restaurantTableId = new RestaurantTableId(restaurantTableId);
    }

    protected EatInOrder() {
    }

    public UUID getId() {
        return id.getValue();
    }

    public OrderType getType() {
        return type;
    }

    public EatInOrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime.getValue();
    }

    public List<EatInOrderLineItem> getOrderLineItems() {
        return eatInOrderLineItems.getValue();
    }

    public UUID getRestaurantTableId() {
        return restaurantTableId.getValue();
    }

    public void changeStatus(EatInOrderStatus status) {
        this.status = status;
    }
}
