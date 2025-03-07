import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*; 


public class logger {

   




    public static void main(String[] args) {
        loggerProcc();//made static maybe not?
                String test ="START logging started ";
                String test2 ="ENCRYPT HELLO ";
        
                ArrayList<String>log= new ArrayList<String>();
                log.add(reader(test));
                log.add(reader(test2));
        
                System.out.println(log);
                
        
                
                
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
        
        
        
        
        
            public static void loggerProcc()
    {
        try { 
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;

        while ((input = reader.readLine()) != null) {
            if (input.equals("STOP")) 
                break; 
            
            System.out.println("Child received from Parent: " + input);
        }

        // Send data 
        PrintWriter writer = new PrintWriter(System.out, true);
        writer.println("hello driver");
        writer.println("STOP");






        reader.close();
        writer.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    }






    




