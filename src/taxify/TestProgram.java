package taxify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestProgram {

    public static void main(String[] args) {
        List<IUser> users = new ArrayList<>();
        List<IDriver> drivers = new ArrayList<>();
        List<IVehicle> vehicles = new ArrayList<>();

        // Creating users
        for (int i = 0; i < 10; i++) {
            IUser user = new User(i + 1, "Jack", "Endo", 'm', LocalDate.of(2000, 8, 20));
            users.add(user);
        }


        for (int i = 0; i < 5; i++) {
            IVehicle taxi = new Taxi(i + 1, ApplicationLibrary.randomLocation());
            IVehicle shuttle = new Shuttle(i + 1, ApplicationLibrary.randomLocation());
            vehicles.add(taxi);
            vehicles.add(shuttle);
        }

        for (int i = 0; i < 10; i++) {
            IVehicle assignedVehicle = vehicles.get(i / 2); // Assign every alternate vehicle to a driver
            IDriver driver;
            if (i % 2 == 0) {
                driver = new Driver("Jorge Martinez", 'm', LocalDate.of(1980, 9, 20), 3, 4.2, assignedVehicle);
            } else {
                driver = new Driver("Another Driver", 'f', LocalDate.of(1990, 5, 15), 5, 4.5, assignedVehicle);
            }
            drivers.add(driver);
        }

        ITaxiCompany taxiCompany = new TaxiCompany("Taxifista", users, vehicles);
        ApplicationSimulator simulator = new ApplicationSimulator(taxiCompany, users, vehicles);
        taxiCompany.addObserver(simulator);

        // Requesting services for users
        for (int i=0; i<=5; i++) {
            simulator.requestService();
            // taxiCompany.provideService(user.getId());
        }

        do {
            simulator.show();
            simulator.update();
            System.out.println("made it");
        }
         while (taxiCompany.getTotalServices() != 0);


        // Display statistics
        System.out.println("Statistics:");
        for (IVehicle vehicle : vehicles) {
            String vehicleType = (vehicle instanceof Taxi) ? "Taxi" : "Shuttle";
            String stats = String.format("%s %2d %2d services %3d km. %4d eur. %2d reviews %.2f stars",
                    vehicleType,
                    vehicle.getId(),
                    vehicle.getStatistics().getServices(),
                    vehicle.getStatistics().getDistance(),
                    vehicle.getStatistics().getBilling(),
                    vehicle.getStatistics().getReviews(),
                    vehicle.getStatistics().getStars());
            System.out.println(stats);
        }
    }
}