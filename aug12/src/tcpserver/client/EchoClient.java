// package aug12.src.tcpserver.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client class, sending and
 * receiving messages from the
 * server. 
 */
public class EchoClient {

  /**
   * main function to run client,
   * Connecting client to server.
   */
  public static void main(String[] args) {
    String hostServer = "localhost";
    int serverPort = 12345;
    
    try {
      Socket socket = new Socket(hostServer, serverPort);
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      Scanner scn = new Scanner(System.in);

      System.out.println("Client connection is made with the server");

      String msg;

      while (true) {
        System.out.println("Enter the line: ");
        msg = scn.nextLine();
        
        if ("exit".equals(msg)) {
          break;
        }

        out.println(msg);
        System.out.println("Server replied back: " + in.readLine());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
