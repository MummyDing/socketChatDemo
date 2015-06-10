import java.lang.Runnable; 
import java.io.*; 
import java.net.*;
class clientConnect{
	 	Socket client = null;
        BufferedReader inputData = null;   
        PrintWriter outputData = null;
        String receiveData ,sendData ;

        clientConnect(){
        	 try {
            client = new Socket("127.0.0.1", 5234);  
            dealData();
            }
        	catch (Exception e) {
        		System.out.println("I cann't Connect!");
            	System.exit(1);
        	}
    	} 
    	void dealData(){
    		try{
    		outputData = new PrintWriter(client.getOutputStream(), true);
    		inputData = new BufferedReader(new InputStreamReader(client.getInputStream())); 
             
 			 sendMsg();
 			 receiveMsg();	
    		}catch (Exception e) {
    			System.out.println("Error ~");
    		}
    		 
    	}
    	void sendMsg(){
    		outputData.println("Hello Server");
    	}
    	void receiveMsg(){
    		try{
    		receiveData =  inputData.readLine(); 
    		System.out.println("Server said "+receiveData);  	
    	}catch (Exception e) {
    		System.out.println("Error");
    	}
    		
    	}
}
public class Client  {
	public static void main(String[] args) { 
		clientConnect connecte = new clientConnect();  
	}
}