package ru.geekbrains.HomeWork1;

public class Main {
    public static void main(String[] args) {
        Course course = new Course(1.3f, 1.4f, 1.35f, 1.45f); // создаем полосу препятствий
        Team team = new Team("Winners", "m1", 1.4f, "m2", 1.3f, "m3", 1.6f, "m4", 1.7f); // создаем команду
        team.getMembers();
        if (course.doIt(team)) {
            team.showResults(); // вывод результатов
        } else System.out.println("Команда " + team.getName() + " не вышла на старт");
    }
}
