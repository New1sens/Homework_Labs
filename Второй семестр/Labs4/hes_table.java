package Labs4;

class IntHashTable {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Entry[] table;
    private int size;
    private int threshold;
    private final float loadFactor;

    public IntHashTable() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public IntHashTable(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public IntHashTable(int initialCapacity, float loadFactor) {
        validateParameters(initialCapacity, loadFactor);

        this.loadFactor = loadFactor;
        this.table = new Entry[initialCapacity];
        this.threshold = calculateThreshold(initialCapacity, loadFactor);
    }

    private void validateParameters(int capacity, float factor) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive");
        }
        if (factor <= 0 || Float.isNaN(factor)) {
            throw new IllegalArgumentException("Load factor must be positive");
        }
    }

    private int calculateThreshold(int capacity, float factor) {
        return (int) (capacity * factor);
    }

    public void put(int key, int value) {
        if (needsResize()) {
            resize();
        }

        int index = calculateIndex(key);
        Entry entry = findEntry(table[index], key);

        if (entry != null) {
            entry.value = value;
        } else {
            addNewEntry(key, value, index);
        }
    }

    private boolean needsResize() {
        return size >= threshold;
    }

    private void resize() {
        int newCapacity = table.length * 2;
        Entry[] newTable = new Entry[newCapacity];

        threshold = calculateThreshold(newCapacity, loadFactor);
        transferEntries(newTable);

        table = newTable;
    }

    private void transferEntries(Entry[] newTable) {
        for (Entry entry : table) {
            while (entry != null) {
                Entry next = entry.next;
                int newIndex = calculateIndex(entry.key, newTable.length);

                entry.next = newTable[newIndex];
                newTable[newIndex] = entry;

                entry = next;
            }
        }
    }

    private int calculateIndex(int key) {
        return calculateIndex(key, table.length);
    }

    private int calculateIndex(int key, int tableLength) {
        return hash(key) % tableLength;
    }

    private Entry findEntry(Entry entry, int key) {
        while (entry != null) {
            if (entry.key == key) {
                return entry;
            }
            entry = entry.next;
        }
        return null;
    }

    private void addNewEntry(int key, int value, int index) {
        table[index] = new Entry(key, value, table[index]);
        size++;
    }

    public Integer get(int key) {
        Entry entry = findEntry(table[calculateIndex(key)], key);
        return entry != null ? entry.value : null;
    }

    public boolean containsKey(int key) {
        return get(key) != null;
    }

    public void remove(int key) {
        int index = calculateIndex(key);
        Entry prev = null;
        Entry current = table[index];

        while (current != null) {
            if (current.key == key) {
                removeEntry(index, prev, current);
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    private void removeEntry(int index, Entry prev, Entry current) {
        if (prev == null) {
            table[index] = current.next;
        } else {
            prev.next = current.next;
        }
        size--;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int hash(int key) {
        return key ^ (key >>> 16);
    }

    // Вложенный класс вынес в конец
    private static class Entry {
        final int key;
        int value;
        Entry next;

        Entry(int key, int value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    // Пример использования хеш-таблицы
    public static void main(String[] args) {
        IntHashTable table = new IntHashTable(); // Создаем таблицу
        // Добавляем элементы
        for (int i = 0; i < 100; i++) {
            table.put(i, i * 10);
        }

        // Проверка работы таблицы
        System.out.println("Размер: " + table.size());
        System.out.println("Значение для ключа 5: " + table.get(5));
        System.out.println("Содержит ключ 99: " + table.containsKey(99));
    }
}
