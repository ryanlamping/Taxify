package taxify;

import java.util.List;

public class FindClosestFreeVehicle implements IFindVehicle {
    @Override
    public int getIndex(List<IVehicle> vehicles, ILocation origin) {
        int distance =  Integer.MAX_VALUE;
        int index = -1;
        for (int vehicle=0; vehicle<vehicles.size(); vehicle++) {
            if(vehicles.get(vehicle).getStatus() == VehicleStatus.FREE && (ApplicationLibrary.distance(vehicles.get(vehicle).getLocation(), origin) < distance)){
                distance = ApplicationLibrary.distance(vehicles.get(vehicle).getLocation(), origin);
                index = vehicle;
            }
        }
        return index;
    }  
}
