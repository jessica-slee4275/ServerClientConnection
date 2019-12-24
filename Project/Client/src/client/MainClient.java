package client;

import java.io.IOException;
import java.net.Socket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class MainClient {
	
	public static void main(String[] args)
	{
		String ip = "";
		try(final DatagramSocket socket = new DatagramSocket()){
			  socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			  ip = socket.getLocalAddress().getHostAddress();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Socket c_socket = new Socket(ip, 8888);
			
			ReceiveThread rec_thread = new ReceiveThread();
			rec_thread.setSocket(c_socket);
			
			SendThread send_thread = new SendThread();
			send_thread.setSocket(c_socket);
			
			send_thread.start();
			rec_thread.start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
