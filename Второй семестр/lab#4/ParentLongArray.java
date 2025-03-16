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
                int pivotIndex = partition(low, high);
                quickSort(low, pivotIndex - 1);
                quickSort(pivotIndex + 1, high);
            }
        }
    }

    // Разделение массива на две части относительно опорного элемента
    private int partition(int low, int high) {
        long pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
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
    private void swap(int i, int j) {
        long temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        operationsCount++;
    }
}
