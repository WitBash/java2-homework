package HomeWork6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static Scanner scClient = new Scanner(System.in);
    private final String SERVER_ADDR = "localhost" ;
    private final int SERVER_PORT = 8189 ;
    private Socket sock ;
    private Scanner in ;
    private PrintWriter out ;

    public Client () {

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
                            if (w.equals("end")) break;
                            System.out.println(w);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String msgClient = scClient.nextLine();
                    out.println(msgClient);
                }
            }
        }).start();
    }
}
