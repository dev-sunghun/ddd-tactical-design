package kitchenpos.menus.tobe.domain.menu;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import kitchenpos.menus.tobe.domain.menugroup.MenuGroup;
import kitchenpos.menus.tobe.domain.menuproduct.MenuProduct;
import kitchenpos.menus.tobe.domain.menuproduct.MenuProducts;
import kitchenpos.shared.client.PurgomalumClient;

@Table(name = "menu")
@Entity(name = "TobeMenu")
public class Menu {

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "id"))
    private MenuId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "name"))
    private MenuName name;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "price"))
    private MenuPrice price;

    @ManyToOne(optional = false)
    @JoinColumn(
        name = "menu_group_id",
        columnDefinition = "binary(16)",
        foreignKey = @ForeignKey(name = "fk_menu_to_menu_group")
    )
    private MenuGroup menuGroup;

    @Column(name = "displayed", nullable = false)
    private boolean displayed;

    @Embedded
    private MenuProducts menuProducts;

    public Menu(UUID id, String name, final PurgomalumClient purgomalumClient, BigDecimal price,
        MenuGroup menuGroup, boolean displayed, List<MenuProduct> menuProducts) {
        this.id = new MenuId(id);
        this.name = new MenuName(name, purgomalumClient);
        this.price = new MenuPrice(price);
        this.menuGroup = menuGroup;
        this.displayed = displayed;
        this.menuProducts = new MenuProducts(menuProducts);
    }

    protected Menu() {
    }

    public UUID getId() {
        return id.getValue();
    }

    public String getName() {
        return name.getValue();
    }

    public BigDecimal getPrice() {
        return price.getValue();
    }

    public void changePrice(final BigDecimal price) {
        this.price = new MenuPrice(price);
    }

    public MenuGroup getMenuGroup() {
        return menuGroup;
    }

    public boolean isDisplayed() {
        return displayed;
    }

    public List<MenuProduct> getMenuProducts() {
        return menuProducts.getValue();
    }

    public UUID getMenuGroupId() {
        return getMenuGroup().getId();
    }

    private BigDecimal calculateTotalProductPrice() {
        return menuProducts.getValue().stream()
            .map(MenuProduct::calculatePrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private boolean isMenuPriceValid() {
        return price.getValue().compareTo(calculateTotalProductPrice()) <= 0;
    }

    public void updateDisplayStatus() {
        this.displayed = isMenuPriceValid();
    }
}
