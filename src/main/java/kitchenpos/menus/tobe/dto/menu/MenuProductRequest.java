package kitchenpos.menus.tobe.dto.menu;

import java.util.UUID;
import kitchenpos.menus.tobe.domain.menuproduct.MenuProduct;

public record MenuProductRequest(UUID productId, long quantity) {

    public MenuProduct toMenuProduct() {
        return new MenuProduct(productId, quantity);
    }
}
