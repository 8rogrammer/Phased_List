package cs1302.p3;

import cs1302.adt.Node;
import cs1302.adt.StringList;
import cs1302.adt.FancyStringList;

/**
 * class that creates new LinkedStringList and allows the user to alter the list.
 * Also extends from baseStringList.
 */
public class LinkedStringList extends BaseStringList {


    private Node head;

    /**
     * emptys Node head.
     */
    public LinkedStringList() {
        this.clear();
    }

    /**
     * copies a StringList into a linkedlist.
     * @param other
     */
    public LinkedStringList(StringList other) {
        forEach(other, this::append);
        //ForEach loop method that appends elements in other StringList to a new LinkedStringList.
    }


/**
 * Add inserts an item into this string list.
 *
 * @param index represents where to add the item.
     * @param item is the item to enter at the specified index.
     * @return status is the boolean on whether the method ran succesfully.
     */
    @Override
    public boolean add(int index, String item) {
        boolean status = false;
        if (item == null) {
            throw new NullPointerException("item can not be null");
        } else if (item.isEmpty()) {
            throw new IllegalArgumentException("item can not be empty");
        } else if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("index: " + index
            + " is out of bounds and size: " + size());
        } else if (head == null) {
            this.head = new Node(item, null);
            status = true;
        } else {
            Node currentNode = this.head;
            Node prevNode = null;
            for (int i = 0; i < index; i++) {
                prevNode = currentNode;
                currentNode = currentNode.getNext();
            }
            if (currentNode == null) {
                prevNode.setNext(new Node(item, null));
            } else {
                if (prevNode == null) {
                    head = new Node(item, currentNode);
                } else {
                    prevNode.setNext(new Node(item, currentNode));
                }
            }
            status = true;
        }
        this.size++;
        return status;
    }

    /**
     * clears the head node.
     */
    public void clear() {
        size = 0;
        this.head = null;
    }

    /**
     * get returns the item at the specifed index.
     *
     * @param index
     * @return item
     */
    @Override
    public String get(int index) {
        String item = "";
        int count = 0;
        Node temp = head;
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("index is out of bounds");
        } else {
            while (temp != null) {
                if (count == index) {
                    item = temp.getItem();
                    break;
                }
                count++;
                temp = temp.getNext();
            }
        }
        return item;
    }

    /**
     * removes the item at the specified index.
     *
     * @param index
     * @return item
     */
    @Override
    public String remove(int index) {
        String item = "";
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("index is out of bounds");
        } else {
            if (index == 0) {
                if (head != null) {
                    item = head.getItem();
                    head = head.getNext();
                }
            } else if (index == (size - 1)) {
                Node prev = getNode(index - 1);
                item = prev.getNext().getItem();
                prev.setNext(null);
            } else {
                Node prev = head;
                for (int i = 0; i < index - 1; i++) {
                    prev = prev.getNext();
                }
                item = prev.getNext().getItem();
                Node next = prev.getNext().getNext();
                prev.setNext(next);
            }
        }
        this.size--;
        return item;
    }

    /**
     * finds the node in a linkedlist.
     *
     * @param index
     * @return temp
     */
    public Node getNode(int index) {
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }
        return temp;
    }

    /**
     * trims a linked list from specifed points.
     *
     * @param start is where the linked list starts.
     * @param stop is where the linked list ends.
     * @return slice is the node after it has been cut.
     */
    @Override
    public StringList slice(int start, int stop) {
        StringList slice = new LinkedStringList();
        if (start < 0 || stop > size() || start > stop) {
            throw new IndexOutOfBoundsException("start and stop out of bounds");
        } else {
            int index = 0;
            for (int i = start; i < stop; i++) {
                slice.add(index++,get(i));
            }

        }
        return slice;
    }

    /**
     * slices a list from the start to the stop and steps over by the input of the user.
     *
     * @param start
     * @param stop
     * @param step
     * @return FancyStringList
     */
    public FancyStringList slice(int start, int stop, int step) {
        FancyStringList sliced = new LinkedStringList();
        if (start < 0 || stop > size() || start > stop || step < 1) {
            throw new IndexOutOfBoundsException("index is out of bounds");
        } else {
            for (int i = start, j = 0; i < stop; i += step) {
                sliced.add(j++, get(i));
            }
        }
        return sliced;
    }

    /**
     * reverses a stringlist.
     *
     * @return FancyStringList
     */
    public FancyStringList reverse() {
        FancyStringList reverse = new LinkedStringList();
        reverseForEach(this, reverse::append);
        //reverseForEach loop method that appends elements in a StringList in reverse order.
        return reverse;
    }
}
