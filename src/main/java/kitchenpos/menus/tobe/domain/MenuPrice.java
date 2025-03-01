package kitchenpos.menus.tobe.domain;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class MenuPrice {

    public static final String ERROR_MESSAGE_PRICE = "상품 가격은 0원 이상 이어야 합니다.";
    public static final int MINIMUM_PRICE = 0;

    private BigDecimal value;

    public MenuPrice(final BigDecimal value) {
        validate(value);
        this.value = value;
    }

    protected MenuPrice() {

    }

    private static void validate(BigDecimal value) {
        if (value == null || value.compareTo(BigDecimal.ZERO) < MINIMUM_PRICE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PRICE);
        }
    }

    public BigDecimal getValue() {
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
        MenuPrice that = (MenuPrice) object;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
