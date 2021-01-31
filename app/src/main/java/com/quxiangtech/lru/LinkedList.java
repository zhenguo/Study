package com.quxiangtech.lru;

public class LinkedList<T> {
    Node head; // 链表头部
    Node curr;
    int size;

    public static void main(String[] args) {
        LinkedList<Integer> integerLinkedList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            integerLinkedList.add(i, i + 1);
        }


        for (LinkedList.Node curr = integerLinkedList.head; curr != null; curr = curr.next) {
            System.out.println("0 : " + curr.data);
        }

//        integerLinkedList.removeLast();
//
//        for (LinkedList.Node curr = integerLinkedList.head; curr != null; curr = curr.next) {
//            System.out.println("0 : " + curr.data);
//        }

//        integerLinkedList.remove(9);
//        for (LinkedList.Node curr = integerLinkedList.head; curr != null; curr = curr.next) {
//            System.out.println("0 : " + curr.data);
//        }
        integerLinkedList.removeLast();
        System.out.println("after removeLast()");
//        integerLinkedList.set(7, 800);
        for (LinkedList.Node curr = integerLinkedList.head; curr != null; curr = curr.next) {
            System.out.println("0 : " + curr.data);
        }
//        System.out.println(integerLinkedList.get(7));
    }

    public LinkedList() {
        this.size = 0;
    }

    /**
     * 头部增加
     *
     * @param data
     */
    public void add(T data) {
        head = new Node(data, head);
        size++;
    }

    // 遍历至index，增加节点
    public void add(int index, T data) {
        if (index == 0) {
            add(data);
        } else {
            int position = 0;
            for (curr = head; curr != null; curr = curr.next) {
                if (position == index - 1)
                    break;
                position++;
            }
//            System.out.println("curr: " + curr.data);
            curr.next = new Node(data, curr.next);
        }

        size++;
    }

    // 删除
    public void removeLast() {
        curr = head;

        for (int i = 0; i < size - 3; i++) {
            System.out.println("curr data: " + curr.data);
            curr = curr.next;
        }
        System.out.println("curr222: " + curr.data.toString());
        curr.next = null; // GC
        size--;
    }

    public void remove(int index) {
        curr = head;
        for (int i = 0; i < index - 1; i++) {
            System.out.println("i: " + i + " curr: " + curr.data);
            curr = curr.next;
        }
        Node n = curr.next;
        curr.next = curr.next.next;
        n.next = null;
        n = null; // GC
        size--;
    }

    // 修改
    public void set(int index, T data) {
        curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        curr.data = data;
    }

    // 查询
    public T get(int index) {
        curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        System.out.println("c222: " + curr);
        return curr.data;
    }

    // 节点信息
    class Node {
        T data;
        Node next;

        public Node(T data, Node node) {
            this.data = data;
            this.next = node;
        }
    }
}
