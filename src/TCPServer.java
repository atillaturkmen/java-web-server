import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A multithreaded TCP Server implemented with java.net libraries
 * Writes incoming data from the clients into a file
 */
class TCPServer {
    public static void main(String[] argv) throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(6789);

        // create output file if it doesn't exist
        File outputFile = new File("db.txt");
        outputFile.createNewFile();

        // create a lock to prevent data races between threads while writing into the file
        BinarySemaphore lock = new BinarySemaphore(true);

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            new Thread(new ServerThread(connectionSocket, lock)).start();
        }
    }
}
