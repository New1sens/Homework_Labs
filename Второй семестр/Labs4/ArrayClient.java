package Labs4;

import java.util.Random;

public class ArrayClient {
    public static void main(String[] args) {
        Random random = new Random();
        int size = 100;

        CommonArray commonArray = new CommonArray(size);

        // Заполнение массива случайными значениями
        for (int i = 0; i < size; i++) {
            commonArray.insert(random.nextLong(1000));
        }

        System.out.println("Массив до сортировки:");
        commonArray.display();

        // Быстрая сортировка
        long startTime = System.nanoTime();
        commonArray.quickSort();
        long endTime = System.nanoTime();

        System.out.println("Массив после сортировки:");
        commonArray.display();

        System.out.println("Время выполнения быстрой сортировки: " + (endTime - startTime) + " нс");
        System.out.println("Количество операций: " + commonArray.getOperationsCount());
    }
}
