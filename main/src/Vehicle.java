public class Vehicle {

    private String name;
    private String type;
    private int distanceTraveled;


    public Vehicle(String name, String type, int distance) {
        this.name = name;
        this.type = type;
        this.distanceTraveled = distance;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getDistanceTraveled() {
        return distanceTraveled;
    }


}
