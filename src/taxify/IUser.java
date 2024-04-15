package taxify;

import java.time.LocalDate;

public interface IUser {

    public int getId();
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
