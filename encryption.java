import java.util.Scanner;

public class encryption {

	static String holder ="";
		
		public static String myattempt(String s,String k,int chose) {
			String alphabet ="abcdefghijklmnopkrstuvwxyz";
			String input =s.toLowerCase();
			String key=k.toLowerCase();
			
			int keyTraker=0;
			int inputLoc=0;
			int keyLoc=0;
			String finalString="";
			for(int i=0;i<input.length();i++)
			{
				inputLoc=alphabet.indexOf(input.charAt(i));//get number of char in alphabet
				
				if(input.charAt(i)==' ') {
					finalString+=input.charAt(i);//if space add to final string 
					continue;
				}
	
				if(keyTraker>=key.length())//resets key runner
					keyTraker=0;
				
				
				if(keyTraker<key.length()) {//matches key index from alphabet to string letter
				keyLoc=alphabet.indexOf(key.charAt(keyTraker));
				keyTraker++;
				}
	
				
	
				int finalnum=0;
				if (chose==0)
				 finalnum=((inputLoc + keyLoc) % 26 + 26) % 26;//encryptor
				else if (chose==1)
					 finalnum=((inputLoc - keyLoc) % 26 + 26) % 26;//decrypt
	
					
	
				finalString+=alphabet.charAt(finalnum);
			
			}
			
			return finalString;
		}
		
		
			public static void encryptionProcc() {

				String paskey=""; 





				Scanner scanner = new Scanner(System.in);
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					holder=line;





					if("password".equals(line)){
						line = scanner.nextLine();
						paskey=line;
						System.out.flush(); 
					}



				if ("encrypt".equals(line)&&!paskey.equals("")){
					line = scanner.nextLine();
					String test1= myattempt(line,paskey,0);
			  		System.out.println(test1+"  encrypt");
					System.out.flush(); 
				}

				
				if ("decrypt".equals(line)&&!paskey.equals("")){
					line = scanner.nextLine();
					String test1= myattempt(line,paskey,1);
			  		System.out.println(test1+"  decrypt");
					System.out.flush(); 
				}




                if ("END".equals(line)) {
                    break; 
                }



				
                System.out.flush(); 
            }
		}








    public static void main(String[] args) {

		encryptionProcc();


        String plaintext = holder;
        String key = "KEY";
        int encript=0;
        int decrypt=1;
        
       String test1= myattempt(plaintext,key,encript);
       //System.out.println(test1+"  test");
       System.out.println();

       String test2=myattempt(test1,key,decrypt);
       //System.out.println(test2+"  test222");

        
    }
}
	
	