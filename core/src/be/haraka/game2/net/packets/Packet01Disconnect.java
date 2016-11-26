package be.haraka.game2.net.packets;

import be.haraka.game2.net.GameClient;

public class Packet01Disconnect extends Packet {
	
	private String username;

	public Packet01Disconnect(byte[] data) {
		super(01);
		this.username = readData(data);
	}
	
	public Packet01Disconnect(String username){
		super(01);
		this.username = username;
	}

	@Override
	public void writeData(GameClient client) {
		client.sendData(getData());
		
	}

	@Override
	public byte[] getData() {
		return ("01" + this.username).getBytes();
	}
	
	

}
