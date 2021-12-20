package cs1302.p3;

import cs1302.adt.StringList;
import cs1302.adt.FancyStringList;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.function.Consumer;

/**
 * creates methods that are used by arraylists and nodes implements
 * StringList.
 *
 */
public abstract class BaseStringList implements FancyStringList {

    protected int size;

    /**
     * creates an empty string list.
     */
    public BaseStringList() {
        this.size = 0;
    }

    /**
     * appends an item to this string list.
     *
     * @param item
     * @return append
     */
    public boolean append(String item) {
        boolean append = false;
        if (item == null) {
            throw new NullPointerException("item is null");
        } else if (item == "") {
            throw new IllegalArgumentException("item is empty");
        } else {
            this.add(size, item);
            append = true;
        }
        return append;
    }

    /**
     * returns true if the string list has no item.
     *
     * @return boolean
     */
    public boolean isEmpty() {
        if (this.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * returns a string representation of this string list that begins
     * with start and ends.
     *
     * @param start
     * @param sep
     * @param end
     * @return result
     */
    public String makeString(String start, String sep, String end) {
        String result = "";
        for (int i = 0; i < this.size(); i++) {
            if (i != size()) {
                result += this.get(i);
            }
            if (i != size() - 1) {
                result += sep;
            }
        }
        return start + result + end;
    }

    /**
     * prepends an item to this string list.
     *
     * @param item
     * @return prepend
     */
    public boolean prepend(String item) {
        boolean prepend = false;
        if (item == null) {
            throw new NullPointerException("item is null");
        } else if (item == "") {
            throw new IllegalArgumentException("item is empty");
        } else {
            this.add(0, item);
            prepend = true;
        }
        return prepend;
    }

    /**
     * returns the number of items in this string list.
     *
     * @return list.size()
     */
    public int size() {
        return size;
    }

    /**
     * returns the makeString with brackets and commas.
     *
     * @return makeString()
     */
    public String toString() {
        return makeString("[", ", ", "]");
    }

    /**
     * returns a boolean after a stringList is added to a specfied index.
     *
     * @param index
     * @param items
     * @return list
     */
    public boolean add(int index, StringList items) {
        boolean list = false;
        if (items == null) {
            throw new NullPointerException("items is null");
        } else if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index is out of bounds at add method: " + index);
        } else {
            reverseForEach(items, input ->  this.add(index, input));
            /* Lambda Expression of consumer along with method written to reverse and save items to
             * a new list.
             */
            list = true;
        }
        return list;
    }

    /**
     * returns a boolean after adding StringList to the end of a list.
     *
     * @param items
     * @return app
     */
    public boolean append(StringList items) {
        boolean app = false;
        if (items == null) {
            throw new NullPointerException("Items is null");
        } else {
            add(size(), items);
            app = true;
        }
        return app;
    }

    /**
     * reverses and inputs values from a StringList into another StringList.
     *
     * @param list
     * @param consumer
     */
    public void reverseForEach(StringList list, Consumer<String> consumer) {
        for (int i = list.size() - 1 ; i >= 0; i--) {
            String input = list.get(i);
            consumer.accept(input);
            //Uses consumer functional interface to make a list of inputs in reverse order.
        }
    }

    /**
     * inputs values from a StringLIst into antoher StringList.
     *
     * @param list
     * @param consumer
     */
    public void forEach(StringList list, Consumer<String> consumer) {
        for (int i = 0; i < list.size(); i++) {
            String input = list.get(i);
            consumer.accept(input);
            //Uses consumer functional interface to make a list of inputs from a StringList.
        }
    }

    /**
     * returns a boolean after searching for a target string from specified index in a list.
     *
     * @param start
     * @param target
     * @return contain
     */
    public boolean contains(int start, String target) {
        boolean contain = false;
        if (start < 0 || start >= size() || target == null) {
            return contain;
        } else {
            for (int i = start; i < size(); i++) {
                if (this.get(i).equals(target)) {
                    contain = true;
                }
            }
        }
        return contain;
    }


    /**
     * finds the index of a word from a specfied index.
     *
     * @param start
     * @param target
     * @return k
     */
    public int indexOf(int start, String target) {
        int k = -1;
        if (start < 0 || start >= size() || target == null) {
            return k;
        } else {
            for (int i = start; i < size(); i++) {
                if (this.get(i).equals(target)) {
                    k = i;
                    break;
                }
            }
        }
        return k;
    }

    /**
     * prepends a StringList to another Stringlist.
     *
     * @param items
     * @return prepend
     */
    public boolean prepend(StringList items) {
        boolean prepend = false;
        if (items == null) {
            throw new NullPointerException("items is null");
        } else {
            this.add(0, items);
            prepend = true;
        }
        return prepend;
    }
}
