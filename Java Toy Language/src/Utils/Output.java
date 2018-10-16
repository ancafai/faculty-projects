package Utils;

public interface Output<E> {
    void add(E value);
    int size();
    Iterable<E> getAll();
}
