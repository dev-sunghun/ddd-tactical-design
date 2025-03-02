package kitchenpos.menus.tobe.dto.menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kitchenpos.menus.tobe.domain.menu.Menu;
import kitchenpos.menus.tobe.domain.menu.ProductPriceClient;
import kitchenpos.menus.tobe.domain.menugroup.MenuGroup;
import kitchenpos.menus.tobe.domain.menuproduct.MenuProduct;
import kitchenpos.shared.client.PurgomalumClient;

public record MenuCreateRequest(String name, BigDecimal price, UUID menuGroupId,
                                List<MenuProductRequest> menuProducts, boolean displayed) {

    public Menu toEntityWith(final PurgomalumClient purgomalumClient, final MenuGroup menuGroup,
        final ProductPriceClient productPriceClient) {

        List<MenuProduct> menuProductList = toMenuProducts(menuProducts);

        return new Menu(UUID.randomUUID(), name, purgomalumClient, price, menuGroup, displayed,
            menuProductList, productPriceClient);
    }

    private List<MenuProduct> toMenuProducts(final List<MenuProductRequest> menuProducts) {

        if (menuProducts == null) {
            return new ArrayList<>();
        }
        return menuProducts.stream().map(
            MenuProductRequest::toMenuProduct).toList();
    }
}
