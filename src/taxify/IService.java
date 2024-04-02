package taxify;

public interface IService {

    public IUser getUser();
    public ILocation getPickupLocation();
    public ILocation getDropoffLocation();
    public boolean getSilent();
    public boolean getPink();
    public int getStars();
    public void setStars(int stars);
    public int calculateDistance();
    public String toString();
    
}
