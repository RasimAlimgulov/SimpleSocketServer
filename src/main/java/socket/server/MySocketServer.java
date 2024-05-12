package socket.server;

import socket.Connection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MySocketServer {

    public static void main(String[] args) throws Exception {

        Thread thread= new Thread(new Runnable() {
            @Override
            public void run() {
                try(
                    ServerSocket serverSocket = new ServerSocket(8080);
                    Socket socket = serverSocket.accept();
                    Connection connection=new Connection(socket);
                ){
                while (true) {
                    String message=(connection.receive());
                    System.out.println(message);
                    connection.send("Fuck you!!!");
                }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();


    }
}
