import java.util.Scanner;
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
            outputData.println("OK");
            }
        	catch (Exception e) {
        		System.out.println("I cann't Connecte!");
            	System.exit(0);
        	}
    	} 
    	void dealData(){
    		try{
    		outputData = new PrintWriter(client.getOutputStream(), true);
    		inputData = new BufferedReader(new InputStreamReader(client.getInputStream())); 
    		}catch (Exception e) {
    			System.out.println("Error ~");
                System.exit(0);
    		} 
    	}
    	String sendMsg(){
            Scanner sc = new Scanner(System.in);
            sendData = sc.nextLine();
    		outputData.println(sendData); 
            outputData.flush();
            System.out.println("I say:"+ sendData);
            return sendData;
    	}
    	boolean receiveMsg(){
    		try{
    		receiveData =  inputData.readLine(); 
            if(receiveData.equals("shutdown")) return false;
    		System.out.println("Server said "+receiveData);  	
    	    }catch (Exception e) {
    		  System.out.println("Error");
              System.exit(0);
    	    }	
            return true;
    	}
}
public class Client  {
	public static void main(String[] args) {
		clientConnect connect = new clientConnect();
		Thread t = new Thread(new Runnable(){
            public void run(){
               while(true){
                connect.dealData();
                if(connect.receiveMsg() == false){
                    System.out.println("SHUTDOWN!!!");
                    System.exit(0);
                }
                }
            }
        });
       t.start();
        while(connect.sendMsg().equals("shutdown") == false){ 
        }
        System.exit(0);
	}
}