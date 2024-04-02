package taxify;

public class Taxi extends Vehicle {
    private static final double TAXI_RATE = 2.0;

    public Taxi(int id, ILocation location) {
        super(id, location);
    }

    @Override
    public int calculateCost() {
        return (int) (TAXI_RATE * super.calculateCost());
    }
}
