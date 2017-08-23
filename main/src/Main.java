import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;


public class Main {

    private static Boolean extraPrint = true;
    private static Boolean developerMode = true;
    private static String logFolder;

    public static Boolean isRaining;

    private static ArrayList<Car> cars = new ArrayList<>();
    private static ArrayList<Motorcycle> motorcycles = new ArrayList<>();
    private static ArrayList<Truck> trucks = new ArrayList<>();

    private static ArrayList<ArrayList> groupedVehicles = new ArrayList<>();

    private static ArrayList<Integer> truckNumbers = new ArrayList<>();

    private static ArrayList<String> carNames = Stream
                                         .of("Icon", "Twister", "Mammoth", "Guerilla", "Behemoth",
                                             "Starlight", "Eon", "Eminance", "Might", "Sliver",
                                             "Falcon", "Vision", "Flux", "Revelation", "Catalyst",
                                             "Dragon", "Heirloom", "Temper", "Zeal", "Pinnacle",
                                             "Ranger", "Striker", "Ethereal", "Dynamo", "Blizzard")
                                         .collect(Collectors.toCollection(ArrayList::new));


    public static void main(String[] args) {

        if (developerMode) {

            logFolder = "../../../logs/" + getNowAsString();

            File directory = new File(logFolder);

            if (! directory.exists()){
                directory.mkdir();
            }

        }

        createVehicles();

        simulateRace();

        if (extraPrint) {
            groupVehicles();
            sortGroupedVehicles();
            printRaceResultsExtra();
        }
        else {
            printRaceResults();
        }
    }


    private static void createVehicles() {

        for (int i = 0; i < 10; i++) {

            String carName = selectCarName();
            cars.add(new Car(carName));

            motorcycles.add(new Motorcycle(i + 1));

            int truckNumber = selectTruckNumber();
            trucks.add(new Truck(truckNumber));

        }

    }


    private static String selectCarName() {

        String carName = "";

        for (int j = 0; j < 2; j++) {
            Random randomGenerator = new Random();
            int randomIndex = randomGenerator.nextInt(carNames.size());
            carName += carNames.get(randomIndex) + " ";
            carNames.remove(randomIndex);
        }

        return carName.trim();
    }


    private static int selectTruckNumber() {

        Boolean status = true;
        int truckNumber = 0;

        while (status) {

            Random randomTruck = new Random();
            truckNumber = randomTruck.nextInt(1001);

            if (truckNumbers.indexOf(truckNumber) == -1) {
                truckNumbers.add(truckNumber);
                status = false;
            }
        }

        return truckNumber;
    }


    private static void checkIfRaining() {

        Boolean raining = false;

        Random rainRand = new Random();
        if (rainRand.nextInt(10) < 3) {
            raining = true;
        }

        isRaining = raining;
    }


    private static void simulateRace() {

        for (int i = 0; i < 50; i++) {

            checkIfRaining();

            if (isRaining) {
                Car.setSpeedLimit(70);
            }
            else {
                Car.setSpeedLimit(-1);
            }

            for (int j = 0; j < 10; j++) {

                cars.get(j).moveForAnHour();
                motorcycles.get(j).moveForAnHour();
                trucks.get(j).moveForAnHour();

            }

        }

    }


    private static void printRaceResults() {

        for (int i = 0; i < 10; i++) {
            System.out.printf("%s %s %s%n", cars.get(i).name, "car", cars.get(i).distanceTraveled);
            System.out.printf("%s %s %s%n", motorcycles.get(i).name, "motorcycle", motorcycles.get(i).distanceTraveled);
            System.out.printf("%s %s %s%n", trucks.get(i).name, "truck", trucks.get(i).distanceTraveled);
        }
    }


    public static Boolean getDeveloperMode() {
        return developerMode;
    }


    public static String getLogFolder() {
        return logFolder;
    }


    private static String getNowAsString() {
        SimpleDateFormat formattedDate = new SimpleDateFormat("yyyyMMdd.HHmmss.SSS");
        Date now = new Date();
        String strDate = formattedDate.format(now);
        return strDate;
    }


    private static void groupVehicles() {

        cars.forEach(car->{
            ArrayList<String> tempCar = new ArrayList<>();
            tempCar.add(car.name);
            tempCar.add(String.valueOf(car.distanceTraveled));
            tempCar.add("car");
            groupedVehicles.add(tempCar);
        });

        motorcycles.forEach(motor->{
            ArrayList<String> tempMotor = new ArrayList<>();
            tempMotor.add(motor.name);
            tempMotor.add(String.valueOf(motor.distanceTraveled));
            tempMotor.add("motorcycle");
            groupedVehicles.add(tempMotor);
        });

        trucks.forEach(truck->{
            ArrayList<String> tempTruck = new ArrayList<>();
            tempTruck.add(String.valueOf(truck.name));
            tempTruck.add(String.valueOf(truck.distanceTraveled));
            tempTruck.add("truck");
            groupedVehicles.add(tempTruck);
        });

    }


    private static void sortGroupedVehicles() {

        for (int i = 0; i < groupedVehicles.size()-1; i++) {
            for (int j = 0; j < groupedVehicles.size()-1; j++) {
                if (Integer.parseInt(groupedVehicles.get(j).get(1).toString()) < Integer.parseInt(groupedVehicles.get(j + 1).get(1).toString())) {
                    ArrayList temp = groupedVehicles.get(j);
                    groupedVehicles.set(j, groupedVehicles.get(j+1));
                    groupedVehicles.set(j+1, temp);
                }
            }
        }

    }


    private static void printRaceResultsExtra() {

        System.out.printf("%-22s %-13s %s%n", "VEHICLE", "TYPE", "DISTANCE");

        groupedVehicles.forEach(vehicle -> {
            System.out.printf("%-22s %-13s %6d%n", vehicle.get(0), vehicle.get(2), Integer.parseInt(vehicle.get(1).toString()));
        });
    }


}
