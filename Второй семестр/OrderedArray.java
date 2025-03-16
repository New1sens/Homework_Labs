package labs1;

public class OrderedArray extends ParentLongArray {
    public OrderedArray(int size) {
        super(size);
    }

    @Override
    public boolean contains(long searchValue) {
        operationsCount = 0;
        int lB = 0;
        int uB = nElems - 1;

        while (lB <= uB) {
            operationsCount++;
            int currentIndex = (lB + uB) / 2;

            if (array[currentIndex] == searchValue) {
                return true;
            } else if (array[currentIndex] < searchValue) {
                lB = currentIndex + 1;
            } else {
                uB = currentIndex - 1;
            }
        }
        return false;
    }

    @Override
    public boolean insert(long value) {
        if (nElems == array.length) return false;

        if (contains(value)) return false;

        int low = 0, high = nElems - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (array[mid] < value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        for (int i = nElems; i > low; i--) {
            array[i] = array[i - 1];
        }
        array[low] = value;
        nElems++;
        return true;
    }

    @Override
    public boolean delete(long value) {
        int low = 0, high = nElems - 1, deleteIndex = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (array[mid] == value) {
                deleteIndex = mid;
                break;
            } else if (array[mid] < value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (deleteIndex == -1) return false;

        for (int i = deleteIndex; i < nElems - 1; i++) {
            array[i] = array[i + 1];
        }
        nElems--;
        return true;
    }

    @Override
    public long getMax() {
        return array[nElems - 1];
    }

    @Override
    public long getMin() {
        return array[0];
    }
}
