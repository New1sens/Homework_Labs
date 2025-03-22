package Labs3;

import java.util.Random;

public class ArrayClient {
    public static void main(String[] args) {
        Random random = new Random();
        int size = 20;

        CommonArray commonArray = new CommonArray(size);
        OrderedArray orderedArray = new OrderedArray(size);

        for (int i = 0; i < size; i++) {
            boolean insertResultCommonArray = false;
            boolean insertResultOrderedArray = false;

            while (!insertResultCommonArray) {
                insertResultCommonArray = commonArray.insert(random.nextLong(1000));
            }

            while (!insertResultOrderedArray) {
                insertResultOrderedArray = orderedArray.insert(random.nextLong(1000));
            }
        }

        long searchValue = random.nextLong(1000);

        orderedArray.display();

        System.out.println("Минимальный элемент упоряд. массива: " + orderedArray.getMin());
        System.out.println("Максимальный элемент упоряд.массива: " + orderedArray.getMax());

        if (orderedArray.contains(searchValue)) {
            System.out.println("Значение найдено в упоряд. массиве: " + searchValue);
        } else {
            System.out.println("Значение не найдено в упоряд.массиве: " + searchValue);
        }
        System.out.println("Количество операций в упоряд. массиве: " + orderedArray.getOperationsCount());

        commonArray.display();

        System.out.println("Минимальный элемент неупоряд. массива: " + commonArray.getMin());
        System.out.println("Максимальный элемент неупоряд. массива: " + commonArray.getMax());

        if (commonArray.contains(searchValue)) {
            System.out.println("Значение найдено в неупоряд. массиве: " + searchValue);
        } else {
            System.out.println("Значение не найдено в неупоряд. массиве: " + searchValue);
        }
        System.out.println("Количество операций в неупоряд. массиве: " + commonArray.getOperationsCount());

        // Сортировка и сравнение
        System.out.println("\nСортировка неупорядоченного массива:");
        commonArray.shellSort();
        commonArray.quickSort();
        commonArray.mergeSort();

        System.out.println("\nСортировка упорядоченного массива:");
        orderedArray.shellSort();
        orderedArray.quickSort();
        orderedArray.mergeSort();
    }
}