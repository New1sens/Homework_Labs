package laba4;


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

        // Инициализация минимального значения первым элементом
        long min = array[0];

        // Поиск минимального значения в массиве
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

        // Инициализация максимального значения первым элементом
        long max = array[0];

        // Поиск максимального значения в массиве
        for (int i = 1; i < nElems; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
}