package lab7;

// Узел связанного списка
class Node2 {
    int data; // Данные узла
    Node next; // Ссылка на следующий узел

    public Node2(int data) {
        this.data = data; // Инициализация данных
        this.next = null; // Изначально следующий узел равен null
    }
}

// Реализация очереди
class Queue {
    private Node front; // Указатель на передний элемент очереди
    private Node rear;  // Указатель на задний элемент очереди

    public Queue() {
        this.front = null; // Изначально очередь пуста
        this.rear = null;  // Изначально очередь пуста
    }

    // Метод для добавления элемента в очередь (в конец)
    public void enqueue(int data) {
        Node newNode = new Node(data);
        if (rear == null) {
            // Если очередь пуста, новый элемент становится и передним, и задним
            front = rear = newNode;
        } else {
            // Добавляем новый элемент в конец и обновляем ссылку rear
            rear.next = newNode;
            rear = newNode;
        }
    }

    // Метод для удаления элемента из очереди (из начала)
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Очередь пуста. Невозможно извлечь элемент.");
        }
        int dequeuedData = front.data; // Сохраняем значение переднего элемента
        front = front.next; // Обновляем передний элемент
        if (front == null) {
            // Если очередь стала пустой, сбрасываем указатель rear
            rear = null;
        }
        return dequeuedData; // Возвращаем значение извлеченного элемента
    }

    // Метод для получения значения переднего элемента без его удаления
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Очередь пуста. Невозможно получить элемент.");
        }
        return front.data; // Возвращаем передний элемент
    }

    // Метод для проверки пустоты очереди
    public boolean isEmpty() {
        return front == null; // Если передний элемент равен null, очередь пуста
    }

    // Метод для отображения элементов очереди
    public void display() {
        Node current = front; // Начинаем с переднего элемента
        while (current != null) {
            System.out.print(current.data + " "); // Выводим элемент
            current = current.next; // Переходим к следующему элементу
        }
        System.out.println();
    }
}

// Пример использования
class Main1 {
    public static void main(String[] args) {
        Queue queue = new Queue(); // Создаем новую очередь

        // Добавляем элементы в очередь
        queue.enqueue(10);
        queue.enqueue(27);
        queue.enqueue(35);

        // Отображаем элементы очереди
        System.out.print("Элементы очереди: ");
        queue.display();

        // Извлекаем элемент из очереди
        System.out.println("Извлеченный элемент: " + queue.dequeue());

        // Показываем передний элемент
        System.out.println("Передний элемент очереди: " + queue.peek());

        // Отображаем элементы очереди после извлечения
        System.out.print("Элементы очереди после извлечения: ");
        queue.display();
    }
}
