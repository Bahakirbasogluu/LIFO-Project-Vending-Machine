import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Tokens> implements Iterable<Tokens> {

    private Node firstIndex;    private Node lastIndex;

    public Queue() { firstIndex = null;    lastIndex = null; }

    private class Node { private Tokens tokenNode;    private Node nextObject;}

    public void enqueue(Tokens item) {
        Node oldLast = lastIndex;    lastIndex = new Node();    lastIndex.tokenNode = item;    lastIndex.nextObject = null;
        if (isEmpty()) firstIndex = lastIndex;
        else oldLast.nextObject = lastIndex; }

    public Tokens Dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Tokens item = firstIndex.tokenNode;    firstIndex = firstIndex.nextObject;
        if (isEmpty()) lastIndex = null;    return item; }

    public boolean isEmpty()
    {
        return firstIndex == null;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Tokens item : this)    s.append(item).append(" ");    return s.toString(); }

    public Iterator<Tokens> iterator()
    {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Tokens> {

        private Node current = firstIndex;

        public boolean hasNext()
        {
            return current != null;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        public Tokens next() {
            if (!hasNext()) throw new NoSuchElementException();
            Tokens item = current.tokenNode;    current = current.nextObject;    return item; }
    }}