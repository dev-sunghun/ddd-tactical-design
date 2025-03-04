package kitchenpos.eatinorders.tobe.domain.eatinorder;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;
import kitchenpos.menus.tobe.domain.menu.MenuId;

@Table(name = "order_line_item")
@Entity(name = "TobeEatInOrderLineItem")
public class EatInOrderLineItem {

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "seq"))
    private EatInOrderLineItemSeq seq;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "menu_id"))
    private MenuId menuId;


    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "quantity"))
    private EatInOrderLineItemQuantity quantity;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "price"))
    private EatInOrderLineItemPrice price;

    public EatInOrderLineItem(Long seq, long quantity, UUID menuId, BigDecimal price) {
        this.seq = new EatInOrderLineItemSeq(seq);
        this.quantity = new EatInOrderLineItemQuantity(quantity);
        this.menuId = new MenuId(menuId);
        this.price = new EatInOrderLineItemPrice(price);
    }

    public EatInOrderLineItem(UUID menuId, long quantity) {
        this.menuId = new MenuId(menuId);
        this.quantity = new EatInOrderLineItemQuantity(quantity);
    }

    protected EatInOrderLineItem() {
    }

    public Long getSeq() {
        return seq.getValue();
    }


    public long getQuantity() {
        return quantity.getValue();
    }

    public UUID getMenuId() {
        return menuId.getValue();
    }

    public BigDecimal getPrice() {
        return price.getValue();
    }
}
