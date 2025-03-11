package lab7;

// Узел связанного списка
class Node {
    int data; // Данные узла
    Node next; // Ссылка на следующий узел

    public Node(int data) {
        this.data = data;
        this.next = null; // Изначально следующий узел равен null
    }
}

// Реализация стека
class Stack {
    private Node top; // Указатель на верхний элемент стека

    public Stack() {
        this.top = null; // Изначально стек пуст
    }

    // Метод для добавления элемента в стек
    public void push(int data) {
        Node newNode = new Node(data); // Создаем новый узел
        newNode.next = top; // Указываем, что новый узел ссылается на текущий верхний элемент
        top = newNode; // Обновляем верхний элемент
    }

    // Метод для удаления элемента из стека
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Стек пуст. Невозможно извлечь элемент.");
        }
        int poppedData = top.data; // Сохраняем значение верхнего элемента
        top = top.next; // Обновляем верхний элемент
        return poppedData; // Возвращаем значение извлеченного элемента
    }

    // Метод для получения значения верхнего элемента без его удаления
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Стек пуст. Невозможно получить элемент.");
        }
        return top.data; // Возвращаем верхний элемент
    }

    // Метод для проверки пустоты стека
    public boolean isEmpty() {
        return top == null; // Если верхний элемент равен null, стек пуст
    }

    // Метод для отображения элементов стека
    public void display() {
        Node current = top; // Начинаем с верхнего элемента
        while (current != null) {
            System.out.print(current.data + " "); // Выводим элемент
            current = current.next; // Переходим к следующему элементу
        }
        System.out.println();
    }
}

// Пример использования
class Main {
    public static void main(String[] args) {
        Stack stack = new Stack(); // Создаем новый стек

        // Добавляем элементы в стек
        stack.push(10);
        stack.push(25);
        stack.push(37);

        // Отображаем элементы стека
        System.out.print("Элементы стека: ");
        stack.display();

        // Извлекаем элемент из стека
        System.out.println("Извлеченный элемент: " + stack.pop());

        // Показываем верхний элемент
        System.out.println("Верхний элемент стека: " + stack.peek());

        // Отображаем элементы стека после извлечения
        System.out.print("Элементы стека после извлечения: ");
        stack.display();
    }
}