package HomeWork8_2;

public class AuthMessage extends Message {
    private String login;
    private String password;

    public AuthMessage(String login, String password) {
        super(MessageType.AUTH_MESSAGE, "");
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "/auth" + login + " " + password;
    }
}
