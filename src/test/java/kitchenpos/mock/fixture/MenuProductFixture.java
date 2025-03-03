package kitchenpos.mock.fixture;


import java.util.UUID;
import kitchenpos.menus.tobe.domain.menuproduct.MenuProduct;

public class MenuProductFixture {

    public static MenuProduct create(final UUID productId, final long quantity) {
        return new MenuProduct(productId, quantity);
    }

}
