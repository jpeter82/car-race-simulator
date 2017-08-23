import java.io.IOException;
import java.util.Random;


public class Car {

    public String name;
    private int normalSpeed;
    private int actualSpeed;
    public int distanceTraveled = 0;
    private static int speedLimit = -1;
    private int turnCounter = 0;


    public Car(String carName) {

        this.name = carName;

        Random randomSpeed = new Random();
        this.normalSpeed = randomSpeed.nextInt(31) + 80;

    }


    public static void setSpeedLimit(int limit) {

        speedLimit = limit;

    }


    private void calculateActualSpeed() {

        if (speedLimit == -1) {
            this.actualSpeed = this.normalSpeed;
        }
        else {
            this.actualSpeed = speedLimit;
        }
    }


    public void moveForAnHour() {

        this.turnCounter += 1;
        calculateActualSpeed();
        this.distanceTraveled += this.actualSpeed;

        if (Main.getDeveloperMode()) {
            this.logTurnData();
        }

    }


    private void logTurnData() {
        String actualTurnData = "";

        actualTurnData += "Turn: " + String.valueOf(this.turnCounter) + "\n";
        actualTurnData += "Distance traveled: " + String.valueOf(this.distanceTraveled) + "\n";
        actualTurnData += "Actual speed: " + String.valueOf(this.actualSpeed) + "\n";
        actualTurnData += "Speed limit: " + String.valueOf(speedLimit) + "\n\n";

        try {
            Log.save(String.valueOf(this.name), actualTurnData);
        }
        catch (IOException error) {
            System.out.println("Unexpected error happened while saving the log: " + error);
        }
    }


}
