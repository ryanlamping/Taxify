package taxify;

public class Bike extends MicroVehicle{
    private static final double MICRO_RATE = 0.50;

    public Scooter(int id, ILocation location){
        super(id, location);
    }

    @Override
    public int calculateCost(){
        return (int) (MICRO_RATE * super.calculateCost());
    }
}
