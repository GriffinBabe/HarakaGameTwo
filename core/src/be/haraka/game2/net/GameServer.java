package be.haraka.game2.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class GameServer extends Thread
{
	private DatagramSocket socket;
	
	public GameServer()
	{
		//Init the object, creating a socket$
		
			try 
			{
				this.socket = new DatagramSocket(1331);
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
			
			if (message.trim().equalsIgnoreCase("ping"))
			{
				System.out.println("CLIENT ["+packet.getAddress().getHostAddress()+":"+packet.getPort()+"]> " + message);
				sendData("pong".getBytes(), packet.getAddress(), packet.getPort());
			}
			
		}
	}
	
	public void sendData(byte[] data,InetAddress ipAddress, int port)
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
