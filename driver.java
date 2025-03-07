import java.util.Scanner;
import java.io.*;




public class driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        proccCreator();
        
        
                String input = scanner.nextLine();
        
        
                System.out.println(input);
            }
        
        
        
        
        
            public static void proccCreator()
    {
        try {
            ProcessBuilder logger = new ProcessBuilder("java", "logger");
            Process loggerProcess = logger.start();

            InputStream loggerInput = loggerProcess.getInputStream();
            OutputStream loggerOutput = loggerProcess.getOutputStream();

            // Send data 
            PrintWriter writer = new PrintWriter(loggerOutput, true);
            writer.println("Hello logger ");
            writer.println("STOP");

            

            // Read data 
            BufferedReader reader = new BufferedReader(new InputStreamReader(loggerInput));
            String input;
            while ((input = reader.readLine()) != null) {
                System.out.println("Parent received " + input);
                
                if (input.equals("STOP")) 
                break;
                
            }

            // Wait 
            loggerProcess.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    } 















}
