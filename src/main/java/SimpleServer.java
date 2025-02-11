import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
    public static void main(String[] args) {
        int port = 12345; // 服务器监听的端口

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("服务器启动，等待客户端连接...");

            while (true) {  // 让服务器一直运行
                Socket clientSocket = serverSocket.accept(); // 等待客户端连接
                System.out.println("客户端已连接：" + clientSocket.getInetAddress());

                // 启动一个线程处理客户端
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
