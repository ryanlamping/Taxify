package taxify;

import java.time.LocalDate;
import java.time.Period;

public class Driver implements IDriver {
    
    private String name;
    private char gender;
    private LocalDate birthDate;
    private int experience;
    private double rating;
    private IVehicle vehicle;

    public Driver(String name, char gender, LocalDate birthDate, int experience, double rating, IVehicle vehicle) {
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.experience = experience;
        this.rating = rating;
        this.vehicle = vehicle;
    }

    // public void acceptService() {
    //     // accept service, return true?
    // }

    public boolean pinkService(Service service) {
        if ( this.gender == 'f' ) {
            if(service.getUser().getGender() =='f' ){
                return true;
            }
            else if((Period.between(LocalDate.now(), service.getUser().getBirthDate())).getYears() <= 18){
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return this.name;
    }

    public char getGender() {
        return this.gender;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public int getYearsOfService() {
        return this.experience;
    }

    public double getOverallRating() {
        return this.rating;
    }
    
    public IVehicle getVehicle() {
        return this.vehicle;
    }
}
