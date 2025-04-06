package Labs4;

class IntHashTable {

    private static final int DEFAULT_INITIAL_CAPACITY = 16; // Начальный размер массива
    private static final float DEFAULT_LOAD_FACTOR = 0.75f; // Коэффициент загрузки для расширения

    private Entry[] table; // Массив бакетов
    private int size; // Количество элементов
    private int threshold; // Пороговое значение для расширения таблицы
    private final float loadFactor; // Коэффициент загрузки

    // Вложенный класс для хранения элементов таблицы
    private static class Entry {
        final int key; // Ключ элемента (неизменяемый)
        int value; // Значение элемента
        Entry next; // Ссылка на следующий элемент в цепочке (для разрешения коллизий)


        Entry(int key, int value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public IntHashTable() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR); // Вызов основного конструктора
    }

    public IntHashTable(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR); // Вызов основного конструктора
    }


    public IntHashTable(int initialCapacity, float loadFactor) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        }

        this.loadFactor = loadFactor; // Установка коэффициента загрузки
        this.table = new Entry[initialCapacity]; // Создание массива бакетов
        this.threshold = (int)(initialCapacity * loadFactor);
    }

    // Метод для добавления или обновления элемента
    public void put(int key, int value) {
                if (size >= threshold) {
            resize(table.length * 2); // Увеличиваем размер таблицы в 2 раза
        }

        // Вычисление индекса бакета для данного ключа
        int index = hash(key) % table.length;

        // Поиск ключа в цепочке бакета
        for (Entry e = table[index]; e != null; e = e.next) {
            if (e.key == key) {
                e.value = value;
                return;
            }
        }
        // Если ключ не найден, добавляем новый элемент в начало цепочки
        table[index] = new Entry(key, value, table[index]);
        size++;
    }

    // Метод для получения значения по ключу
    public Integer get(int key) {
        int index = hash(key) % table.length;

        // Поиск ключа в цепочке бакета
        for (Entry e = table[index]; e != null; e = e.next) {
            if (e.key == key) {
                return e.value; // Возвращаем значение, если ключ найден
            }
        }

        return null;
    }

    // Метод проверки наличия ключа в таблице
    public boolean containsKey(int key) {
        return get(key) != null;
    }
    
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    // Вспомогательный метод для вычисления хеш-кода ключа
    private int hash(int key) {
        return key ^ (key >>> 16);
    }

    // Метод для расширения таблицы
    private void resize(int newCapacity) {
        Entry[] oldTable = table; // Сохраняем ссылку на старую таблицу
        int oldCapacity = oldTable.length; // Сохраняем старый размер

        // Проверка на максимально возможный размер
        if (oldCapacity >= Integer.MAX_VALUE) {
            threshold = Integer.MAX_VALUE; // Устанавливаем максимальный порог
            return;
        }

        // Создаем новую таблицу увеличенного размера
        Entry[] newTable = new Entry[newCapacity];
        table = newTable; // Заменяем ссылку на таблицу
        // Пересчитываем пороговое значение
        threshold = (int)(newCapacity * loadFactor);
        size = 0; // Временно обнуляем размер (будет пересчитан)

        // Перехеширование всех элементов
        for (Entry head : oldTable) {
            while (head != null) {
                Entry next = head.next; // Сохраняем ссылку на следующий элемент
                // Вычисляем новый индекс для текущего элемента
                int index = hash(head.key) % newCapacity;

                // Вставляем элемент в новую таблицу
                head.next = newTable[index];
                newTable[index] = head;
                size++; // Увеличиваем счетчик элементов

                head = next; // Переходим к следующему элементу
            }
        }
    }

    // Пример использования хеш-таблицы
    public static void main(String[] args) {
        IntHashTable table = new IntHashTable(); // Создаем таблицу
        // Добавляем элементы
        for (int i = 0; i < 100; i++) {
            table.put(i, i * 10);
        }

        // Проверяем работу таблицы
        System.out.println("Размер: " + table.size());
        System.out.println("Значение для ключа 5: " + table.get(5));
        System.out.println("Содержит ключ 99: " + table.containsKey(99));

    }
}
