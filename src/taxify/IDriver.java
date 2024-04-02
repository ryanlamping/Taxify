package taxify;

import java.time.LocalDate;

public interface IDriver {
    public boolean pinkService(Service service);
    public String getName();
    public char getGender();
    public LocalDate getBirthDate();
    public int getYearsOfService();
    public double getOverallRating();
    public IVehicle getVehicle();
    public boolean acceptService(IService service);
}
