package kitchenpos.menus.tobe.domain;

import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class MenuProductQuantity {

    public static final String ERROR_MESSAGE_QUANTITY = "수량은 0개 이상 이어야 합니다.";
    public static final int MINIMUM_QUANTITY = 0;

    private long value;

    public MenuProductQuantity(final Long value) {
        validate(value);
        this.value = value;
    }

    protected MenuProductQuantity() {

    }

    private static void validate(Long value) {
        if (value == null || value < MINIMUM_QUANTITY) {
            throw new IllegalArgumentException(ERROR_MESSAGE_QUANTITY);
        }
    }

    public long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        MenuProductQuantity that = (MenuProductQuantity) object;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
