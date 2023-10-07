import java.io.*;
import java.net.*;

 class ChatClient {
    private static final String SERVER_IP = "192.168.229.65";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to the chat server. Type your messages:");

            String userInput;
            while ((userInput = consoleIn.readLine()) != null) {
                out.println(userInput);
                String serverResponse = in.readLine();
                System.out.println("Server says: " + serverResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}