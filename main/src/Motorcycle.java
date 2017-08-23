import java.io.IOException;
import java.util.Random;

public class Motorcycle {

    public String name;
    private int nameNumber;
    private int speed = 100;
    public int distanceTraveled = 0;
    private int turnCounter = 0;


    public Motorcycle(int itemNumber) {
        this.nameNumber = itemNumber;
        this.name = "Motorcycle_" + this.nameNumber;
    }


    private void changeSpeedWhenRaining() {
        if (Main.isRaining) {
            Random speedRand = new Random();
            this.speed = 100 - (speedRand.nextInt(46) + 5);
        }
        else {
            this.speed = 100;
        }
    }


    public void moveForAnHour() {
        this.turnCounter += 1;
        this.changeSpeedWhenRaining();
        this.distanceTraveled += this.speed;

        if (Main.getDeveloperMode()) {
            this.logTurnData();
        }
    }


    private void logTurnData() {
        String actualTurnData = "";

        actualTurnData += "Turn: " + String.valueOf(this.turnCounter) + "\n";
        actualTurnData += "Distance traveled: " + String.valueOf(this.distanceTraveled) + "\n";
        actualTurnData += "Raining: " + String.valueOf(Main.isRaining) + "\n";
        actualTurnData += "Speed: " + String.valueOf(this.speed) + "\n\n";

        try {
            Log.save(String.valueOf(this.name), actualTurnData);
        }
        catch (IOException error) {
            System.out.println("Unexpected error happened while saving the log: " + error);
        }
    }

}
