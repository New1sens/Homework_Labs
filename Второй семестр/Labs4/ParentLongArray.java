package Labs4;

public abstract class ParentLongArray implements ArrayInterface {
    protected final long[] array;
    protected int nElems;
    protected int operationsCount = 0;

    @Override
    public int getOperationsCount() {
        return operationsCount;
    }

    public ParentLongArray(int size) {
        this.array = new long[size];
        this.nElems = 0;
    }

    @Override
    public boolean insert(long value) {
        if (nElems == array.length) {
            return false;
        }
        array[nElems++] = value;
        return true;
    }

    @Override
    public boolean delete(long value) {
        int i;
        for (i = 0; i < nElems; i++) {
            if (array[i] == value) break;
        }

        if (i == nElems) {
            return false;
        } else {
            for (int j = i; j < nElems - 1; j++) {
                array[j] = array[j + 1];
            }
            nElems--;
            return true;
        }
    }

    @Override
    public void display() {
        for (int i = 0; i < nElems; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    @Override
    public int getSize() {
        return this.nElems;
    }

    // Метод для быстрой сортировки
    public void quickSort() {
        quickSort(0, nElems - 1);
    }

    // Рекурсивный метод быстрой сортировки
    private void quickSort(int low, int high) {
        if (low < high) {
            if (high - low < 10) {
                // Используем сортировку вставками для малых подмассивов
                insertionSort(low, high);
            } else {
                // Выбор опорного элемента на основе медианы из трех точек
                long pivot = medianOfThree(low, high);
                int partitionIndex = partition(low, high, pivot);
                quickSort(low, partitionIndex - 1);
                quickSort(partitionIndex + 1, high);
            }
        }
    }

    // Метод для выбора медианы из трех точек
    private long medianOfThree(int low, int high) {
        int mid = low + (high - low) / 2;

        // Сортируем три элемента: low, mid, high
        if (array[low] > array[mid]) {
            swap(low, mid);
        }
        if (array[low] > array[high]) {
            swap(low, high);
        }
        if (array[mid] > array[high]) {
            swap(mid, high);
        }

        // Возвращаем медиану (средний элемент)
        return array[mid];
    }

    // Разделение массива на две части относительно опорного элемента
    private int partition(int low, int high, long pivot) {
        int leftPtr = low - 1; // Справа от первого элемента
        int rightPtr = high + 1; // Слева от последнего элемента

        while (true) {
            do {
                leftPtr += 1;
            } while (leftPtr < high && array[leftPtr] < pivot); // Поиск элемента, который больше опорного, в левой части массива

            do {
                rightPtr -= 1;
            } while (rightPtr > low && array[rightPtr] > pivot); // Поиск элемента, который меньше опорного, в правой части массива

            if (leftPtr >= rightPtr) { // Условие выхода из цикла, если указатели leftPtr и rightPtr сошлись
                break;
            } else {
                swap(leftPtr, rightPtr); // Если указатели еще не сошлись, элементы, найденные выше, меняются местами
            }
        }
        return leftPtr; // Позиция разбиения
    }

    // Сортировка вставками для малых подмассивов
    private void insertionSort(int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            long key = array[i];
            int j = i - 1;

            while (j >= low && array[j] > key) {
                array[j + 1] = array[j];
                j--;
                operationsCount++;
            }
            array[j + 1] = key;
            operationsCount++;
        }
    }

    // Вспомогательный метод для обмена элементов
    private void swap(int index1, int index2) {
        long temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
        operationsCount++;
    }
}