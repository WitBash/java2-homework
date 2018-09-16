package HomeWork8;

import HomeWork8_2.*;

import java.io.IOException;
import java.net.Socket;


public class ClientHandler {
    private Server server;
    private String nick;
    private Channel channel;
    private Socket socket;

    public ClientHandler(Socket socket, Server server) {
        this.server = server;
        this.socket = socket;
        try {
            channel = ChannelBase.of(socket);
            new Thread(() -> {
                waitingForAuthorization();
                System.out.println(nick + " handler waiting for new massages");
                while (socket.isConnected()) {
                    Message msg = channel.getMessage();
                    if (msg == null) continue;
                    switch (msg.getType()) {
                        case EXIT_COMMAND:
                            server.unsubscribe(this);
                            break;
                        case PRIVATE_MESSAGE:
                            sendPrivateMessage(msg.getBody());
                            break;
                        case BROADCAST_CHAT:
                            server.sendBroadcastMessage(nick + " : " + msg.getBody());
                            break;
                        default:
                            System.out.println("Invalid type message");
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void waitingForAuthorization() {
        Thread authorizationThread = new Thread(new Runnable() {
            @Override
            public void run() {
                auth();
            }
        });
        authorizationThread.start();
        try {
            authorizationThread.join(120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (nick == null) {
            sendMessage("Authorization timeout");
            System.out.println("Authorization timeout");
            closeSocket(socket);
        }
        return;
    }

    private void closeSocket(Socket socket) {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Wait command: "/auth login1 pass1
     */
    private void auth() {
        while (true) {
            if (!channel.hasNextLine()) continue;
            Message msgAuth = channel.getMessage();
            if (MessageType.AUTH_MESSAGE.equals(msgAuth.getType())) {
                String[] commands = msgAuth.getBody().split(" ");
                if (commands.length >= 3) {
                    String login = commands[1];
                    String password = commands[2];
                    System.out.println("Try to login with " + login + " and " + password);
                    String nick = server.getAuthService().
                            authByLoginAndPassword(login, password);
                    if (nick == null) {
                        String msg = "Invalid login or password";
                        System.out.println(msg);
                        channel.sendMessage(msg);
                    } else if (server.isNickTaken(nick)) {
                        String msg = "Nick " + nick + " already taken!";
                        System.out.println(msg);
                        channel.sendMessage(msg);
                    } else {
                        this.nick = nick;
                        String msg = "Auth ok!";
                        System.out.println(msg);
                        channel.sendMessage(msg);
                        server.subscribe(this);
                        break;
                    }
                }
            } else {
                channel.sendMessage("Invalid command!");
            }
        }
    }

    private void sendPrivateMessage(String messageWithNickTo) {
        int firstSpaceIndex = messageWithNickTo.indexOf(" ");
        final String nickTo = messageWithNickTo.substring(0, firstSpaceIndex);
        final String message = messageWithNickTo.substring(firstSpaceIndex).trim();
        if (server.isNickTaken(nickTo)) server.sendPersonalMessage(nick, nickTo, nick + "->" + nickTo + ": " + message);
        else sendMessage(nickTo + " not taken!");
    }

    public void sendMessage(String msg) {
        channel.sendMessage(msg);
    }

    public String getNick() {
        return nick;
    }
}
