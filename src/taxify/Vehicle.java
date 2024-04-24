package taxify;

public abstract class Vehicle implements IVehicle {
	private int id;
	private ITaxiCompany company;
	private IService service;
	private VehicleStatus status;
	private ILocation location;
    private ILocation destination;
    private IStatistics statistics;
    private IRoute route;
	private boolean silent;	// do i include as attribute of vehicle or do I just get it from service?
	private boolean pink; // again do we include or not?
	private boolean share;
	private IDriver driver;
	    
    public Vehicle(int id, ILocation location) {    	
    	this.id = id;
	    this.service = null;
	    this.status = VehicleStatus.FREE;
    	this.location = location;	    
	    this.destination = ApplicationLibrary.randomLocation(this.location);
	    this.statistics = new Statistics();
	    this.route = new Route(this.location, this.destination);
    }

	@Override
    public int getId() {
    	return this.id;
    }

	@Override
	public void setPink(boolean pink) {
		this.pink = pink;
	}
 
	@Override
    public ILocation getLocation() {
    	return this.location;
	}

	@Override
    public ILocation getDestination() {
    	return this.destination;
    }
    
	@Override
    public IService getService() {
    	return this.service;
    }
	public VehicleStatus getStatus() {
		return this.status;
	}
    
	@Override
    public IStatistics getStatistics() {
    	return this.statistics;
    }

	@Override 
	public IDriver getDriver() {
		return this.driver;
	}
	@Override
	public void setShare(boolean share) {
		this.share = share;
	}
	@Override
	public void setDriver(IDriver driver) {
		this.driver = driver;
	}
    
	@Override
	public void setCompany(ITaxiCompany company) {
		this.company = company;
	}
	
	@Override
    public void pickService(IService service, boolean silent, boolean pink) {
		// pick a service, set destination to the service pickup location, and status to "pickup"
		System.out.println("made it to pick service");
		this.pink = pink;
    	this.service = service;
		this.silent = silent;
    	this.destination = service.getPickupLocation(); 
    	this.route = new Route(this.location, this.destination);    	
    	this.status = VehicleStatus.PICKUP;
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
	public boolean getShare() {
		return this.share;
	}

	@Override
    public void startService() {
		// set destination to the service drop-off location, and status to "service"
        this.destination = this.service.getDropoffLocation();
        this.route = new Route(this.location, this.destination);
        this.status = VehicleStatus.SERVICE;
    }

	@Override
    public void endService() {
		// update vehicle statistics
		
    	this.statistics.updateBilling(this.calculateCost());
    	this.statistics.updateDistance(this.service.calculateDistance());
    	this.statistics.updateServices();
    	
    	// if the service is rated by the user, update statistics
    	
    	if (this.service.getStars() != 0) {
    		this.statistics.updateStars(this.service.getStars());
    		this.statistics.updateReviews();
    	}
    	
    	// set service to null, and status to "free"
    	
        this.service = null;
        this.destination = ApplicationLibrary.randomLocation(this.location);
        this.route = new Route(this.location, this.destination);
        this.status = VehicleStatus.FREE;
    }

	@Override
    public void notifyArrivalAtPickupLocation() {
    	this.company.arrivedAtPickupLocation(this);
    	this.startService();
	}
	    
	@Override
    public void notifyArrivalAtDropoffLocation() {
    	this.company.arrivedAtDropoffLocation(this);
    	this.endService();
 	}
	    
	@Override
    public boolean isFree(boolean pink) {
		if(pink == true && this.driver.getGender() == 'f') {
			return (this.status == VehicleStatus.FREE);
		}
		else if (pink == false) {
			return (this.status == VehicleStatus.FREE);
		}
		else {
			return false;
		}
    }   

	@Override
	public boolean isService(boolean pink) {
		if(pink == true && this.driver.getGender() == 'f') {
			return (this.status == VehicleStatus.SERVICE);
		}
		else if (pink == false) {
			return (this.status == VehicleStatus.SERVICE);
		}
		else {
			System.out.println("Vehicle not in service");
			// find a free vehicle if not
			return isFree(pink);
		}
	}

	// function to see if there is a vehicle close to where user makes request
    
	@Override
    public void move() {
		// get the next location from the driving route
		
    	this.location = this.route.getNextLocation();    	
    	
		// if the route has more locations the vehicle continues its route, otherwise the vehicle has arrived to a pickup or drop off location

    	if (!this.route.hasLocations()) {
    		if (this.service == null) {
    			// the vehicle continues its random route

    			this.destination = ApplicationLibrary.randomLocation(this.location);
    			this.route = new Route(this.location, this.destination);
    		}
    		else {
    			// check if the vehicle has arrived to a pickup or drop off location

    			ILocation origin = this.service.getPickupLocation();
    			ILocation destination = this.service.getDropoffLocation();

    			if (this.location.getX() == origin.getX() && this.location.getY() == origin.getY()) {

    				notifyArrivalAtPickupLocation();

    			} else if (this.location.getX() == destination.getX() && this.location.getY() == destination.getY()) {

    				notifyArrivalAtDropoffLocation();

    			}    	
    		}
    	}
    }

	@Override
	public int calculateCost() {
		if(this.service.getShare()) {
			return this.service.calculateDistance()/2;
		}
		else {
			return this.service.calculateDistance();
		}
	}

	@Override
    public String toString() {
    	return this.id + " at " + this.location + " driving to " + this.destination +
    		   ((this.status == VehicleStatus.FREE) ? " is free with path " + this.route.toString(): ((this.status == VehicleStatus.PICKUP) ? " to pickup user " + this.service.getUser().getId() : " in service "));
    }	
}