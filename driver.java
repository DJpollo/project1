import java.util.Scanner;
import java.io.*;




public class driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        
        
                String input = scanner.nextLine();
                proccCreator(input);




        System.out.println("--------------------------------------");
        System.out.println("              MENU                     ");

        System.out.println("--------------------------------------");


            if (input=="password")
            password();

        
        
                //System.out.println(input);
            }
        
        
        
        public static void password()
             {




                    }




        
        
            public static void proccCreator(String sendData)
    {
        try {
            ProcessBuilder logger = new ProcessBuilder("java", "logger");
            Process loggerProcess = logger.start();

            InputStream loggerInput = loggerProcess.getInputStream();
            OutputStream loggerOutput = loggerProcess.getOutputStream();

            // Send data 
            PrintWriter writer = new PrintWriter(loggerOutput, true);
            writer.println(sendData);
            writer.println("STOP");

            

             // Read data 
            BufferedReader reader = new BufferedReader(new InputStreamReader(loggerInput));
            String input;
            while ((input = reader.readLine()) != null) {
               System.out.println(" " + input);
                
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
