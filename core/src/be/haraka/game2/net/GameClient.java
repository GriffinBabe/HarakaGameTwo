package be.haraka.game2.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class GameClient extends Thread 
{
	private InetAddress ipAddress;
	private DatagramSocket socket;
	
	public GameClient(String ipAdress)
	{
		//Init the object, by giving the adress of the server app
		
			try 
			{
				this.socket = new DatagramSocket();
				this.ipAddress = InetAddress.getByName(ipAdress);
			} 
			
			catch (java.net.UnknownHostException e) 
			{
				e.printStackTrace();
			}
			
			catch (SocketException e1) 
			{
				e1.printStackTrace();
			}
			
			
		}
	
	public void run()
	{
		//Creating a packet of 1024 bytes that we are sending
		while(true)
		{
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			
			try 
			{
				socket.receive(packet);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			String message = new String(packet.getData());
			System.out.println("SERVER> " + message);
			
		}
	}
	
	public void sendData(byte[] data)
	{
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331);
		
		try 
		{
			socket.send(packet);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		
	}
}
	
	

