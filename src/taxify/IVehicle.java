package taxify;

public interface IVehicle extends IMovable {

    public int getId();
    public ILocation getLocation();
    public ILocation getDestination();
    public IService getService();
    public IStatistics getStatistics();
    public boolean getSilent();
    public void setCompany(ITaxiCompany company);
    public void pickService(IService service, boolean silent);
    public void startService();
    public void endService();
    public void notifyArrivalAtPickupLocation();
    public void notifyArrivalAtDropoffLocation();
    public boolean isFree();
    public int calculateCost();
    public String toString();
    
}