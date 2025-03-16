package labs1;

import java.util.Random;

public class ArrayClient {
    public static void main(String[] args) {
        Random random = new Random();
        int size = 20;

        CommonArray commonArray = new CommonArray(size);

        for (int i = 0; i < size; i++) {
            commonArray.insert(random.nextLong(1000));
        }

        // Тестирование различных последовательностей
        long startTime = System.nanoTime();
        commonArray.shellSort("Default");
        long endTime = System.nanoTime();
        System.out.println("Время сортировки (Default): " + (endTime - startTime) + " нс");

        startTime = System.nanoTime();
        commonArray.shellSort("Knuth");
        endTime = System.nanoTime();
        System.out.println("Время сортировки (Knuth): " + (endTime - startTime) + " нс");

        startTime = System.nanoTime();
        commonArray.shellSort("Sedgewick");
        endTime = System.nanoTime();
        System.out.println("Время сортировки (Sedgewick): " + (endTime - startTime) + " нс");
    }
}