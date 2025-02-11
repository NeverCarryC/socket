import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler2 {
    private Socket socket;

    public ClientHandler2(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try {


            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);

            String message = input.readLine();
            System.out.println("received cliente´message: " + message);

            output.println("servidor received: " + message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
