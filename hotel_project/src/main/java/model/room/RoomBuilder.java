package model.room;

public class RoomBuilder {
    private Long id;
    private Integer price;
    private Integer numberOfSeats;
    private String apartment;
    private String state;
    private String name;

    public RoomBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public RoomBuilder setPrice(Integer price) {
        this.price = price;
        return this;

    }

    public RoomBuilder setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
        return this;

    }

    public RoomBuilder setApartment(String apartment) {
        this.apartment = apartment;
        return this;

    }

    public RoomBuilder setState(String state) {
        this.state = state;
        return this;

    }

    public RoomBuilder setName(String name) {
        this.name = name;
        return this;

    }

    public Room build() {
        return new Room(id, price, numberOfSeats, apartment, state, name);
    }
}
