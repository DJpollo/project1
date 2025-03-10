import java.util.Scanner;
import java.io.*;

public class parent { 
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);


    // Start 
    Process loggerProcess = startProcess();

    
    PrintWriter writer = new PrintWriter(loggerProcess.getOutputStream(), true);
    Scanner processScanner = new Scanner(loggerProcess.getInputStream());

    // Thread 
    Thread outputThread = new Thread(() -> readChild(processScanner));
    outputThread.start();

    






    while (true) {
        String input = scanner.nextLine();  
        if ("END".equals(input)) {
            writer.println(input); 
            writer.flush();
            break; // Exit loop
        }

        writer.println(input); // Send input to logger 
        writer.flush();
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


