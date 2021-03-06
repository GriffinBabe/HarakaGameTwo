package be.haraka.game2.net.packets;

import be.haraka.game2.net.GameClient;

public abstract class Packet {
	
	public static enum PacketTypes {
		
		INVALID(-01), LOGIN(00), DISCONNECT(01);
		private int packetId;
		
		private PacketTypes (int packetId) {
			this.packetId = packetId;
		}
		
		public int getId() {
			return packetId;
		}
	}
	
	public byte packetId;
	
	public Packet(int packetId){
		this.packetId = (byte) packetId;
	}
	
	public abstract void writeData(GameClient client);
	
	//Fonction qui lit les deux premiers digits du packet
	public String readData(byte[] data) {
		String message = new String(data).trim();
		return message.substring(2);
	}
	
	public abstract byte[] getData();
	
	//Fonction qui v�rifie si le paquet est valide, dans le cas �cheant elle renvoye le packet invalide
	public static PacketTypes lookupPacket(int id) {
		for (PacketTypes p : PacketTypes.values()) {
			if (p.getId() == id) {
				return p;
			}
		}
		
		return PacketTypes.INVALID;
	}

}
