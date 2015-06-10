
import java.util.Scanner;
import java.lang.Runnable; 
import java.net.*;
import java.io.*;
class serverConnect {
		ServerSocket serverSocket = null; 
		Socket clientSocket = null;
		String receiveData,sendData;
		PrintWriter outputData ;
		BufferedReader inputData;
		serverConnect(){
			try {
        	serverSocket = new ServerSocket(5234);
        	clientSocket = serverSocket.accept();
        }
        catch (Exception e) {  
        	System.out.println("Server Bulid Error");
        	System.exit(1);
        }
		}
		void dealData(){
			try{  
			 outputData = new PrintWriter(clientSocket.getOutputStream(), true);
			 inputData = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			}catch (Exception e) {
				System.out.println("Error!"); 
				System.exit(0);
			}
		}
		boolean receiveMsg(){
			try{
				receiveData = inputData.readLine(); 
				if(receiveData.equals("shutdown")) return false ;
				System.out.println("Client say:"+ receiveData);
			}catch (Exception e) {
				System.out.println("receive Error~");
				System.exit(0);
			}
			return true;
		}
		String sendMsg(){
			Scanner sc = new Scanner(System.in); 
			sendData = sc.nextLine(); 
			outputData.println(sendData);  
			outputData.flush();
			System.out.println("I say:"+ sendData);
			return sendData;
		}
}
public class Server{
	public static void main(String[] args) {
		serverConnect connect = new serverConnect();
		Thread t  = new Thread(new Runnable(){
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