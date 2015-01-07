package pro.gramming.tictactoe;

public class CircularQueue<T> {
    private T[] _buffer;
    private int _index;

    public CircularQueue(T... items) {
        if (items.length == 0) {
            throw new IllegalArgumentException("Must add at least 1 item to circular queue");
        }
        _buffer = items;
        _index = 0;
    }

    public T peekNext() {
        return _buffer[_index];
    }

    public T getNext() {
        T item = _buffer[_index];
        _index = (_index + 1) % _buffer.length;
        return item;
    }

    public int size() {
        return _buffer.length;
    }
}
