package Loteria;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String userNumber = input.readLine(); // 读取客户端号码
            System.out.println("收到客户端号码：" + userNumber);

            // 读取服务器的中奖号码
            String winningNumber = LoteriaServer.getWinningNumber();

            // 判断是否中奖
            String result = userNumber.equals(winningNumber) ? "🎉 恭喜！你中奖了！" : "😞 很遗憾，你没中奖。";
            output.println(result); // 发送结果给客户端
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
