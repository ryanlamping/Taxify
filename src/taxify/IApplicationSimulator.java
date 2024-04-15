package taxify;

public interface IApplicationSimulator {

    public void show();
    public void showStatistics();
    public void update();
    public void requestService(boolean silent, boolean pink, boolean share);
    public int getTotalServices();
    
}
