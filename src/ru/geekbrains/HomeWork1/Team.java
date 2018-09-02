package ru.geekbrains.HomeWork1;

public class Team {
    private String name;
    private String[] members = new String[4];
    float[] jumps = new float[4];

    public Team(String name, String member1, float jump1, String member2, float jump2, String member3, float jump3, String member4, float jump4) {
        this.name = name;
        members[0] = member1;
        members[1] = member2;
        members[2] = member3;
        members[3] = member4;
        jumps[0] = jump1;
        jumps[1] = jump2;
        jumps[2] = jump3;
        jumps[3] = jump4;
    }

    public void getMembers() {
        for (int i = 0; i < 4; i++) {
            System.out.println("Участникa " + (i + 1) + " зовут " + members[i]);
        }
    }

    public String getName() {
        return name;
    }

    void showResults() {
        for (int i = 0; i < 4; i++) {
            int count = 0;
            for (int j = 0; j < 4; j++) {
                if (jumps[i] > Course.obstacles[j]) count++;
            }
            if (count == 4)
                System.out.println(members[i] + " прошел дистанцию");
        }
    }
}

