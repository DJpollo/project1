import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class child {
    static ArrayList<String>log= new ArrayList<String>();

   
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            
            if ("END".equals(line)) {
                break; 
            }
            log.add(line);
            System.out.println("Logged: " + log);
            System.out.flush(); 
    
    
    
    }
}
}

