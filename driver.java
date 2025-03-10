import java.util.Scanner;
import java.io.*;

public class driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Start 
        Process loggerProcess = startProcess();

        
        PrintWriter writer = new PrintWriter(loggerProcess.getOutputStream(), true);
        Scanner processScanner = new Scanner(loggerProcess.getInputStream());

       



        Thread outputThread = new Thread(() -> readChild(processScanner));
        outputThread.start();




        while (true) {
           

            

            System.out.println("--------------------------------------");
            System.out.println("              MENU                     ");
            System.out.println("--------------------------------------");
            System.out.println("password - Set password");
            System.out.println("encrypt - encrypt your input");
            System.out.println("decrypt - decrypt a string");
            System.out.println("history - show history ");
            System.out.println("quit - end program");
            System.out.println("--------------------------------------");
            System.out.print("Enter command- ");


            String input = scanner.nextLine();  


            
            
            
            if (input.equals("quit"))
            {
              loggerProcess.destroy();
               break; // Exit loop
               
            }
           

            writer.println(input); // Send input to logger 
            writer.flush();
            




            if(input.equals("password"))
                System.out.println("password");
            else if (input.equals("encrypt"))
                System.out.println("encrypt");
            else if (input.equals("decrypt"))
                System.out.println("decrypt");
            else if (input.equals("history"))
                System.out.println("history");
            else 
                System.out.println("enter valid input");
   
            





            
        }











        try {
            loggerProcess.waitFor();
            outputThread.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static Process startProcess() {
        try {
            ProcessBuilder loggerProcessBuilder = new ProcessBuilder("java", "logger");
            Process loggerProcess = loggerProcessBuilder.start();
            return loggerProcess;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void readChild(Scanner processScanner) {
        while (processScanner.hasNextLine()) {
            System.out.println("logger: " + processScanner.nextLine());
        }
    }
}
