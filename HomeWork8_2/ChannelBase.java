package HomeWork8_2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChannelBase implements Channel {
    private Scanner sc;
    private PrintWriter pw;

    public ChannelBase(InputStream inputStream, OutputStream outputStream) {
        sc = new Scanner(inputStream);
        pw = new PrintWriter(outputStream, true);
    }

    public static Channel of(Socket socket) throws IOException{
        return new ChannelBase(
                socket.getInputStream(),
                socket.getOutputStream()
        );
    }

    @Override
    public void sendMessage(Message message) {
        pw.println(message.toString());
    }
    public void sendMessage(String message) {
        pw.println(message);
    }


    @Override
    public Message getMessage() {
        String s = sc.nextLine();
        if (s==null||s.trim().isEmpty())
            return null;
        if (s.startsWith("/auth"))
            return Message.authMsg(s);
        if (s.equals("/exit"))
            return new Message(MessageType.EXIT_COMMAND," ");
        if (s.startsWith("/w "))
            return new Message(MessageType.PRIVATE_MESSAGE,s.substring(3).trim());
        return new Message(MessageType.BROADCAST_CHAT,s);
    }

    @Override
    public boolean hasNextLine() {
        return sc.hasNextLine();
    }
}
