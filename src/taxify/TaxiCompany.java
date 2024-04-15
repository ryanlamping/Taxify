package taxify;

import java.util.List;

public class TaxiCompany implements ITaxiCompany, ISubject {
    private String name;
    private List<IUser> users;
    private List<IVehicle> vehicles;
    private int totalServices;
    private IObserver observer;
    
    public TaxiCompany(String name, List<IUser> users, List<IVehicle> vehicles) {
        this.name = name;
        this.users = users;
        this.vehicles = vehicles;        
        this.totalServices = 0;
        
        for (IUser user : this.users) {
            user.setCompany(this);
        }
        
        for (IVehicle vehicle : this.vehicles) {
            vehicle.setCompany(this);
        }
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getTotalServices() {
        return this.totalServices;
    }
        
    @Override
    public boolean provideService(int requestUser, boolean silent, boolean pink, boolean share) {
        // find user and create a variable type user
        int userIndex = findUserIndex(requestUser);
        IUser user = this.users.get(userIndex);

        // set origin of user
        ILocation origin = ApplicationLibrary.randomLocation();

        // initiate vehicle indexes as -1 as no vehicle has been found
        int vehicleIndex = -1;

        if (share) {
            System.out.println("User " + user.getId() + " chose ride share");
            vehicleIndex = findVehicle(new FindClosestServiceVehicle(), origin);

            if (vehicleIndex != -1) {
                System.out.println("Vehicle " + vehicles.get(vehicleIndex).toString() + "found for ride share: ");
            } else {
                System.out.println("No vehicle for ride share found!");
            }
        }

        else if (pink) {
            System.out.println("User " + user.getId() + " chose a pink ride");
            vehicleIndex = findVehicle(new FindClosestPinkVehicle(), origin);

            if (vehicleIndex != -1) {
                System.out.println("Vehicle " + vehicles.get(vehicleIndex).toString() + "found for pink ride: ");
            } else {
                System.out.println("No vehicle for pink ride found!");
            }
        }
        else {
            System.out.println("User " + user.getId() + " is looking for a ride");
            vehicleIndex = findVehicle(new FindClosestFreeVehicle(), origin);

            if (vehicleIndex != -1) {
                System.out.println("Vehicle " + vehicles.get(vehicleIndex).toString() + "found for regular ride: ");
            } else {
                System.out.println("No vehicle for regular ride found!");
            } 
        }
        
        // if there is a free vehicle, assign a random pickup and drop-off location to the new service
        System.out.println("vehicle index: " + vehicleIndex);
        if (vehicleIndex != -1) {
            System.out.println("made it");            
            ILocation destination = ApplicationLibrary.randomLocation(origin);
            
            // update the user status
                       
            this.users.get(userIndex).setService(true);

            if (share == true) {
                IService service = this.vehicles.get(vehicleIndex).getService();
                this.totalServices--;
                service.setPickupLocation(origin);
                service.setDropOffLocation(destination);

                service.setShare(share);
                // formality: make sure driver accepts ride (hard coded to true)
                this.vehicles.get(vehicleIndex).getDriver().acceptService(service);
                // call function to pick up user
                this.vehicles.get(vehicleIndex).pickService(service, silent, pink);

                notifyObserver("User " + this.users.get(userIndex).getId() + " requests a service from " + service.toString() + ", the ride is assigned to " +
                this.vehicles.get(vehicleIndex).getClass().getSimpleName() + " " + this.vehicles.get(vehicleIndex).getId() + " at location " +
                this.vehicles.get(vehicleIndex).getLocation().toString());
 
                // update the counter of services
                
                this.totalServices++;
                
                return true;
            }
            else {
                // create a service with the user, the pickup and the drop-off location

                IService service = new Service(this.users.get(userIndex), origin, destination, silent, pink, share);
                
                // assign the new service to the vehicle. no need to pass pink because only vehicles that would be available have female drivers
                
                // formality: make sure driver accepts ride (hard coded to true)
                this.vehicles.get(vehicleIndex).getDriver().acceptService(service);

                // call function to pick up user
                this.vehicles.get(vehicleIndex).pickService(service, silent, pink);

                notifyObserver("User " + this.users.get(userIndex).getId() + " requests a service from " + service.toString() + ", the ride is assigned to " +
                this.vehicles.get(vehicleIndex).getClass().getSimpleName() + " " + this.vehicles.get(vehicleIndex).getId() + " at location " +
                this.vehicles.get(vehicleIndex).getLocation().toString());
 
                // update the counter of services
                
                this.totalServices++;
                
                return true;
            } 
        }
        
        return false;
    }

    @Override
    public void arrivedAtPickupLocation(IVehicle vehicle) {
        this.notifyObserver("Vehicle has arrived at pickup location");
        // notify the observer a vehicle arrived at the pickup location
    }
    
    @Override
    public void arrivedAtDropoffLocation(IVehicle vehicle) {
        // a vehicle arrives at the drop-off location
        
        IService service = vehicle.getService();       
        int user = service.getUser().getId();
        int userIndex = findUserIndex(user);
       
        // the taxi company requests the user to rate the service, and updates its status

        this.users.get(userIndex).rateService(service);
        this.users.get(userIndex).setService(false);

        // update the counter of services
        
        this.totalServices--;    
        
        notifyObserver(String.format("%-8s",vehicle.getClass().getSimpleName()) + vehicle.getId() + " drops off user " + user);         
    }
        
    @Override
    public void addObserver(IObserver observer) {
        this.observer = observer;
    }
    
    @Override
    public void notifyObserver(String message) {
        this.observer.updateObserver(message);
    }

    private int findVehicle(IFindVehicle find, ILocation origin) {
        return find.getIndex(this.vehicles, origin);
    }

    private int findUserIndex(int id) {
        for (int i=0; i<this.users.size(); i++) {
            if (this.users.get(i).getId() == id)
                return i;
        }
        
        return -1;
    }
}