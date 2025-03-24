import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.logging.Logger;
import java.io.*;

public class driver {

    static String s="";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Process loggerProcess = startProcess();
        Process encryptorProcess= startProcessencryptor();

        PrintWriter writer = new PrintWriter(loggerProcess.getOutputStream(), true);
        Scanner processScanner = new Scanner(loggerProcess.getInputStream());
        PrintWriter encryptorwriter = new PrintWriter(encryptorProcess.getOutputStream(), true);
        Scanner encryptorScanner = new Scanner(encryptorProcess.getInputStream());


        Thread outputThread = new Thread(() -> readChild(processScanner));
        outputThread.start();
        
        Thread outputThreadencryptor = new Thread(() -> readChildencryptor(encryptorScanner,writer));
        outputThreadencryptor.start();


        int pass=0;

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
            System.out.println("Enter command- ");
            


            String input = scanner.nextLine();  


            if (!input.matches("[a-zA-Z ]+")) { 
                System.out.println("Invalid input!");
                continue;
            }

            if (input.equals("quit"))
            {
                System.out.println("going to quit");
                writer.println("quit");  // Send to logger process
                writer.flush();
                break; // Exit loop  
            }

            if (input.equals("history")) {//just shows the logger (history)
                s = readLogFromFile();
                System.out.println(s);
            }


            if (input.equals("encrypt")&&pass>0) {

                System.out.println("do you wish to use from the history? yes/no");
                input = scanner.nextLine();

                if(input.equals("yes")){
                    readLogFromFile();
                    if(choices.isEmpty()){
                    System.out.println("List is empty cant get from history");
                    }
                        else
                        {

                            System.out.println(choices+ " choose from the list");
                            input = scanner.nextLine();
                            int num = Integer.parseInt(input);
                            String usersChoice=choices.get(num);
                            encryptorwriter.println("encrypt"); // Send input to encryptor 


                            encryptorwriter.println(usersChoice);

                            writer.println("encrypt"+" "+usersChoice); // Send input to logger 
                            
                            encryptorwriter.flush();


                        }
                }
                else{

                    System.out.println("encrypting -");
                    encryptorwriter.println("encrypt"); // Send input to encryptor 
                    input = scanner.nextLine();

                    encryptorwriter.println(input);
                    writer.println("encrypt"+" "+input); // Send input to logger 

                    encryptorwriter.flush();
                    writer.flush();


                }

            }
                else if(pass==0&&input.equals("encrypt"))
                System.out.println("set password first");



            if (input.equals("password")) {
                pass++;
                System.out.println("set paskey -");

                encryptorwriter.println("password"); // Send input to encryptor 
                input = scanner.nextLine();
                encryptorwriter.println(input);

                encryptorwriter.flush();
            }



            if (input.equals("decrypt")&&pass>0) {

                System.out.println("do you wish to use from the history? yes/no");
                input = scanner.nextLine();
                if(input.equals("yes")){
                    readLogFromFile();
                    if(choices.isEmpty()){
                    System.out.println("List is empty cant get from history");
                    }
                        else
                        {

                            System.out.println(choices+ " choose from the list");
                            input = scanner.nextLine();
                            int num = Integer.parseInt(input);
                            String usersChoice=choices.get(num);
                            encryptorwriter.println("decrypt"); // Send input to encryptor 


                            encryptorwriter.println(usersChoice);

                             writer.println("decrypt"+" "+usersChoice); // Send input to logger 
                            
                            encryptorwriter.flush();

                    }

                }
                else{

                        System.out.println("decrypting -");
                        encryptorwriter.println("decrypt"); // Send input to encryptor 
                        input = scanner.nextLine();


                        encryptorwriter.println(input);

                        writer.println("decrypt"+" "+input); // Send input to logger 


                        
                        encryptorwriter.flush();
                        writer.flush();

                }

               
            }
            else if(pass==0&&input.equals("decrypt"))
            System.out.println("set password first");
   
        }//end of while loop


        loggerProcess.destroy();
        encryptorProcess.destroy();


        try {
            loggerProcess.waitFor();
            outputThread.join();
            encryptorProcess.waitFor();
            outputThreadencryptor.join();



        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    
    public static String readLogFromFile() {
        String history = "";  
        choices=new ArrayList<String>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader("log.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {  
                history += line + "\n";  
                getchoice(line);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return history;  
    }
    
    static ArrayList<String> choices= new ArrayList<String>();
    public static ArrayList<String> getchoice(String s)
        {
                String holder="";
                int goAhead=0;
                for (int i =0;i<s.length();i++){
                    char ch = s.charAt(i);
                    if (ch==']'){
                    goAhead++;
                    continue;
                    }
                    if(goAhead>0)
                    holder+=ch;
   
                }
            
                choices.add(holder);
                choices = new ArrayList<>(new LinkedHashSet<>(choices));

                return choices;


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


    public static Process startProcessencryptor() {
        try {
            ProcessBuilder encryptionProcessBuilder = new ProcessBuilder("java", "encryption");
            Process encryptionProcess = encryptionProcessBuilder.start();
            return encryptionProcess;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void readChild(Scanner processScanner) {
       
        while (processScanner.hasNextLine()) {
            s=processScanner.nextLine();

           
        }
    }
    private static String encryptionOutput = ""; 

    private static void readChildencryptor(Scanner encryptorScanner, PrintWriter writer) {
        while (encryptorScanner.hasNextLine()) {
            encryptionOutput = encryptorScanner.nextLine(); 
            writer.println(encryptionOutput);
        }
    }
    
}
