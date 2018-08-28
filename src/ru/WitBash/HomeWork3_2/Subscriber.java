package ru.WitBash.HomeWork3_2;

public class Subscriber {
    private String surname;
    private String phoneNumber;

    public Subscriber(String surname, String phoneNumber) {
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        String[] subscriber = new String[2];
        subscriber[0] = surname;
        subscriber[1] = phoneNumber;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

