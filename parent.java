import java.io.*;

public class parent {
    public static void main(String[] args) {
        try {
            ProcessBuilder child = new ProcessBuilder("java", "child");
            Process child1Process = child.start();

            InputStream childinput = child1Process.getInputStream();
            OutputStream childoutput = child1Process.getOutputStream();

            // Send data 
            PrintWriter writer1 = new PrintWriter(childoutput, true);
            writer1.println("Hello Child ");
            writer1.println("STOP");

            

            // Read data 
            BufferedReader reader = new BufferedReader(new InputStreamReader(childinput));
            String input;
            while ((input = reader.readLine()) != null) {
                System.out.println("Parent received " + input);
                
                if (input.equals("STOP")) 
                break;
                
            }

            // Wait 
            child1Process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
