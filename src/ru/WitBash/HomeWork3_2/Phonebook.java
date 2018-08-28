package ru.WitBash.HomeWork3_2;
import java.util.*;
public class Phonebook {
    public static void main(String[] args) {
        List<Subscriber> phb = new ArrayList<Subscriber>();
        phb.add(new Subscriber("Ivanov", "455467"));
        phb.add(new Subscriber("Petrov", "674893"));
        phb.add(new Subscriber("Gromov", "349675"));
        phb.add(new Subscriber("Ivanov", "243581"));
        phb.add(new Subscriber("Gromov", "759337"));
        phb.add(new Subscriber("Ptachov", "352794"));
        get("Gromov", phb);
    }
    public static void get(String surname, List<Subscriber> e) {
        Subscriber[] st = new Subscriber[e.size()];
        st = e.toArray(st);
        for (Subscriber o : e) {
            if (o.getSurname().equals(surname)) {
                System.out.println(o.getPhoneNumber());
            }
        }
    }
}
