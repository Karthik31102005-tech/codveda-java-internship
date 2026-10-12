
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(
                     socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            Thread receiver = new Thread(new Runnable() {
                public void run() {
                    try {
                        String message;
                        while ((message = in.readLine()) != null) {
                            System.out.println(message);
                        }
                    } catch (IOException e) {
                        System.out.println("Disconnected from server.");
                    }
                }
            });

            receiver.setDaemon(true);
            receiver.start();

            while (scanner.hasNextLine()) {
                String message = scanner.nextLine();
                out.println(message);

                if (message.equalsIgnoreCase("/exit")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(
                    "Could not connect. Start the server first. "
                    + e.getMessage());
        }
    }
}
