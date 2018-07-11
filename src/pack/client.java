/*Login -id : asg6506
Name : Akshat Sunil Gawankar
Code was referenced from http://pirate.shu.edu/~wachsmut/Teaching/CSAS2214/Virtual/Lectures/chat-client-server.html
                         http://w1.weather.gov/xml/current_obs/
                         https://stackoverflow.com/questions/18590901/check-if-a-string-contains-numbers-java
                         https://stackoverflow.com/questions/26407040/case-insensitive-matching-in-java


*/


package pack;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.security.Timestamp;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.awt.event.ActionEvent;
import java.util.Date;
public class client {

	private JFrame frame;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					client window = new client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public client() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 725, 470);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEnterLatitude = new JLabel("Enter Latitude");
		lblEnterLatitude.setBounds(68, 25, 140, 33);
		frame.getContentPane().add(lblEnterLatitude);
		
		textField_1 = new JTextField();
		textField_1.setBounds(240, 31, 64, 21);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblEnterLongitude = new JLabel("Enter Longitude");
		lblEnterLongitude.setBounds(398, 31, 151, 21);
		frame.getContentPane().add(lblEnterLongitude);
		
		JLabel lblAnd = new JLabel("and");
		lblAnd.setBounds(340, 29, 38, 24);
		frame.getContentPane().add(lblAnd);
		
		textField_2 = new JTextField();
		textField_2.setBounds(592, 31, 107, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					
					String lat=null;
					String longi=null;
					
					 lat=textField_1.getText();
					 longi=textField_2.getText();
					if(lat.isEmpty())
					{
						
					JOptionPane.showMessageDialog(null, "Please Enter Latitude");
					}
					
					
					else if(longi.isEmpty())
					{
					JOptionPane.showMessageDialog(null, "Please Enter Longitude");
					}
					
					else
					{
					int soc=1230;
				Socket s=new Socket("localhost", soc);
				 //Create socket with Port number 
				System.out.println("Server connected succesfully");
				while(true)
				{
				DataInputStream din=new DataInputStream(s.getInputStream());  //Lets application to read primitive data types
				DataOutputStream dout=new DataOutputStream(s.getOutputStream());//Lets application to write data
				         //Get the string entered in TextField and store it in str
				
				
				Float  lattitude=Float.parseFloat(textField_1.getText());     //Store the data in latitude       
				Float longitude=Float.parseFloat(textField_2.getText());      //Store the data in longitude   
				String str=Float.toString(lattitude);
				String str1=Float.toString(longitude);
				dout.writeUTF(str);
				dout.writeUTF(str1);
				String temp=din.readUTF();
				textField_3.setText(temp);                                   //display temperature   
				String rel=din.readUTF();
				textField_4.setText(rel+"%");                               //display Humidity
				String wind=din.readUTF();                   
				textField_5.setText(wind);                                  //display Wind direction
				String pressure=din.readUTF();
				textField_6.setText(pressure);                              //display Pressure
				String dew=din.readUTF();
				textField_7.setText(dew);                                  //display Dew point
				s.close();                                                 //close socket
				
				
				}	
				}
			
				
				}
			
			
				
				catch(Exception e)
				{
					
				}
		
			}
		});
		
		/*initialize frame contents*/
		
		btnSearch.setBounds(303, 69, 113, 33);
		frame.getContentPane().add(btnSearch);
		
		JLabel lblTemperature = new JLabel("Temperature");
		lblTemperature.setBounds(92, 136, 123, 33);
		frame.getContentPane().add(lblTemperature);
		
		textField_3 = new JTextField();
		textField_3.setBounds(260, 139, 359, 27);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblRelativeHumidity = new JLabel("Relative humidity");
		lblRelativeHumidity.setBounds(92, 194, 144, 38);
		frame.getContentPane().add(lblRelativeHumidity);
		
		textField_4 = new JTextField();
		textField_4.setBounds(260, 197, 359, 33);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblWindSpeed = new JLabel("Wind Speed");
		lblWindSpeed.setBounds(92, 243, 116, 29);
		frame.getContentPane().add(lblWindSpeed);
		
		textField_5 = new JTextField();
		textField_5.setBounds(260, 241, 359, 33);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Pressure");
		lblNewLabel.setBounds(92, 296, 98, 27);
		frame.getContentPane().add(lblNewLabel);
		
		textField_6 = new JTextField();
		textField_6.setBounds(260, 285, 359, 33);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblDewPoint = new JLabel("Dew Point");
		lblDewPoint.setBounds(92, 334, 98, 44);
		frame.getContentPane().add(lblDewPoint);
		
		textField_7 = new JTextField();
		textField_7.setBounds(260, 330, 359, 33);
		frame.getContentPane().add(textField_7);
		textField_7.setColumns(10);
		
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date date = new Date();
		String date1 = formatter.format(date);
        			
		
		
		JLabel lblCurrentTime = new JLabel("Current Time :"+date1);
		lblCurrentTime.setBounds(260, 374, 359, 33);
		frame.getContentPane().add(lblCurrentTime);
		
		JButton btnRefresh = new JButton("REFRESH");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
				textField_7.setText("");
		
				
		
				
			}
		});
		btnRefresh.setBounds(339, 418, 135, 23);
		frame.getContentPane().add(btnRefresh);
	}
}

