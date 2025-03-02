package kitchenpos.menus.tobe.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import kitchenpos.products.tobe.domain.Product;

@Table(name = "menu_product")
@Entity(name = "TobeMenuProduct")
public class MenuProduct {

    @Column(name = "seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long seq;

    @ManyToOne(optional = false)
    @JoinColumn(
        name = "product_id",
        columnDefinition = "binary(16)",
        foreignKey = @ForeignKey(name = "fk_menu_product_to_product")
    )
    private Product product;


    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "quantity", nullable = false))
    private MenuProductQuantity quantity;

    public MenuProduct(Long seq, Product product, MenuProductQuantity quantity) {
        this.seq = seq;
        this.product = product;
        this.quantity = quantity;
    }

    protected MenuProduct() {
    }

    public Long getSeq() {
        return seq;
    }


    public long getQuantity() {
        return quantity.getValue();
    }


    public Product getProduct() {
        return product;
    }

    public BigDecimal calculatePrice() {
        return getProduct()
            .getPrice()
            .multiply(BigDecimal.valueOf(getQuantity()));
    }
}
