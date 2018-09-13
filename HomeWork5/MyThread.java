package HomeWork5;

public class MyThread extends Thread {
    static final int size = 1000000;
    static final int h = size / 2;
    static float[] arr = new float[size];
    float[] array;

    MyThread(String name, float[] array) {
        super(name);
        this.array = array;
        start();
    }

    public void run() {
        System.out.println(getName() + " - запуск");
        for (int i = 0; i < h; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
        }
        System.out.println(getName() + " - завершение");
    }

    public static void method1() {
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
        }
        System.currentTimeMillis();
        System.out.println("Method 1: " + (System.currentTimeMillis() - a));
    }

    public static void method2() {
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        float[] a1 = new float[h];
        float[] a2 = new float[h];

        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        MyThread mt1 = new MyThread("MyThread1", a1);

        for (int i = 0; i < h; i++) {
            a2[i] = (float) (a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
        }
        try {
            mt1.join();
        } catch (InterruptedException exc) {
            System.out.println("Основной поток прерван");
        }
        System.out.println("Завершение основного потока");

        System.arraycopy(mt1.array, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        System.currentTimeMillis();
        System.out.println("Method 2: " + (System.currentTimeMillis() - a));
    }

    public static void main(String[] args) {
        method1();
        method2();
    }
}



