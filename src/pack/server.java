/*Login -id : asg6506
Name : Akshat Sunil Gawankar
Code was referenced from http://pirate.shu.edu/~wachsmut/Teaching/CSAS2214/Virtual/Lectures/chat-client-server.html
                         http://w1.weather.gov/xml/current_obs/
                         https://stackoverflow.com/questions/18590901/check-if-a-string-contains-numbers-java
                         https://stackoverflow.com/questions/26407040/case-insensitive-matching-in-java


*/

package pack;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Pattern;

public class server {
	static ServerSocket serverSocket = null;
public static void main(String[] args) throws IOException
    
    {
    	 try {
            serverSocket = new ServerSocket(1230);           //Defining port number.
        } 
        catch (IOException e) 
        {
            
        }

        while(true){                                          //Handling multiple clients by creating Thread for each client
            Socket clientSocket = serverSocket.accept();      //Server accepting client connection
            System.out.println("Client successfully connected to server");
            MiniServer mini = new MiniServer(clientSocket);
            mini.start();                                    //Thread started
        }
        
    }
}
	
class MiniServer extends Thread                           //Thread class

{

   private Socket clientSocket = null;
   
   public MiniServer(Socket socket) {                    //Constructor invoked
   	

       super("MiniServer");                             //Calling super constructor
      clientSocket = socket;
       
   }

   public void run(){                                  //run method.This method is invoked when thread starts
   	
   	try {
   		
   		DataOutputStream dout=new DataOutputStream(clientSocket.getOutputStream());
	    System.out.println("Client successfully connected to server");
	    String a="http://w1.weather.gov/xml/current_obs/index.xml";               //url for the weather website
		URL url=new URL(a);
	//Scanner sc=new Scanner("http://w1.weather.gov/xml/current_obs/index.xml");    //url for the weather  website
		URLConnection conn = url.openConnection();
	    DataInputStream din=new DataInputStream(clientSocket.getInputStream());
	   
	   System.out.println("here");
	    String str=din.readUTF();                                                 //Reading the data sent from the client
	    System.out.println("Latitude is : "+str);                               
	    String str1=din.readUTF();
	    System.out.println("Longitude is : "+str1);
	    String lat=str+str1;
	    BufferedReader br = new BufferedReader(
	            new InputStreamReader(conn.getInputStream()));
		System.out.println(lat);
		String b="<latitude>"+str+"</latitude>";
		String inputLine;
		while ((inputLine = br.readLine()) != null) {                                //Reading the contents of the xml file
		 
		 if(inputLine.contains("<latitude>"))                                      //if file contains "latitude"
		 {
			
				 while(Pattern.compile(Pattern.quote("<latitude>"+str),Pattern.CASE_INSENSITIVE).matcher(inputLine).find())    //match the pattern with the xml file
				 {
			 
					 inputLine=br.readLine();
					 if(Pattern.compile(Pattern.quote("<longitude>"+str1),Pattern.CASE_INSENSITIVE).matcher(inputLine).find())  //math the pattern with longitude
					 {	 
					 
			 int i=0;
			 
			 while((inputLine= br.readLine()) !=null && i!=2){
				 ++i;}
				 	String str5[]=inputLine.split("<xml_url>");
				 	String str2[]=str5[1].split("</xml_url>");
				 String a1=str2[0];
				 	 
				 
				 	 
				 	url=new URL(a1);
				 	URLConnection conn1=url.openConnection();
				 	
				 	BufferedReader br1=new BufferedReader(new InputStreamReader(conn1.getInputStream()));
				 	String inputLine1;
				 	while((inputLine1 = br1.readLine())!=null)
				 	{
				 		
				 		if(inputLine1.contains("<temperature_string>"))                                            //Displaying temperature
				 		{
				 			String str3[]=inputLine1.split("<temperature_string>");
				 			String str4[]=str3[1].split("</temperature_string>");
				 			String a3=str4[0];
				 			System.out.println("Temperature is :"+a3);
				 			dout.writeUTF(a3);
				 		}
				 		
				 		if(inputLine1.contains("<relative_humidity>"))                                           //Displaying Relative Humidity
				 		{
				 			String str3[]=inputLine1.split("<relative_humidity>");
				 			String str4[]=str3[1].split("</relative_humidity>");
				 			String a2=str4[0];
				 			System.out.println("Relative Humidity is :"+a2);
				 			dout.writeUTF(a2);
				 		}
				 		
				 		
				 		if(inputLine1.contains("<dewpoint_string>"))                                           //Displaying Dew Point
				 		{
				 			String str3[]=inputLine1.split("<dewpoint_string>");
				 			String str4[]=str3[1].split("</dewpoint_string>");
				 			String a2=str4[0];
				 			System.out.println("Dew Point is :"+a2);
				 			dout.writeUTF(a2);
				 		}
				 		
				 		if(inputLine1.contains("<wind_string>"))                                             //Display Wind Speed and Direction
				 		{
				 			String str3[]=inputLine1.split("<wind_string>");
				 			String str4[]=str3[1].split("</wind_string>");
				 			String a2=str4[0];
				 			System.out.println("Wind Speed and Direction is :"+a2);
				 			dout.writeUTF(a2);
				 		}
				 		
				 		if(inputLine1.contains("<pressure_string>"))                                         //Display  Msl Pressure
				 		{
				 			String str3[]=inputLine1.split("<pressure_string>");
				 			String str4[]=str3[1].split("</pressure_string>");
				 			String a2=str4[0];
				 			System.out.println("Msl Pressure is :"+a2);
				 			dout.writeUTF(a2);
				 		}
				 		
				 		
				 		
				 	}
				 		
				 	
				 	
			 }
		 }
		 }
		
		 }
	    
	    
		
	
		
		
	   
		
		
		}
	
   		
   	
   	
   	catch(Exception e)
   	{
   		
   	}
}
}