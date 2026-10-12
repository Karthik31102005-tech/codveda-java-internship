
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 5000;
    private static final Set<ClientHandler> clients =
            Collections.synchronizedSet(new HashSet<ClientHandler>());

    public static void main(String[] args) {
        System.out.println("Chat server starting on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running. Waiting for clients...");

            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler client = new ClientHandler(socket);
                clients.add(client);
                client.start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    private static void broadcast(String message) {
        synchronized (clients) {
            for (ClientHandler client : clients) {
                client.sendMessage(message);
            }
        }
    }

    private static class ClientHandler extends Thread {
        private final Socket socket;
        private PrintWriter out;
        private String username = "User";

        ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void sendMessage(String message) {
            if (out != null) {
                out.println(message);
            }
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                out.println("Enter your username:");
                username = in.readLine();

                if (username == null || username.trim().isEmpty()) {
                    username = "User";
                }

                broadcast(username + " joined the chat.");

                String message;
                while ((message = in.readLine()) != null) {
                    if (message.equalsIgnoreCase("/exit")) {
                        break;
                    }

                    broadcast(username + ": " + message);
                }
            } catch (IOException e) {
                System.out.println("A client disconnected.");
            } finally {
                clients.remove(this);
                broadcast(username + " left the chat.");

                try {
                    socket.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
}
