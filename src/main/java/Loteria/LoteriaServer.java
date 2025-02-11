package Loteria;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class LoteriaServer {
    private static final String FILE_PATH = "src/main/java/Loteria/loteria.txt"; // 中奖号码存储文件
    private static String winningNumber; // 中奖号码（全局变量）

    public static void main(String[] args) {
        int port = 12345; // 服务器监听端口

        // 读取中奖号码
        loadWinningNumber();

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("服务器启动，等待客户端连接...");

            while (true) {
                Socket clientSocket = serverSocket.accept(); // 等待客户端连接
                System.out.println("客户端已连接：" + clientSocket.getInetAddress());

                // 使用线程处理客户端请求
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 读取中奖号码
    private static void loadWinningNumber() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            winningNumber = reader.readLine().trim(); // 读取第一行中奖号码
            System.out.println("今日中奖号码：" + winningNumber);
        } catch (IOException e) {
            System.out.println("读取中奖号码失败：" + e.getMessage());
            winningNumber = "00000"; // 设定一个默认值，避免出错
        }
    }

    // 获取中奖号码
    public static synchronized String getWinningNumber() {
        return winningNumber;
    }
}
