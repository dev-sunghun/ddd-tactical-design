package kitchenpos.menus.tobe.domain.menugroup;

import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class MenuGroupName {

    public static final String ERROR_MESSAGE_EMPTY = "상품 이름이 공백입니다.";

    private String value;

    public MenuGroupName(final String value) {
        validate(value);

        this.value = value;
    }

    protected MenuGroupName() {

    }

    private static void validate(String value) {
        if (Objects.isNull(value) || value.isEmpty()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_EMPTY);
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
        MenuGroupName that = (MenuGroupName) object;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
