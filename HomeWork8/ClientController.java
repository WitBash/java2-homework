package HomeWork8;

import HomeWork8_2.AuthMessage;
import HomeWork8_2.Message;
import HomeWork8_2.MessageType;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class ClientController implements Controller {
    private static final String SERVER_ADDR = "localhost";
    private static final int SERVER_PORT = 8189;
    private ClientUi ui;

    private Socket sock;
    private Scanner in;
    private PrintWriter out;

    public ClientController() {
        initConnection();
    }

    public void showUi(ClientUi ui) {
        this.ui = ui;
        ui.showUi();
    }

    @Override
    public void sendMessage(AuthMessage authMessage) {
        out.println(authMessage.toString());
    }

    private void initConnection() {
        try {
            sock = new Socket(SERVER_ADDR, SERVER_PORT);
            in = new Scanner(sock.getInputStream());
            out = new PrintWriter(sock.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (in.hasNext()) {
                            String w = in.nextLine();
                            if (w.startsWith("end session")) break;
                            ui.addMessage(w);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }).start();
    }

    @Override
    public void sendMessage(String msg) {
        out.println(msg);
    }

    @Override
    public void closeConnection() {
        try {
            out.println("end");
            sock.close();
            out.close();
            in.close();
        } catch (IOException exc) {
        }
    }
}


