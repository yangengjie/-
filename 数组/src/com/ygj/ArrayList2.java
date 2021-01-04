package com.ygj;

/**
 * 动态数组的优化
 *
 */
public class ArrayList2<E> extends AbstractList<E> {
    private E[] elements;
    private static final int DEFAULT_CAPACTITY = 10;
    // 记录数组中首元素的位置，默认为0
    private int front;

    public ArrayList2() {
        this(DEFAULT_CAPACTITY);
    }

    public ArrayList2(int capacity) {
        if (capacity <= DEFAULT_CAPACTITY)
            capacity = DEFAULT_CAPACTITY;
        elements = (E[]) new Object[capacity];
    }

    /**
     * 向指定位置添加元素
     * 
     * @param index
     * @param element
     */
    public void add(int index, E element) {
        checkIndexForAdd(index);
        ensureCapacity(size + 1);
        if (index >= size >> 1) { // 向后移动
            for (int i = size - 1; i >= index; i--) {
                elements[index(i + 1)] = elements[index(i)];
            }
        } else { // 向前移动
            for (int i = 0; i < index; i++) {
                elements[index(i - 1)] = elements[index(i)];
            }
            front = index(-1);
        }
        elements[index(index)] = element;
        size++;
    }

    // 修正index
    private int index(int index) {
        index += front;
        if (index < 0)
            index += elements.length;
        else
            index = index % elements.length;
        return index;
    }

    /**
     * 扩容
     * 
     * @param capactity
     */
    private void ensureCapacity(int capactity) {
        if (capactity >= elements.length) {
            int newCapacity = capactity + (capactity >> 1);
            System.out.println("扩容 oldCapactity:" + elements.length + " newCapacity:" + newCapacity);
            E[] newElements = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[index(i)];
            }
            elements = newElements;
            front = 0;
        }
    }

    /**
     * 获取指定位置的元素
     * 
     * @param index
     * @return
     */
    public E get(int index) {
        checkIndex(index);
        return elements[index(index)];
    }

    /**
     * 设置index位置的元素
     * 
     * @param index
     * @param element
     */
    @Override
    public void set(int index, E element) {
        checkIndex(index);
        elements[index(index)] = element;
    }

    /**
     * 删除指定位置的元素
     * 
     * @param index
     * @return
     */
    public E remove(int index) {
        checkIndex(index);
        E oldE = elements[index(index)];
        if (index >= size >> 1) { // 向前移动
            for (int i = index; i < size - 1; i++) {
                elements[index(i)] = elements[index(i + 1)];
            }
            elements[index(size - 1)] = null;
        } else { // 向后移动
            for (int i = index; i > 0; i--) {
                elements[index(i)] = elements[index(i - 1)];
            }
            elements[front] = null;
            front = index(1);
        }
        size--;
        if (size == 0)
            front = 0;
        trim();
        return oldE;
    }

    /**
     * 缩容：当size==capactity/2时就进行缩容,缩小为容量的一半
     */
    private void trim() {
        int newCapacity = elements.length >> 1;
        if (size <= newCapacity && elements.length > DEFAULT_CAPACTITY) {
            E[] newElement = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newElement[i] = elements[index(i)];
            }
            System.out.println(elements.length + "缩容为" + newCapacity);
            front = 0;
            elements = newElement;
        }
    }

    /**
     * 删除元素
     * 
     * @param element
     * @return
     */
    public E remove(E element) {
        return remove(indexOf(element));
    }

    /**
     * 返回指定元素的位置
     * 
     * @param element
     * @return 返回-1，表示未找到元素
     */
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (elements[index(i)] == null)
                    return i;
            } else {
                if (element.equals(elements[index(i)]))
                    return i;
            }
        }
        return -1;
    }

    /**
     * 清空元素
     */
    public void clear() {
        for (E e : elements)
            e = null;
        size = 0;
        front = 0;
        // 缩容
        if (elements != null && elements.length > DEFAULT_CAPACTITY)
            elements = (E[]) new Object[DEFAULT_CAPACTITY];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size=" + size + " capactity=" + elements.length + "  front=" + front + "  [");
        for (int i = 0; i < elements.length; i++) {
            sb.append(elements[i]);
            if (i != elements.length - 1)
                sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}
