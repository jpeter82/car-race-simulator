import java.io.IOException;
import java.util.Random;

public class Truck {

    public int name;
    private int speed = 100;
    public int distanceTraveled = 0;
    private int breakdownTurnsLeft = 0;
    private int turnCounter = 0;


    public Truck(int truckNumber) {

        this.name = truckNumber;

    }


    public void moveForAnHour() {

        this.turnCounter += 1;

        if (! isBrokenDown()) {
            this.distanceTraveled += this.speed;
        }

        checkBreakDown();

        if (Main.getDeveloperMode()) {
            this.logTurnData();
        }

    }


    private Boolean isBrokenDown() {

        return this.breakdownTurnsLeft > 0 ? true : false;

    }


    private void checkBreakDown() {

        Boolean status = isBrokenDown();

        if (!status) {

            Random broken = new Random();

            if (broken.nextInt(100) < 5) {
                this.breakdownTurnsLeft += 2;
            }
        }
        else {
            this.breakdownTurnsLeft -= 1;
        }
    }


    private void logTurnData() {
        String actualTurnData = "";

        actualTurnData += "Turn: " + String.valueOf(this.turnCounter) + "\n";
        actualTurnData += "Distance traveled: " + String.valueOf(this.distanceTraveled) + "\n";
        actualTurnData += "Breakdown turns left: " + String.valueOf(this.breakdownTurnsLeft) + "\n\n";

        try {
            Log.save(String.valueOf(this.name), actualTurnData);
        }
        catch (IOException error) {
            System.out.println("Unexpected error happened while saving the log: " + error);
        }
    }


}
