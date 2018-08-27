package ru.WitBash.HomeWork2;

public class Main {
    static final int SIZE = 4;

    public static void main(String[] args) {
        String[][] array = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "b"}
        };
        sumMembersOfArray(array);

    }

    public static void sumMembersOfArray(String arr[][]) {
        int sum = 0;
        int[][] numArr = new int[SIZE][SIZE];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    if (arr.length != SIZE | arr[i].length != SIZE)
                        throw new MyArrayDataException();
                    try {
                        numArr[i][j] = Integer.parseInt(arr[i][j]);
                        sum += numArr[i][j];
                    } catch (NumberFormatException e) {
                        System.out.println("Конвертация невозможна");
                        throw new MyArraySizeException(i, j);
                    }
                } catch (MyArrayDataException ade) {
                    System.out.println(ade);
                    return;
                } catch (MyArraySizeException ase) {
                    System.out.println(ase);
                    continue;
                }
            }
        }
        System.out.println(sum);
    }
}
