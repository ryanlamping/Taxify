package taxify;

import java.time.LocalDate;

public class User implements IUser {
    private int id;
    private String firstName;
    private String lastName;
    private char gender;
    private LocalDate birthDate;
    private ITaxiCompany company;
    private boolean service;
    
    public User(int id, String firstName, String lastName, char gender, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.service = false;
    }
    
    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }
    
    @Override
    public String getLastName() {
        return this.lastName;
    }
    
    @Override
    public char getGender() {
       return this.gender;
    }

    @Override
    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    @Override
    public boolean getService() {
        return this.service;
    }
    
    @Override
    public void setService(boolean service) {
        this.service = service;
    }
    
    @Override
    public void setCompany(ITaxiCompany company) {
        this.company = company;
    }
    
    @Override
    public void requestService(boolean silent, boolean pink, boolean share) {
        if(this.getGender() == 'm') {
            pink = false;
        }
        // call function to see if a service is nearby
            // if yes --> allow option to do shared service
            // if no --> pass to single service
        this.company.provideService(this.id, silent, pink, share);
    }
    
    @Override
    public void rateService(IService service) {
        // users rate around 50% of the services (1 to 5 stars)
        
        if (ApplicationLibrary.rand() % 2 == 0) {
            service.setStars(ApplicationLibrary.rand(5) + 1);
        }
    }

    public boolean acceptRideShare() {
        if( ApplicationLibrary.rand() % 2 == 0) {
            return true;
        }
        else {
            return false;
        }
    }
    
    @Override
    public String toString() {
        return this.getId() + " " + String.format("%-20s",this.getFirstName() + " " + this.getLastName());
    }
}
