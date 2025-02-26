package kitchenpos.products.tobe.domain;

import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class ProductPrice {

    public static final String ERROR_MESSAGE_PRICE = "상품 가격은 0원 이상 이어야 합니다.";
    public static final int MINIMUM_PRICE = 0;

    private BigDecimal price;

    public ProductPrice(final BigDecimal price) {
        validate(price);
        this.price = price;
    }

    public ProductPrice() {

    }

    private static void validate(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) < MINIMUM_PRICE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PRICE);
        }
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        ProductPrice that = (ProductPrice) object;
        return Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }
}
