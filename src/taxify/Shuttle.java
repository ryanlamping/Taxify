package taxify;

public class Shuttle extends Vehicle {
    private static final double SHUTTLE_RATE = 1.5;

    public Shuttle(int id, ILocation location) {
        super(id, location);
    }

    @Override
    public int calculateCost() {
        return (int) (SHUTTLE_RATE * super.calculateCost());
    }
}
