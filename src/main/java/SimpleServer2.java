import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer2 {
    public static void main(String[] args) {
        int port = 12345;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("El servidor está iniciando, esperando el petion del cliente...");

            while(true){
                // Socket 和 ServerSocket 这两个class的区别
                // Socket 的 getInetAddress()？
                Socket clientSocket = serverSocket.accept();
                System.out.println("Servidor ha aceptado el petion del cliente: " + clientSocket.getInetAddress());

                new Thread(new Runnable() {
                    private Socket socket;


                    @Override
                    public void run() {

                    }
                });
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
