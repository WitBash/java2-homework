package HomeWork7;

import HomeWork7_1.AuthService;
import HomeWork7_1.BaseAuthService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private ServerSocket serverSocket;
    private AuthService authService;
    private List<ClientHandler> clients = new ArrayList<>();

    public Server(AuthService authService) {
        this.authService = authService;
        try {
            serverSocket = new ServerSocket(8189);
            System.out.println("Сервер запущен, ожидаем подключения...");
        } catch (IOException e) {
            System.out.println("Ошибка инициализации сервера");
            close();
        }
    }

    public void close() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        AuthService baseAuthService = new BaseAuthService();
        Server server = new Server(baseAuthService);
        server.start();
    }

    private void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                sendBroadcastMessage("Новый клиент подключился");
                System.out.println("Клиент подключился");
                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                clients.add(clientHandler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendBroadcastMessage(String msg) {
        for (ClientHandler client : clients) {
            client.sendMessage(msg);
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public boolean isNickTaken(String nick) {
        for (ClientHandler client : clients) {
            if(nick.equals(client.getNick())){
                return true;
            }
        }
        return false;
    }
    public void subscribe(ClientHandler clientHandler) {
        String msg = "Клиент " + clientHandler.getNick() + " подключился";
        sendBroadcastMessage(msg);
        System.out.println(msg);
        clients.add(clientHandler);
    }

    public void unsubscribe(ClientHandler clientHandler) {
        String msg = "Клиент " + clientHandler.getNick() + " отключился";
        sendBroadcastMessage(msg);
        System.out.println(msg);
        clients.remove(clientHandler);
    }

    public void sendPersonalMessage(String recipientNick,String senderNick,String pMsg) {
        for (ClientHandler client: clients) {
            if (client.getNick().equals(senderNick))
                client.sendMessage(senderNick +": "+pMsg);
            if (client.getNick().equals(recipientNick))
                client.sendMessage(senderNick +": "+pMsg);

        }

    }
}

