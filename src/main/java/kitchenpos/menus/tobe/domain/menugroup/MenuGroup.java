package kitchenpos.menus.tobe.domain.menugroup;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.UUID;

@Table(name = "menu_group")
@Entity(name = "TobeMenuGroup")
public class MenuGroup {

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "id"))
    private MenuGroupId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "name"))
    private MenuGroupName name;

    public MenuGroup(UUID id, String name) {
        this.id = new MenuGroupId(id);
        this.name = new MenuGroupName(name);
    }

    protected MenuGroup() {
    }


    public UUID getId() {
        return id.getValue();
    }

    public String getName() {
        return name.getValue();
    }
}
