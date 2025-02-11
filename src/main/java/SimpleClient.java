import java.io.*;
import java.net.*;

public class SimpleClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // 服务器的 IP 地址（本机）
        int port = 12345; // 服务器的端口

        try (
                Socket socket = new Socket(serverAddress, port); // 连接服务器
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true); // 发送数据
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 读取服务器返回的信息
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in)) // 读取用户输入
        ) {
            System.out.print("输入要发送的消息: ");
            String message = userInput.readLine(); // 读取用户输入的信息

            output.println(message); // 发送给服务器

            String response = input.readLine(); // 读取服务器返回的信息
            System.out.println("服务器回复: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
