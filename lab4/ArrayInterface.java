package laba4;

public interface ArrayInterface {
    int getOperationsCount();
    boolean contains(long searchValue);
    boolean insert(long value);
    boolean delete(long value);
    void display();
    int getSize();
    long getMax();
    long getMin();

}
