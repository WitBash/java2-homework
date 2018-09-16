package HomeWork8_2;


public interface Channel {

//    default void sendMessage(String msg){
//        sendMessage(new Message(MessageType.BROADCAST_CHAT,msg));
//    }

    void sendMessage(Message message);

    void sendMessage(String message);

    Message getMessage();

    boolean hasNextLine();
}
