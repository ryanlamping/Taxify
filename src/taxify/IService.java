package taxify;

public interface IService {

    public IUser getUser();
    public ILocation getPickupLocation();
    public ILocation getDropoffLocation();
    public boolean getSilent();
    public boolean getPink();
    public int getStars();
    public void setPickupLocation(ILocation origin);
    public void setDropOffLocation(ILocation destination);
    public void setShare(boolean share);
    public void setStars(int stars);
    public int calculateDistance();
    public String toString();
    
}
