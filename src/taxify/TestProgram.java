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
        IUser user = new User(1, "Jack", "Endo", 'm', LocalDate.of(2000, 8, 20));
        users.add(user);

        IUser user1 = new User(2, "Jim", "Torka", 'm', LocalDate.of(1988, 6, 5));
        users.add(user1);

        IUser user2 = new User(3, "Riley", "Pulp", 'f', LocalDate.of(2000, 3, 20));
        users.add(user2);

        IUser user3 = new User(4, "Ryan", "Lamping", 'm', LocalDate.of(2003, 5, 30));
        users.add(user3);

        IUser user4 = new User(5, "Ana Belen", "Ortiz", 'f', LocalDate.of(2003, 8, 20));
        users.add(user4);

        IUser user5 = new User(6, "Laci", "Type", 'f', LocalDate.of(2010, 2, 20));
        users.add(user5);

        IUser user6 = new User(7, "Matt", "Cook", 'm', LocalDate.of(2012, 4, 20));
        users.add(user6);

        IUser user7 = new User(8, "Jack", "Smith", 'm', LocalDate.of(2000, 8, 20));
        users.add(user7);

        IUser user8 = new User(9, "Steve", "Endo", 'm', LocalDate.of(2000, 8, 20));
        users.add(user8);

        IUser user9 = new User(10, "Isabell", "Etzel", 'f', LocalDate.of(1990, 8, 20));
        users.add(user9);

        IUser user10 = new User(11, "Nate", "Erk", 'm', LocalDate.of(1970, 8, 20));
        users.add(user10);



        for (int i = 0; i < 5; i++) {
            IVehicle taxi = new Taxi(i + 1, ApplicationLibrary.randomLocation());
            IVehicle shuttle = new Shuttle(i + 1, ApplicationLibrary.randomLocation());
            vehicles.add(taxi);
            vehicles.add(shuttle);
        }

        for (int i = 0; i < 10; i++) {
            IVehicle assignedVehicle = vehicles.get(i);
            String driverName = "Driver" + i + "X";
            char gender = 'f';
            if(i % 2 == 1) {
                gender = 'm';
            }
            IDriver driver = new Driver(driverName, gender, LocalDate.of(1980, 9, 20), 3, 4.2, assignedVehicle);
            drivers.add(driver);
            assignedVehicle.setDriver(driver);
        }

        ITaxiCompany taxiCompany = new TaxiCompany("Taxifista", users, vehicles);
        ApplicationSimulator simulator = new ApplicationSimulator(taxiCompany, users, vehicles);
        taxiCompany.addObserver(simulator);

        // Requesting services for users
        boolean silent = true;
        boolean pink = false;
        for (int i=0; i<=5; i++) {
            int rand = ApplicationLibrary.rand();
            if (rand % 2 == 1) {
                silent = false;
                pink = true;
            }
            simulator.requestService(silent, pink);
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
            String stats = String.format("%s %2d driven by %s, Silent: %b Pink: %b %2d services %3d km. %4d eur. %2d reviews %.2f stars",
                    vehicleType,
                    vehicle.getId(),
                    vehicle.getDriver().getName(),
                    vehicle.getSilent(),
                    vehicle.getPink(),
                    vehicle.getStatistics().getServices(),
                    vehicle.getStatistics().getDistance(),
                    vehicle.getStatistics().getBilling(),
                    vehicle.getStatistics().getReviews(),
                    vehicle.getStatistics().getStars());
            System.out.println(stats);
        }
    }
}