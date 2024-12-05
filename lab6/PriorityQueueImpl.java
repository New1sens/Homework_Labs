package Labs6;

public class PriorityQueueImpl {

    private int maxSize;
    private long[] queArray;
    private int nItems;

    public PriorityQueueImpl(int s) {
        maxSize = s;
        queArray = new long[maxSize];
        nItems = 0;
    }


    public void insert(long item) throws IllegalStateException {
        if (isFull()) {
            throw new IllegalStateException("Очередь переполнена, невозможно вставить элемент: " + item);
        }

        int j;
        if (nItems == 0) {
            queArray[nItems++] = item;
        } else {
            for (j = nItems - 1; j >= 0; j--) {
                if (item > queArray[j]) {
                    queArray[j + 1] = queArray[j];
                } else {
                    break;
                }
            }
            queArray[j + 1] = item;
            nItems++;
        }
    }

        public long remove() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Очередь пуста, невозможно извлечь элемент.");
        }
        return queArray[--nItems];
    }


    public long peekMin() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Очередь пуста, невозможно прочитать элемент.");
        }
        return queArray[nItems - 1];
    }

    // Проверка на пустоту
    public boolean isEmpty() {
        return (nItems == 0);
    }

    // Проверка на заполненность
    public boolean isFull() {
        return (nItems == maxSize);
    }
}

class test {
    public static void main(String[] args) {
        PriorityQueueImpl queue = new PriorityQueueImpl(5);

        try {
            queue.remove();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        try {
            queue.insert(42);
            queue.insert(21);
            queue.insert(52);
            queue.insert(44);
            queue.insert(15);
            queue.insert(60);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }


        while (!queue.isEmpty()) {
            System.out.println("Извлечён: " + queue.remove());
        }


        try {
            queue.remove();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}