package lab5;

import java.util.Random;

class BubbleSortExample {

    public static void main(String[] args) {
        // Генерация неотсортированного массива
        int size = 15; // размер массива
        int[] array = new int[size];
        Random random = new Random();

        // Заполнение массива случайными числами
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100); // случайные числа от 0 до 99
        }

        System.out.println("Неотсортированный массив:");
        printArray(array);

        // Измерение времени сортировки
        long startTime = System.nanoTime(); // Начало отсчета времени
        bubbleSort(array);                   // Сортировка массива
        long endTime = System.nanoTime();    // Конец отсчета времени

        // Вывод времени сортировки
        long duration = endTime - startTime; // Время выполнения в наносекундах
        System.out.println("Время сортировки: " + duration + " наносекунд");

        // Сортировка массива с помощью пузырьковой сортировки
        bubbleSort(array);

        System.out.println("Отсортированный массив:");
        printArray(array);
    }

    // Метод для сортировки массива пузырьком
    public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;

        // Внешний цикл для прохода по всему массиву
        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            // Внутренний цикл для сравнения соседних элементов
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Меняем элементы местами
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }

            // Если внутренний цикл не менял элементов, массив уже отсортирован
            if (!swapped) {
                break;
            }
        }
    }

    // Метод для вывода массива на консоль
    public static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
