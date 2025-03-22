package Labs3;

public class CommonArray extends ParentLongArray {
    public CommonArray(int size) {
        super(size);
    }

    @Override
    public boolean contains(long searchValue) {
        operationsCount = 0; // Обнуляем счетчик перед поиском

        for (int i = 0; i < nElems; i++) {
            operationsCount++; // Увеличиваем счетчик операций
            if (array[i] == searchValue) return true;
        }
        return false;
    }

    @Override
    public long getMin() {
        if (nElems == 0) {
            throw new IllegalStateException("Массив пуст.");
        }

        long min = array[0];
        for (int i = 1; i < nElems; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    @Override
    public long getMax() {
        if (nElems == 0) {
            throw new IllegalStateException("Массив не имеет элементов.");
        }

        long max = array[0];
        for (int i = 1; i < nElems; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    // Сортировка Шелла
    public void shellSort() {
        long startTime = System.nanoTime();
        int n = nElems;
        int comparisons = 0;
        int swaps = 0;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                long temp = array[i];
                int j;
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                    swaps++;
                    comparisons++;
                }
                array[j] = temp;
                swaps++;
            }
        }

        long endTime = System.nanoTime();
        System.out.println("Shell Sort: Comparisons = " + comparisons + ", Swaps = " + swaps + ", Time = " + (endTime - startTime) + " ns");
    }

    // Быстрая сортировка
    public void quickSort() {
        long startTime = System.nanoTime();
        quickSort(0, nElems - 1);
        long endTime = System.nanoTime();
        System.out.println("Quick Sort: Time = " + (endTime - startTime) + " ns");
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        long pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                long temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        long temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    // Сортировка слиянием
    public void mergeSort() {
        long startTime = System.nanoTime();
        mergeSort(0, nElems - 1);
        long endTime = System.nanoTime();
        System.out.println("Merge Sort: Time = " + (endTime - startTime) + " ns");
    }

    private void mergeSort(int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(l, m);
            mergeSort(m + 1, r);
            merge(l, m, r);
        }
    }

    private void merge(int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        long[] L = new long[n1];
        long[] R = new long[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = array[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = array[m + 1 + j];

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }
}