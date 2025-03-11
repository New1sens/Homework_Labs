package laba4;

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
            // Сдвиг оставшихся элементов влево
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
}