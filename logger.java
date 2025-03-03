import java.util.*; 


public class logger {

   




    public static void main(String[] args) {
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
    



}
