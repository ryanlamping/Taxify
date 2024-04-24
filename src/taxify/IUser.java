package taxify;

import java.time.LocalDate;

public interface IUser {

    public int getId();
    public void findMicroService(); // check distance to scooter from user or a timer to arrive to the scooter |
    public void bookMicroService(); // call find | free --> booked
    public boolean startMicroService(int id); // booked --> in a ride
    public boolean endMicroService(int id);   // in a ride --> free
    public String getFirstName();
    public String getLastName();
    public char getGender();
    public LocalDate getBirthDate();
    public boolean getService();
    public void setService(boolean service);
    public void setCompany(ITaxiCompany company);
    public void requestService(boolean silent, boolean pink, boolean share);
    public void rateService(IService service);
    public boolean acceptRideShare();
    public String toString();

}
