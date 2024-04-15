package taxify;

public interface IVehicle extends IMovable {

    public int getId();
    public ILocation getLocation();
    public ILocation getDestination();
    public IService getService();
    public IStatistics getStatistics();
    public boolean getSilent();
    public boolean getPink();
    public boolean getShare();
    public IDriver getDriver();
    public VehicleStatus getStatus();
    public void setShare(boolean share);
    public void setDriver(IDriver driver);
    public void setCompany(ITaxiCompany company);
    public void pickService(IService service, boolean silent, boolean pink);
    public void startService();
    public void endService();
    public void notifyArrivalAtPickupLocation();
    public void notifyArrivalAtDropoffLocation();
    public boolean isFree(boolean pink);
    public boolean isService(boolean pink);
    public int calculateCost();
    public String toString();
    public void setPink(boolean pink);
    
}