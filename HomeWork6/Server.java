package HomeWork6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static Scanner scServer = new Scanner(System.in);
    private static PrintWriter pw;

    public static void main(String[] args) {
        ServerSocket serv = null;
        Socket sock = null;
        try {
            serv = new ServerSocket(8189);
            System.out.println("Сервер запущен, ожидаем подключения...");
            sock = serv.accept();
            System.out.println("Клиент подключился");
            Scanner scClient = new Scanner(sock.getInputStream());
            pw = new PrintWriter(sock.getOutputStream(), true);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sendMsgServer();
                }
            }).start();
            while (true) {
                String str = scClient.nextLine();
                if (str.equals("end")) break;
                System.out.println("Client: " + str);
            }
        } catch (IOException e) {
            System.out.println("Ошибка инициализации сервера");
        } finally {
            try {
                serv.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void sendMsgServer() {
        while (true) {
            String msg = scServer.nextLine();
            if (msg.equals("end")) break;
            pw.println("Server:" + msg);

        }
    }
}

