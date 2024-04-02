package taxify;

public class Service implements IService {
    private IUser user;
    private ILocation pickup;
    private ILocation dropoff;
    private int stars;
    private boolean silent;
    private boolean pink;
    
    public Service(IUser user, ILocation pickup, ILocation dropoff, boolean silent, boolean pink) {
        this.user = user;
        this.pickup = pickup;
        this.dropoff = dropoff; 
        this.stars = 0;
        this.silent = silent;
        this.pink = pink;
    }
    
    @Override
    public IUser getUser() {
        return this.user;
    }
    
    @Override
    public ILocation getPickupLocation() {
        return this.pickup;
    }
    
    @Override
    public ILocation getDropoffLocation() {
        return this.dropoff;
    }

    @Override
    public boolean getSilent() {
        return this.silent;
    }

    @Override
    public boolean getPink() {
        return this.pink;
    }
    
    @Override
    public int getStars() {
        return this.stars;
    }
    
    @Override
    public void setStars(int stars) {
        this.stars = stars;
    }
    
    @Override
    public int calculateDistance() {
        return Math.abs(this.pickup.getX() - this.dropoff.getX()) + Math.abs(this.pickup.getY() - this.dropoff.getY());
    }
    
    @Override
    public String toString() {
        return this.getPickupLocation().toString() + " to " + this.getDropoffLocation().toString();
    }
}
