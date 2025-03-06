import java.io.*;

public class child {
    public static void main(String[] args) {
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
        writer.println("hello");
        writer.println("STOP");






        reader.close();
        writer.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
