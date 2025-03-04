package kitchenpos.eatinorders.tobe.domain.restaurant;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.UUID;

@Table(name = "order_table")
@Entity(name = "RestaurantTable")
public class RestaurantTable {

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "id"))
    private RestaurantTableId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "name"))
    private RestaurantTableName name;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "number_of_guests"))
    private RestaurantTableNumberOfGuests numberOfGuests;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "occupied"))
    private RestaurantTableOccupied occupied;

    public RestaurantTable(UUID id, String name, int numberOfGuests, boolean occupied) {
        this.id = new RestaurantTableId(id);
        this.name = new RestaurantTableName(name);
        this.numberOfGuests = new RestaurantTableNumberOfGuests(numberOfGuests);
        this.occupied = new RestaurantTableOccupied(occupied);
    }

    protected RestaurantTable() {
    }

    public UUID getId() {
        return id.getValue();
    }

    public String getName() {
        return name.getValue();
    }

    public int getNumberOfGuests() {
        return numberOfGuests.getValue();
    }

    public boolean isOccupied() {
        return occupied.getValue();
    }
}
