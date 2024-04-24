package taxify;

public class Scooter extends MicroVehicle{
    private static final double MICRO_RATE = 0.75;

    public Scooter(int id, ILocation location){
        super(id, location);
    }

    @Override
    public int calculateCost(){
        return (int) (MICRO_RATE * super.calculateCost());
    }
}



