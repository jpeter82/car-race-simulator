import java.io.*;


public class Log {


    public static void save(String fileName, String actualTurnData) throws IOException {

        BufferedWriter bwriter = null;
        FileWriter fwriter = null;

        try {
            File file = new File(Main.getLogFolder() + "/" + fileName + ".txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            fwriter = new FileWriter(file.getAbsoluteFile(), true);
            bwriter = new BufferedWriter(fwriter);

            bwriter.append(actualTurnData + "\n");
        }
        catch (IOException error) {
            System.out.println("Error saving log: " + error);
        }
        finally {

            try {
                if (bwriter != null)
                    bwriter.close();

                if (fwriter != null) {
                    fwriter.close();
                }
            }
            catch (IOException ex) {
                System.out.println("Error closing File: " + ex);
            }

        }

    }


}
