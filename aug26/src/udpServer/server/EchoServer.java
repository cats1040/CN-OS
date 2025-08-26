import java.net.*;

class EchoServer {
  public static void main(String[] args) throws Exception {
    DatagramSocket d = new DatagramSocket(9090); // 9090 port number
    byte[] received = new byte[1024];
      DatagramPacket dp = new DatagramPacket(received, received.length);
      /**
       * Datagram packets are used to implement a connectionless packet 
       * delivery service. Each message is routed from one machine to 
       * another based solely on information contained within that packet.
       * Multiple packets sent from one machine to another might be routed
       * differently, and might arrive in any order. Packet delivery is 
       * not guaranteed.
       */
    while (true) { 
      d.receive(dp);
      InetAddress ipAddress = dp.getAddress();
      String message = new String(dp.getData());
      System.out.println(ipAddress + " " +  message);

      // get client port, if we want to send them a message
      InetAddress clientAddress = dp.getAddress();
      int clientPort = dp.getPort();

      String str = "Echo: " + message;
      byte[] sendData = str.getBytes();

      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);

      d.send(sendPacket);      
    }
  }
}
