package HomeWork8;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ConsoleClient {
    private static final String SERVER_ADDR = "localhost";
    private static final int SERVER_PORT = 8189;

    private static Scanner consoleScanner = new Scanner(System.in);

    private Socket sock;
    private Scanner in;
    private PrintWriter out;

    public static void main(String[] args) {
        new ConsoleClient();
    }

    public ConsoleClient() {
        initConnection();
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
                            System.out.println(w);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }).start();
        scanConsole();
    }

    private void scanConsole() {
        while (true) {
            String msg = consoleScanner.nextLine();
            if (msg != null && !msg.isEmpty()) {
                if (msg.equals("end")) break;
                out.println(msg);
            }
        }
    }
}


