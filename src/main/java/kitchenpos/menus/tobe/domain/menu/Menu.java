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
import kitchenpos.menus.tobe.domain.ohs.ProductPriceService;
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
        MenuGroup menuGroup, boolean displayed, List<MenuProduct> menuProducts,
        ProductPriceService productPriceService) {
        this.id = new MenuId(id);
        this.name = new MenuName(name, purgomalumClient);
        this.menuGroup = menuGroup;
        this.displayed = displayed;
        validatePrice(price, menuProducts, productPriceService);
        this.price = new MenuPrice(price);
        this.menuProducts = new MenuProducts(menuProducts);
    }

    protected Menu() {
    }

    private void validatePrice(BigDecimal menuPrice, List<MenuProduct> menuProducts,
        ProductPriceService productPriceService) {
        BigDecimal sum = menuProducts.stream()
            .map(menuProduct -> menuProduct.calculatePrice(productPriceService))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (menuPrice.compareTo(sum) > 0) {
            throw new IllegalArgumentException();
        }
    }

    private void validatePrice(ProductPriceService productPriceService) {
        validatePrice(this.price.getValue(), this.menuProducts.getValue(), productPriceService);
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

    public void changePrice(final BigDecimal price, ProductPriceService productPriceService) {
        validatePrice(price, this.menuProducts.getValue(), productPriceService);
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

    private boolean isMenuPriceValid(ProductPriceService productPriceService) {
        return
            price.getValue().compareTo(menuProducts.calculateTotalProductPrice(
                productPriceService))
                <= 0;
    }

    public void updateDisplayStatus(ProductPriceService productPriceService) {
        this.displayed = isMenuPriceValid(productPriceService);
    }

    public void display(ProductPriceService productPriceService) {
        validatePrice(productPriceService);
        this.displayed = true;
    }

    public void hide() {
        this.displayed = false;
    }
}
