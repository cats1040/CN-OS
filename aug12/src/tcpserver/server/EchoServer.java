// package aug12.src.tcpserver.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Creating a Server that listens
 * to its clients, and sends back
 * messages.
 */
public class EchoServer {
  /**
   * main function for server.
   */
  public static void main(String[] args) {
    int port = 12345;

    ServerSocket server = null;

    try {
      server = new ServerSocket(port);
      System.out.println(
          "We are able to create the object for the ServerScoket on port " + port);
    } catch (Exception e) {
      System.out.println(
          "We are not able to create the object for the ServerSocket on port " + port);
    }

    while (true) {
      try {
        Socket client = server.accept();
        System.out.println("Client Address is: " + client.getRemoteSocketAddress());

        Thread thread = new Thread(() -> handleClient(client));
        thread.start();
      } catch (Exception e) {
        System.out.println("Getting Error while accepting the connection.");
      }
    }
  }

  /**
   * Method to handle client operations.
   * Read and write.
   */
  public static void handleClient(Socket client) {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
      PrintWriter out = new PrintWriter(client.getOutputStream(), true);

      {
        String line;
        while ((line = in.readLine()) != null) {
          System.out.println("Received this from Client: " + line);
          out.println("Echo back: " + line);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Closing the socket carefully.
   */
  public void closeConnection(Socket client) {
    try {
      client.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
