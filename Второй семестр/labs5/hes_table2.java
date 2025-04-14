package labs5;
class  OpenAddressingHashTable {

    private static final int DEFAULT_CAPACITY = 8;
    private static final float LOAD_FACTOR = 0.7f;
    private static final int DELETED_KEY = Integer.MIN_VALUE;


    private int[] keys;
    private int[] values;
    private int size;


    public OpenAddressingHashTable() {
        this(DEFAULT_CAPACITY);
    }

    public OpenAddressingHashTable(int initialCapacity) {
        int capacity = Math.max(initialCapacity, 4);
        this.keys = new int[capacity];
        this.values = new int[capacity];
        this.size = 0;
    }

    public void put(int key, int value) {
        if (key == DELETED_KEY) {
            throw new IllegalArgumentException("Invalid key value: " + DELETED_KEY);
        }

        if (size >= keys.length * LOAD_FACTOR) {
            resize();
        }

        int index = findIndexForInsert(key);
        if (index == -1) {
            resize();
            index = findIndexForInsert(key);
            if (index == -1) {
                throw new IllegalStateException("Hash table is full after resize");
            }
        }

        if (keys[index] == 0 || keys[index] == DELETED_KEY) {
            size++;
        }

        keys[index] = key;
        values[index] = value;
    }

    public Integer get(int key) {
        if (key == DELETED_KEY) {
            return null;
        }

        int index = findIndexForKey(key);
        return index != -1 ? values[index] : null;
    }


    public boolean contains(int key) {
        return get(key) != null;
    }

    public void remove(int key) {
        if (key == DELETED_KEY) {
            return;
        }

        int index = findIndexForKey(key);
        if (index != -1) {
            keys[index] = DELETED_KEY;
            values[index] = 0;
            size--;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public void printStats() {
        System.out.println("--- Статистика по хэш-таблице ---");
        System.out.println("Размер: " + size);
        System.out.println("Вместимость: " + keys.length);
        System.out.printf("Коэффициент нагрузки: %.2f%%\n", (size * 100.0 / keys.length));
        System.out.println("-----------------------");
    }


    private int findIndexForInsert(int key) {
        int hash = Math.abs(key % keys.length);
        int i = 0;
        int firstDeleted = -1;

        while (i <= keys.length) {
            int index = (hash + i * i) % keys.length;

            // Если нашли такой же ключ - возвращаем его позицию
            if (keys[index] == key) {
                return index;
            }

            // Запоминаем первую удаленную ячейку
            if (keys[index] == DELETED_KEY && firstDeleted == -1) {
                firstDeleted = index;
            }

            // Если нашли пустую ячейку (не удаленную)
            if (keys[index] == 0) {
                // Возвращаем либо удаленную ячейку (если нашли), либо текущую пустую
                return firstDeleted != -1 ? firstDeleted : index;
            }

            i++;
        }
        // Если не нашли пустую ячейку, возвращаем удаленную (если есть)
        return firstDeleted;
    }


    private int findIndexForKey(int key) {
        int hash = Math.abs(key % keys.length);
        int i = 0;
        while (i <= keys.length) {
            // Вычисляем индекс с учетом квадратичного пробирования
            int index = (hash + i * i) % keys.length;

            // Если нашли ключ - возвращаем его индекс
            if (keys[index] == key) {
                return index;
            }
            // Если нашли пустую (не удаленную) ячейку - ключ не существует
            if (keys[index] == 0) {
                return -1;
            }

            i++;
        }

        return -1;
    }

    private void resize() {
        int newCapacity = keys.length * 2;
        int[] oldKeys = keys;
        int[] oldValues = values;

        // Создаем новые массивы
        keys = new int[newCapacity];
        values = new int[newCapacity];
        size = 0; // Сброс счетчика

        // Перехешируем все существующие элементы
        for (int i = 0; i < oldKeys.length; i++) {
            if (oldKeys[i] != 0 && oldKeys[i] != DELETED_KEY) {
                put(oldKeys[i], oldValues[i]);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Хэш-таблица [размер=").append(size)
                .append(", вместимость=").append(keys.length).append("]\n");

        // Добавляем информацию о каждой ячейке
        for (int i = 0; i < keys.length; i++) {
            String status;
            if (keys[i] == 0) {
                status = "пусто";
            } else if (keys[i] == DELETED_KEY) {
                status = "удалено";
            } else {
                status = String.format("key=%d, value=%d", keys[i], values[i]);
            }
            sb.append("[").append(i).append("]: ").append(status).append("\n");
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        OpenAddressingHashTable table = new OpenAddressingHashTable(4);

        // Добавляем элементы
        System.out.println("Добавляем элементы:");
        table.put(1, 100);
        table.put(2, 200);
        table.put(5, 500); // Вызовет коллизию
        table.printStats();
        System.out.println(table);

        // Получаем значения
        System.out.println("Значение для ключа 2: " + table.get(2));
        System.out.println("Содержит ключ 3? " + table.contains(3));

        // Удаляем элемент
        table.remove(2);
        System.out.println("\nПосле удаления ключа 2:");
        table.printStats();
        System.out.println(table);

        // Добавляем новый элемент
        table.put(9, 900);
        System.out.println("После добавления ключа 9:");
        table.printStats();
        System.out.println(table);

        // Тестируем автоматическое расширение
        table.put(3, 300);
        table.put(4, 400);
        table.put(6, 600);
        System.out.println("\nПосле добавления нескольких элементов:");
        table.printStats();
        System.out.println(table);
    }
}