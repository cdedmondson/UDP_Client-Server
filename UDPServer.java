import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDPServer {

    // Main function begin
    public static void main(String[] args) throws Exception {

        // Create server socket object that acts as pipeline for communication
        DatagramSocket serverSocket = new DatagramSocket(9999);

        // Create array of bytes to hold data being sent and received
        byte[] receiveBuffer = new byte[512];
        byte[] sendBuffer;

        // Create datagram packet object that receives incoming data from client
        DatagramPacket receiveDatagramPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

        // Receive data from datagram packet via server socket pipeline port 9999
        serverSocket.receive(receiveDatagramPacket);

        // Store ip address from client datagram packet
        InetAddress IP = receiveDatagramPacket.getAddress();

        // Store port number from client datagram packet
        int portNumber = receiveDatagramPacket.getPort();

        // Create string object to hold all data received from client datagram packet
        String clientData = new String(receiveDatagramPacket.getData());

        // Create time stamp
        String timeStamp = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date());

        // Take data sent by client and add time stamp to end of data
        String serverData = clientData.trim() + " " + timeStamp;

        // Prepare data to be sent by server by converting string to bytes
        sendBuffer = serverData.getBytes();

        // Assemble packet to be sent back to client
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, IP, portNumber);

        // Send packet to client via socket pipeline
        serverSocket.send(sendPacket);

    } // End main function

} // End class UDPServer