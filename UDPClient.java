import java.io.*;
import java.net.*;

public class UDPClient {

    // Main function begin
    public static void main(String[] args) throws IOException {

        // Create object that stores client message
        BufferedReader clientRead = new BufferedReader(new InputStreamReader(System.in));

        // Create and initialize object to store ip address of server
        InetAddress ip = InetAddress.getByName("127.0.0.1");

        // Create datagram socket object, which acts as pipeline to server
        DatagramSocket clientSocket = new DatagramSocket();

        // Create array of bytes to hold data being sent and received
        byte[] receiveBuffer = new byte[512];
        byte[] sendBuffer;

        // Let user know client is ready to receive message
        System.out.print("\nClient: ");

        // Read clients message and store in clientData
        String clientData = clientRead.readLine();

        // Prepare to send message by converting to bytes
        sendBuffer = clientData.getBytes();

        // Assemble packet to be sent to server
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, ip, 9999);

        // Send packet to server via socket pipeline
        clientSocket.send(sendPacket);

        // Create new packet object to receive message from server
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

        // set the timeout for 3 seconds
        clientSocket.setSoTimeout(3000);

        boolean messageReceived = true;

        // Receive data until timeout
        while (messageReceived) {
            try {

                // Receive data sent from server via socket pipeline
                clientSocket.receive(receivePacket);

                // Store data from server in string object
                String serverData = new String(receivePacket.getData());

                // Print message received from server
                System.out.println("\nServer: " + serverData.trim());

            }
            // Catch timeout exception
            catch (SocketTimeoutException e) {

                System.out.println("\n3 seconds reached!!! ");
                clientSocket.close();
                messageReceived = false;

            } catch (SocketException e) {

                System.out.println("Socket closed " + e);

            }

        } // End while loop

    } // End main function

} // End class UDPClient
