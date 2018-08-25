package ru.WitBash.HomeWork2;

public class MyArraySizeException extends Exception {
    int a;
    int b;

    public String toString() {
        return "В ячейке [" + a + "]" + "[" + b + "] массив содержит элемент нестрокового типа";
    }

    MyArraySizeException(int a, int b) {
        this.a = a;
        this.b = b;
    }
}
