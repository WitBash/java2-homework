package HomeWork8;

import HomeWork8_2.AuthMessage;

public interface Controller {
    void sendMessage(String msg);

    void closeConnection();

    void showUi(ClientUi clientUi);

    void sendMessage(AuthMessage authMessage);
}
