package socket.client;

import socket.Connection;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final Scanner sc=new Scanner(System.in);
    private static Connection connection;

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8080);
Thread thread=new Thread(new Runnable() {
    @Override
    public void run() {
        try {
            connection = new Connection(socket);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        while (true){
            try {
                System.out.println("Введите сообщение серверу");
                String message=sc.nextLine();
                if (message.equals("exit")) break;
                connection.send(message);
                System.out.println("Ответ сервера: "+connection.receive());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
});
thread.start();



    }
}

