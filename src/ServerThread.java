import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketAddress;

public class ServerThread implements Runnable {
    Socket connectionSocket;
    BinarySemaphore writerLock;

    public ServerThread(Socket connectionSocket, BinarySemaphore lock) {
        this.connectionSocket = connectionSocket;
        this.writerLock = lock;
    }

    @Override
    public void run() {
        try {
            SocketAddress clientAddress = connectionSocket.getRemoteSocketAddress();
            System.out.println(clientAddress + " opened a connection in thread " + this);
            // create buffers for listening and writing into the socket
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            // listen to the connection until client closes it
            while (true) {
                String clientSentence;
                try {
                    clientSentence = inFromClient.readLine();
                } catch (Exception e) {
                    System.out.println(clientAddress + " closed the connection.");
                    break;
                }
                System.out.println(clientAddress + " sent: " + clientSentence);
                // start of the critical section
                writerLock.P();
                FileWriter writer = new FileWriter("db.txt", true);
                writer.write(clientAddress + " " + clientSentence + "\n");
                writer.close();
                writerLock.V();
                // end of critical section
                outToClient.writeBytes("OK\n");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
