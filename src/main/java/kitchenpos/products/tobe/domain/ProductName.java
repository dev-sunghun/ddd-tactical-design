package kitchenpos.products.tobe.domain;

import jakarta.persistence.Embeddable;
import java.util.Objects;
import kitchenpos.shared.client.PurgomalumClient;

@Embeddable
public class ProductName {

    public static final String ERROR_MESSAGE_EMPTY = "상품 이름이 공백입니다.";
    public static final String ERROR_MESSAGE_PROFANITY = "상품 이름에 비속어가 포함되어 있습니다.";

    private String value;

    public ProductName(final String value, final PurgomalumClient purgomalumClient) {
        validate(value, purgomalumClient);

        this.value = value;
    }

    public ProductName() {

    }

    private static void validate(String value, PurgomalumClient purgomalumClient) {
        if (Objects.isNull(value) || value.isEmpty()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_EMPTY);
        }
        if (purgomalumClient.containsProfanity(value)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PROFANITY);
        }
    }

    public String getValue() {
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
        ProductName that = (ProductName) object;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
