package taxify;

import java.util.List;

public class FindClosestServiceVehicle implements IFindVehicle {
    @Override
    public int getIndex(List<IVehicle> vehicles, ILocation origin) {
        int distance =  Integer.MAX_VALUE;
        int index = -1;
        for (int vehicle=0; vehicle<vehicles.size(); vehicle++) {
            if((vehicles.get(vehicle).getStatus() == VehicleStatus.SERVICE || vehicles.get(vehicle).getStatus() == VehicleStatus.PICKUP) && (ApplicationLibrary.distance(vehicles.get(vehicle).getLocation(), origin) < distance)){
                System.out.println("made it within found service vehicle");
                distance = ApplicationLibrary.distance(vehicles.get(vehicle).getLocation(), origin);
                index = vehicle;
            }
        }
        return index;
    }
}
