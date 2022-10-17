package week5;

/*
    Design your implementation of the circular double-ended queue (deque).
 */
public class MyCircularDeque {

    int[] deque;
    int head;
    int tail;

    public MyCircularDeque(int k) {
        deque = new int[k + 1];
        head = 0; // always empty sentinel node
        tail = 1;
    }

    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        deque[head] = value;
        head = head - 1 >= 0 ? head - 1 : deque.length - 1;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        deque[tail] = value;
        tail = tail + 1 < deque.length ? tail + 1 : 0;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        head = head + 1 < deque.length ? head + 1 : 0;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        tail = tail - 1 >= 0 ? tail - 1 : deque.length - 1;
        return true;
    }

    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        int index = head + 1 < deque.length ? head + 1 : 0;
        return deque[index];
    }

    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        int index = tail - 1 >= 0 ? tail - 1 : deque.length - 1;
        return deque[index];
    }

    public boolean isEmpty() {
        return tail - head == 1 || head - tail == deque.length - 1;
    }

    public boolean isFull() {
        return head == tail;
    }
}
