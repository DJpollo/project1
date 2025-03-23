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
            boolean del=false;
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                System.out.println("Received in logger: " + line);
                
                if ("quit".equals(line)) {
                    saveLogToFile(true);
                    System.exit(0);  // Force exit
                }

                if (!"history".equals(line)) {
                    log.add(reader(line));
                    saveLogToFile(del);
                }




                //  log.add(reader(line));
               // System.out.println(" " + log);
                System.out.flush(); 
            }
        }
    




        public static void saveLogToFile(boolean del) {
            try (PrintWriter save = new PrintWriter(new FileWriter("log.txt"))) {
                if (del) {
                    save.close();  // Clears the file properly
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
        //System.out.println(fullstr);



    
        return fullstr;    
    }


        
        
        

}




            
    
    
    
    
    






    



