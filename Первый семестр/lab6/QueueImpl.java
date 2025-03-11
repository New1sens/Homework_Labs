package Labs6;

public class QueueImpl {
    private int maxSize;
    private long[] queArray;
    private int front;
    private int rear;
    private int nItems;

    public QueueImpl(int s) {
        maxSize = s;
        queArray = new long[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    public void insert(long j) throws IllegalStateException {
        if (isFull()) {
            throw new IllegalStateException("Очередь переполнена, невозможно вставить элемент: " + j);
        }


        if (rear == maxSize - 1) {
            rear = -1;
        }
        queArray[++rear] = j;
        nItems++;
    }

    public long remove() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Очередь пуста, невозможно извлечь элемент.");
        }

        long temp = queArray[front++];


        if (front == maxSize) {
            front = 0;
        }
        nItems--;
        return temp;
    }

    public long peekFront() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Очередь пуста, невозможно прочитать элемент.");
        }
        return queArray[front];
    }

    public boolean isEmpty() {
        return (nItems == 0);
    }

    public boolean isFull() {
        return (nItems == maxSize);
    }

    public int size() {
        return nItems;
    }
}

class QueueTest {
    public static void main(String[] args) {
        QueueImpl queue = new QueueImpl(5);

        try {
            queue.remove();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        try {
            queue.insert(10);
            queue.insert(20);
            queue.insert(30);
            queue.insert(40);
            queue.insert(50);
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
    }}
