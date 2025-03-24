import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*; 


public class logger {

    static ArrayList<String>log= new ArrayList<String>();


    public static void main(String[] args) {
        loggerProcc();
            }



        public static void loggerProcc() {
            boolean del=false;//tried making boolean to check if del == true then delete everything from file to clear it
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                
                if ("quit".equals(line)) {
                    saveLogToFile(true);//if true then it should delete everything from the file. dont think this works due to every run after each other if i call history
                    System.exit(0);  // Force exit                                                                                  then it still has the previous history
                                                                                                                     //altough it doesnt interfere with the actual running of the program
                }

                if (!"history".equals(line)) {
                    log.add(reader(line));
                    saveLogToFile(del);
                }

                System.out.flush(); 
            }
        }
    




        public static void saveLogToFile(boolean del) {
            try (PrintWriter save = new PrintWriter(new FileWriter("log.txt"))) {
                if (del) {
                    save.close();  //if true then delete everything from the file (altough dont think this does anything)
                    return;
                }
        
                for (String entry : log) {
                    save.println(entry);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
                
                
     public static String reader(String s){

            String action ="[";
            String rest =" ";
            Date date = new Date(); 
            String time= date.toString();
            int firstblank=0;
                                                                        //algorithm to store the inputs with time and format of [action] rest
            for (int i =0;i<s.length();i++){
            char ch = s.charAt(i);
            
            if (ch!=' '&& firstblank==0)
            action+=ch;	
            else if (firstblank==0) {
                firstblank++;
                action+=']';
            }
            
            
            if(firstblank==1)
            rest+=ch;
            

        }
        

        
        String fullstr="";
        fullstr=time+action+rest;
    
        return fullstr;    
    }

    

}


