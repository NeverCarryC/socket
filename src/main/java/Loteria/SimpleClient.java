package Loteria;
import java.io.*;
import java.net.*;

public class SimpleClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // 服务器地址
        int port = 12345; // 服务器端口

        try (
                Socket socket = new Socket(serverAddress, port);
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.print("请输入5位数的彩票号码: ");
            String number = userInput.readLine(); // 读取用户输入

            if (number.matches("\\d{5}")) { // 确保输入是5位数字
                output.println(number); // 发送到服务器

                String response = input.readLine(); // 读取服务器返回的结果
                System.out.println("服务器回复: " + response);
            } else {
                System.out.println("输入错误！请输入5位数字！");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
