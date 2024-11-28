package lab5;
import java.util.Arrays;
import java.util.Random;

class SelectionSortExample {

    public static void main(String[] args) {
        // Генерация неотсортированного массива случайных чисел
        int[] array = generateRandomArray(100, 100); // Массив из 10 случайных чисел до 100
        System.out.println("Неотсортированный массив: " + Arrays.toString(array));



        long startTime = System.nanoTime(); // Начало отсчета времени
        selectionSort(array);                   // Сортировка массива
        long endTime = System.nanoTime();    // Конец отсчета времени

        // Вывод времени сортировки
        long duration = endTime - startTime; // Время выполнения в наносекундах
        System.out.println("Время сортировки: " + duration + " наносекунд");


        // Сортировка массива методом выбора
        selectionSort(array);
        System.out.println("Отсортированный массив: " + Arrays.toString(array));
    }

    // Метод генерации случайного массива
    private static int[] generateRandomArray(int size, int max) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(max);
        }
        return array;
    }

    // Метод сортировки массивов методом выбора
    private static void selectionSort(int[] array) {
        int n = array.length;

        // Один за другим перемещаем границу неотсортированной части массива
        for (int i = 0; i < n - 1; i++) {
            // Находим минимальный элемент в оставшейся неотсортированной части массива
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            // Меняем местами найденный минимальный элемент с первым элементом неотсортированной части
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
}

