import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 读取客户端发送的消息
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true) // 发送消息给客户端
        ) {
            String message = input.readLine(); // 读取客户端数据
            System.out.println("收到客户端信息：" + message);

            output.println("服务器收到: " + message); // 发送响应
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close(); // 关闭连接
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
