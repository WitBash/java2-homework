package HomeWork7;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientHandler {
    //    private Socket socket;
    private Server server;
    private Scanner sc;
    private PrintWriter pw;
    private String nick;

    public ClientHandler(Socket socket, Server server) {
        this.server = server;
//        this.socket = socket;

        try {
            sc = new Scanner(socket.getInputStream());
            pw = new PrintWriter(socket.getOutputStream(), true);
            new Thread(() -> {
                auth();
                System.out.println(nick + " handler waiting for new massages");
                while (socket.isConnected()) {
                    String s = sc.nextLine();
                    if (s.startsWith("/w")) {
                        privateMessage(nick, s);
                        continue;
                    }
                    if (s != null && s.equals("/exit"))
                        server.unsubscribe(this);
                    if (s != null && !s.isEmpty())
                        server.sendBroadcastMessage(nick + " : " + s);
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Wait command: "/auth login1 pass1
     */
    private void auth() {
        while (true) {
            if (!sc.hasNextLine()) continue;
            String s = sc.nextLine();
            if (s.startsWith("/auth")) {
                String[] commands = s.split(" ");
                if (commands.length >= 3) {
                    String login = commands[1];
                    String password = commands[2];
                    System.out.println("Try to login with " + login + " and " + password);
                    String nick = server.getAuthService().
                            authByLoginAndPassword(login, password);
                    if (nick == null) {
                        String msg = "Invalid login or password";
                        System.out.println(msg);
                        pw.println(msg);
                    } else if (server.isNickTaken(nick)) {
                        String msg = "Nick " + nick + " already taken!";
                        System.out.println(msg);
                        pw.println(msg);
                    } else {
                        this.nick = nick;
                        String msg = "Auth ok!";
                        System.out.println(msg);
                        pw.println(msg);
                        server.subscribe(this);
                        break;
                    }
                }
            } else {
                pw.println("Invalid command!");
            }
        }
    }

    private void privateMessage(String senderNick, String s) {
        List<String> commands = new ArrayList<>();
        String[] com = s.split(" ");
        for (int i = 0; i < com.length; i++) {
            commands.add(i, com[i]);
        }
        if (commands.size() >= 3) {
            String recipientNick = commands.get(1);
            commands.remove(0);
            commands.remove(0);
            String[] a = new String[commands.size()];
            for (int i = 0; i < commands.size(); i++) {
                a[i] = commands.get(i);
            }
            String pMsg = getMessage(a);
            if (recipientNick == null) {
                String msg = "Invalid nick";
                System.out.println(msg);
                pw.println(msg);
            } else if (recipientNick.equals("nick1") || recipientNick.equals("nick2") || recipientNick.equals("nick3")) {
                server.sendPersonalMessage(recipientNick, senderNick, pMsg);

            }
        }
    }

    private String getMessage(String[] a) {
        String personalMessage = "";
        for (int i = 0; i < a.length; i++) {
            personalMessage += a[i] + " ";
        }
        return personalMessage;
    }

    public void sendMessage(String msg) {
        pw.println(msg);
    }

    public String getNick() {
        return nick;
    }
}
