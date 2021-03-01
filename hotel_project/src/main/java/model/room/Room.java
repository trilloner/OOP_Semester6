package model.room;

public class Room {
    private Long id;
    private Integer price;
    private Integer numberOfSeats;
    private String apartment;
    private String state;
    private String name;

    public Room(Long id, Integer price, Integer numberOfSeats, String apartment, String state, String name) {
        this.id = id;
        this.price = price;
        this.numberOfSeats = numberOfSeats;
        this.apartment = apartment;
        this.state = state;
        this.name = name;
    }

    public Room() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
