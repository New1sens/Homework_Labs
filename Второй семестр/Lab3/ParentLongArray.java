package labs1;

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

    // Метод для сортировки Шелла с различными интервальными последовательностями
    public void shellSort(String sequenceType) {
        int[] intervals = generateIntervals(sequenceType);
        for (int gap : intervals) {
            for (int i = gap; i < nElems; i++) {
                long temp = array[i];
                int j;
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                    operationsCount++;
                }
                array[j] = temp;
                operationsCount++;
            }
        }
    }

    // Генерация интервалов в зависимости от типа последовательности
    private int[] generateIntervals(String sequenceType) {
        switch (sequenceType) {
            case "Knuth":
                return generateKnuthSequence();
            case "Sedgewick":
                return generateSedgewickSequence();
            case "Default":
                return new int[]{5, 3, 1};
            default:
                throw new IllegalArgumentException("Неизвестный тип последовательности: " + sequenceType);
        }
    }

    // Последовательность Кнута: h = 3*h + 1
    private int[] generateKnuthSequence() {
        int maxGap = 1;
        while (maxGap < nElems / 3) {
            maxGap = 3 * maxGap + 1;
        }

        int[] intervals = new int[(int) (Math.log(maxGap) / Math.log(3)) + 1];
        int index = 0;
        while (maxGap > 0) {
            intervals[index++] = maxGap;
            maxGap = (maxGap - 1) / 3;
        }
        return intervals;
    }

    // Последовательность Седжвика: h = 9 * (4^k - 2^k) + 1 или 4^k + 3 * 2^(k-1) + 1
    private int[] generateSedgewickSequence() {
        int k = 0;
        int maxGap = 1;
        while (maxGap < nElems) {
            if (k % 2 == 0) {
                maxGap = 9 * ((int) Math.pow(4, k) - (int) Math.pow(2, k)) + 1;
            } else {
                maxGap = (int) Math.pow(4, k) + 3 * (int) Math.pow(2, k - 1) + 1;
            }
            k++;
        }

        int[] intervals = new int[k];
        k = 0;
        maxGap = 1;
        while (maxGap < nElems) {
            if (k % 2 == 0) {
                maxGap = 9 * ((int) Math.pow(4, k) - (int) Math.pow(2, k)) + 1;
            } else {
                maxGap = (int) Math.pow(4, k) + 3 * (int) Math.pow(2, k - 1) + 1;
            }
            intervals[k++] = maxGap;
        }
        return intervals;
    }
}
