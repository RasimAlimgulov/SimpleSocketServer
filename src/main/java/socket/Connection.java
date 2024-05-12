package socket;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/* 
Сокетный сервер и клиент
*/

public class Connection implements Closeable {
    private final Socket socket;
    private volatile ObjectInputStream in;
    private volatile ObjectOutputStream out;

    public Connection(Socket socket) throws Exception {
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());

    }

    public void send(String message) throws Exception {
        synchronized (out){
        out.writeObject(message);}
    }

    public String receive() throws Exception {
       synchronized (in) {
           return (String) in.readObject();
       }
       }

    @Override
    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}
