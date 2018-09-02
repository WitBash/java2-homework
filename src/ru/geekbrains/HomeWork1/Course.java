package ru.geekbrains.HomeWork1;

public class Course {
    public static float obstacles[] = new float[4];

    public Course(float obs1, float obs2, float obs3, float obs4) {
        obstacles[0] = obs1;
        obstacles[1] = obs2;
        obstacles[2] = obs3;
        obstacles[3] = obs4;
    }

    boolean doIt(Team t) {
        int count1 = 0;
        for (int i = 0; i < 4; i++) {
            int count2 = 0;
            for (int j = 0; j < 4; j++) {
                if (t.jumps[i] > obstacles[j]) count2++;
            }
            if (count2 == 4) count1++;
        }
        if (count1 > 0) return true;
        return false;
    }
}


