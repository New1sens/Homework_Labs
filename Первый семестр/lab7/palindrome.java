package lab7;
import java.util.Scanner;
class Node {
    char data; // Данные, которые хранит узел
    Node next; // Ссылка на следующий узел

    public Node(char data) {
        this.data = data;
        this.next = null;
    }
}
class LinkedList {
    private Node head; // Голова списка

    // Метод для добавления элемента в конец списка
    public void append(char data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode; // Если список пуст, новый узел становится головой
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next; // Перейти к последнему узлу
        }
        current.next = newNode; // Добавить новый узел в конец списка
    }

    // Метод для проверки, является ли список палиндромом
    public boolean isPalindrome() {
        if (head == null) return true; // Пустой список считается палиндромом

        // Используем два указателя для нахождения середины списка
        Node slow = head;
        Node fast = head;
        Node prev = null;

        // Переворачиваем первую половину связанного списка
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            Node nextNode = slow.next;
            slow.next = prev;
            prev = slow;
            slow = nextNode;
        }

        // Учитываем нечётное количество символов
        if (fast != null) {
            slow = slow.next; // Пропустить средний элемент
        }

        // Сравниваем первую и вторую половины
        while (prev != null) {
            if (prev.data != slow.data) {
                return false; // Если не совпадают, не является палиндромом
            }
            prev = prev.next; // Двигаемся по первой половине
            slow = slow.next; // Двигаемся по второй половине
        }

        return true; // Если все элементы совпадают, это палиндром
    }
}

class PalindromeTest {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите строку для проверки на палиндром:");
        String input = scanner.nextLine();

        // Заполняем связный список символами из строки
        for (char ch : input.toCharArray()) {
            list.append(ch);
        }

        // Проверяем на палиндром
        if (list.isPalindrome()) {
            System.out.println("Строка является палиндромом.");
        } else {
            System.out.println("Строка не является палиндромом.");
        }

        scanner.close();
    }
}