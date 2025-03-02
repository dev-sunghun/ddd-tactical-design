package kitchenpos.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kitchenpos.menus.tobe.domain.menu.Menu;
import kitchenpos.menus.tobe.domain.menugroup.MenuGroup;
import kitchenpos.menus.tobe.domain.menuproduct.MenuProduct;
import kitchenpos.menus.tobe.dto.menu.MenuProductRequest;
import kitchenpos.mock.adapter.FakeProductPriceAdapter;
import kitchenpos.mock.fixture.MenuFixture;
import kitchenpos.mock.fixture.MenuGroupFixture;
import kitchenpos.mock.fixture.MenuProductFixture;
import kitchenpos.mock.fixture.ProductFixture;
import kitchenpos.mock.persistence.FakeMenuGroupRepository;
import kitchenpos.mock.persistence.FakeMenuRepository;
import kitchenpos.mock.persistence.FakeProductRepository;
import kitchenpos.products.tobe.domain.Product;

public class TestUtil {

    public static final BigDecimal PRICE = BigDecimal.valueOf(28_000);
    public static final BigDecimal MINUS_PRICE = BigDecimal.valueOf(-100);
    public static final MenuGroup DEFAULT_MENU_GROUP = MenuGroupFixture.create();


    private final FakeProductRepository productRepository;
    private final FakeMenuRepository menuRepository;
    private final FakeProductPriceAdapter productPriceAdapter;
    private final MenuGroup defaultMenuGroup;

    public TestUtil(FakeMenuGroupRepository menuGroupRepository,
        FakeProductRepository productRepository, FakeMenuRepository menuRepository,
        FakeProductPriceAdapter productPriceAdapter) {
        this.productRepository = productRepository;
        this.defaultMenuGroup = menuGroupRepository.save(DEFAULT_MENU_GROUP);
        this.menuRepository = menuRepository;
        this.productPriceAdapter = productPriceAdapter;
    }

    public MenuGroup getSavedMenuGroup() {
        return defaultMenuGroup;
    }

    public UUID getSavedMenuGroupId() {
        return defaultMenuGroup.getId();
    }

    public Product getSavedProduct(String name, BigDecimal price) {
        return productRepository.save(ProductFixture.create(name, price));
    }

    public Product getSavedProduct() {
        return getSavedProduct(PRICE);
    }

    public Product getSavedProduct(BigDecimal price) {
        String name = "치킨";
        return productRepository.save(ProductFixture.create(name, price));
    }

    public List<MenuProduct> getMenuProducts(int quantity) {

        Product product = getSavedProduct();
        MenuProduct menuProduct = MenuProductFixture.create(product.getId(), quantity);
        return List.of(menuProduct);
    }

    public List<MenuProductRequest> getMenuProductRequests(int quantity) {
        return getMenuProducts(quantity).stream()
            .map(x -> new MenuProductRequest(x.getProductId(), x.getQuantity())).toList();
    }

    public List<MenuProduct> getMenuProducts(UUID productId, int quantity) {
        List<MenuProduct> menuProducts = new ArrayList<>();
        menuProducts.add(MenuProductFixture.create(productId, quantity));
        return menuProducts;
    }

    public Menu getSavedMenu() {
        int quantity = 1;
        Product savedProduct = getSavedProduct(PRICE);
        MenuProduct menuProduct = MenuProductFixture.create(savedProduct.getId(), quantity);
        Menu menu = MenuFixture.create("두마리 치킨", PRICE, getSavedMenuGroup(), List.of(menuProduct), productPriceAdapter);
        return menuRepository.save(menu);
    }

    public Menu getSavedMenu(Menu menu) {
        return menuRepository.save(menu);
    }
}
