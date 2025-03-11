package Labs6;

class StackImpl {
    private int maxSize;    // Максимальный размер стека
    private long[] stackArray; // Массив для хранения элементов стека
    private int top;        // Индекс верхнего элемента стека

    public StackImpl(int size) {
        this.maxSize = size; // Инициализация максимального размера стека
        this.stackArray = new long[maxSize]; // Создание массива
        this.top = -1; // Устанавливаем индекс верхнего элемента на -1, чтобы обозначить пустой стек
    }

    // Метод добавления элемента в стек
    public void push(long value) throws IllegalStateException {
        if (isFull()) {
            throw new IllegalStateException("Стек переполнен, невозможно вставить элемент: " + value);
        }
        stackArray[++top] = value; // Увеличиваем индекс и добавляем элемент
    }

    // Метод извлечения элемента из стека
    public long pop() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Стек пуст, невозможно извлечь элемент.");
        }
        return stackArray[top--]; // Возвращаем элемент и уменьшаем индекс верхнего элемента
    }

    // Метод для чтения верхнего элемента стека без его извлечения
    public long peek() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Стек пуст, невозможно прочитать верхний элемент.");
        }
        return stackArray[top]; // Возвращаем верхний элемент
    }

    // Проверка, пуст ли стек
    public boolean isEmpty() {
        return (top == -1);
    }

    // Проверка, заполнен ли стек
    public boolean isFull() {
        return (top == maxSize - 1);
    }
}
class StackTest {
    public static void main(String[] args) {
        StackImpl stack = new StackImpl(15);

        try {
            // Попытка извлечь элемент из пустого стека
            stack.pop();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        try {
            // Добавление элементов в стек
            stack.push(105);
            stack.push(20);
            stack.push(303);
            stack.push(45);
            stack.push(52);
            stack.push(65); // Это вызовет исключение
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        // Чтение и извлечение элементов из стека
        while (!stack.isEmpty()) {
            System.out.println("Извлечён: " + stack.pop());
        }

        // Попытка извлечь элемент снова из пустого стека
        try {
            stack.pop();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}