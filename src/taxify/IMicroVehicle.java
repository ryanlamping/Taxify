package taxify;

public interface IMicroVehicle {
    
        public int getId();
        public ILocation getLocation();
        public IService getService();
        public IStatistics getStatistics();
        public MicroStatus getStatus(); 
        public void setCompany(ITaxiCompany company);
        public void startMicroService();
        public void endMicroService();
        public void notifyArrivalAtPickupLocation();
        public void notifyArrivalAtDropoffLocation();
        public int calculateCost();
        public String toString();
}
