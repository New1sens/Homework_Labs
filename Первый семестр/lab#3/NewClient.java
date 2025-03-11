package Lab3;
import java.util.Random;

public class NewClient {
    public static void main(String[] args) {
        Random random = new Random();

        int size = 100;
        NewInterface array = new HighInterfaceArrayImpl(size);

        for (int i = 0; i < size; i++) {
            array.insert(random.nextLong(50));
        }

        array.display();
        long serchVal = random.nextLong(size);
        if (array.find(serchVal)) {
            System.out.println("Удалось найти значение: " + serchVal);
        } else {
            System.out.println("Не удалось найти значение: " + serchVal);
        }
        System.out.println("Количество элементов в массиве:" + array.getSize());
        System.out.println("Максимальный элемент в массиве:" + array.getMax());
        System.out.println("Минимальный элемент в массиве:" + array.getMin());
    }
}
