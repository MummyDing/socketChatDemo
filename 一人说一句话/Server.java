
import java.lang.Runnable; 
import java.net.*;
import java.io.*;
class serverConnect {
		ServerSocket serverSocket = null; 
		Socket clientSocket = null;
		String receiveData,sendData;
		serverConnect(){
			try {
        	serverSocket = new ServerSocket(5234);
        	clientSocket = serverSocket.accept();
        	dealData(); 
        }
        catch (Exception e) {  
        	System.out.println("Server Bulid Error");
        	System.exit(1);
        }
		}
		void dealData(){
			try{
			PrintWriter outputData = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader inputData = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			receiveData = inputData.readLine();  
			System.out.println("Client say:"+ receiveData);
			outputData.println("Ha ha , I got your info: "+receiveData);  
			}catch (Exception e) {
				System.out.println("Error!");
			}
		}
}
public class Server{
	public static void main(String[] args) { 
		serverConnect connecte = new serverConnect();		 
	}
}