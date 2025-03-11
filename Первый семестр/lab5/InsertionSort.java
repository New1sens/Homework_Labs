package lab5;
import java.util.Arrays;
import java.util.Random;


class InsertionSortExample{

    public static void main(String[] args) {
        
        int size = 100;


        int[] array = new int[size];

        // Заполняем массив случайными числами
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100); // Случайные числа от 0 до 99
        }

        System.out.println("Неотсортированный массив: " + Arrays.toString(array));

        // Измерение времени сортировки
        long startTime = System.nanoTime(); // Начало отсчета времени
        insertionSort(array);                   // Сортировка массива
        long endTime = System.nanoTime();    // Конец отсчета времени

        // Вывод времени сортировки
        long duration = endTime - startTime; // Время выполнения в наносекундах
        System.out.println("Время сортировки: " + duration + " наносекунд");

        // Сортируем массив методом вставки
        insertionSort(array);

        System.out.println("Отсортированный массив: " + Arrays.toString(array));
    }

    // Метод сортировки вставкой
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;

            // Сдвигаем элементы массива, которые больше ключа, на одну позицию вперед
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            // Вставляем ключ на правильную позицию
            array[j + 1] = key;
        }
    }
}
