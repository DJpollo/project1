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
        Scanner processScanner = new Scanner(loggerProcess.getInputStream());                                       //everything done to comunicate to my other java files done once here
        PrintWriter encryptorwriter = new PrintWriter(encryptorProcess.getOutputStream(), true);
        Scanner encryptorScanner = new Scanner(encryptorProcess.getInputStream());


        Thread outputThread = new Thread(() -> readChild(processScanner));
        outputThread.start();
        
        Thread outputThreadencryptor = new Thread(() -> readChildencryptor(encryptorScanner,writer));
        outputThreadencryptor.start();


        int pass=0;//counter to check if password has been set or not

        while (true) {//main loop
           
           
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


            if (!input.matches("[a-zA-Z ]+")) { //simple check to see if any numbers are in users input if so continue
                System.out.println("Invalid input!");
                continue;
            }

            else if (input.equals("quit"))//this ends the main loop
            {
                System.out.println("going to quit");
                writer.println("quit");  // Send to logger process
                writer.flush();
                break; // Exit loop  
            }

            else if (input.equals("history")) {//just shows the logger (history)
                s = readLogFromFile();
                System.out.println(s);
            }


            else if (input.equals("encrypt")&&pass>0) {

                System.out.println("do you wish to use from the history? yes/no");
                input = scanner.nextLine();

                if(input.equals("yes")){
                    readLogFromFile();//readfromfile is ran to update the arraylist that holds histories strings
                    if(choices.isEmpty()){
                    System.out.println("List is empty cant get from history");
                    }
                        else
                        {

                            System.out.println(choices+ " choose from the list");
                            input = scanner.nextLine();
                            int num = Integer.parseInt(input);//convert string to integer
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
                else if(pass==0&&input.equals("encrypt"))//checks if pass is not == to 0 if so then the password has not been set
                System.out.println("set password first");



            else if (input.equals("password")) {
                pass++;//here it is updated to know that password has been set
                System.out.println("set paskey -");

                encryptorwriter.println("password"); // Send input to encryptor 
                input = scanner.nextLine();
                encryptorwriter.println(input);

                encryptorwriter.flush();
            }



           else if (input.equals("decrypt")&&pass>0) {//same type of functioonality as encrypt

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
            else 
            System.out.println("eneter valid input");



   
        }//end of while loop


        loggerProcess.destroy();            //ensures once quit is typed that the processes are destryed 
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
        choices=new ArrayList<String>();//creates a new one everytime here so it doesnt pile up after every run
    
        try (BufferedReader reader = new BufferedReader(new FileReader("log.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {  
                history += line + "\n";  
                getchoice(line);//updates the arraylist full of history outputs not encluding the actions

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
                    if (ch==']'){                       //makes sure i get everything after the action from the history logger
                    goAhead++;
                    continue;
                    }
                    if(goAhead>0)
                    holder+=ch;
   
                }
            
                choices.add(holder);
                choices = new ArrayList<>(new LinkedHashSet<>(choices));//shouldnt let duplicates to exist but not sure this works. will leave it just incase

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
            encryptionOutput = encryptorScanner.nextLine(); //this ise where i send the data to the logger after every succesful encrypt or decrypt.
            writer.println(encryptionOutput);
        }
    }
    
}
