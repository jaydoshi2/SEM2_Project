import java.util.Scanner;

class GFG {

    static class DQueNode {
        int value;
        DQueNode next;
        DQueNode prev;
    }

    static class deque {

        private DQueNode head;
        private DQueNode tail;

        public deque() {
            head = tail = null;
        }

        boolean isEmpty() {
            if (head == null)
                return true;

            return false;
        }

        int size() {

            if (!isEmpty()) {
                DQueNode temp = head;
                int len = 0;

                while (temp != null) {
                    len++;
                    temp = temp.next;
                }
                return len;
            }
            return 0;
        }

        void insert_first(int element) {

            DQueNode temp = new DQueNode();
            temp.value = element;
            if (head == null) {
                head = tail = temp;
                temp.next = temp.prev = null;
            } else {
                head.prev = temp;
                temp.next = head;
                temp.prev = null;
                head = temp;
            }
        }

        void insert_last(int element) {

            DQueNode temp = new DQueNode();
            temp.value = element;

            if (head == null) {
                head = tail = temp;
                temp.next = temp.prev = null;
            } else {
                tail.next = temp;
                temp.next = null;
                temp.prev = tail;
                tail = temp;
            }
        }

        void remove_first() {

            if (!isEmpty()) {
                DQueNode temp = head;
                head = head.next;
                head.prev = null;

                return;
            }
            System.out.print("List is Empty");
        }

        void remove_last() {

            try {
                if (!isEmpty()) {
                    DQueNode temp = tail;
                    tail = tail.prev;
                    if (tail != null) {

                        tail.next = null;
                    }
                    return;
                }
            } catch (Exception e) {
                System.out.print("THE DATA STRUCTURE IS EMPTY");
            }

        }

        void display() {
            if (!isEmpty()) {
                DQueNode temp = head;

                while (temp != null) {
                    System.out.println("| " + temp.value + " |");
                    System.out.println("-----");
                    temp = temp.next;
                }

                return;
            }
            System.out.print("List is Empty");
        }

        void display_queue() {
            if (!isEmpty()) {
                DQueNode temp = head;

                while (temp != null) {
                    System.out.print("| " + temp.value + " |");
                    if(temp.next!=null){
                    System.out.print("----->");
                    }
                    temp = temp.next;
                }
                // System.out.println("NULL");
                return;
            }
            System.out.print("List is Empty");
        }

        void display_dequeue() {
            if (!isEmpty()) {
                DQueNode temp = head;

                while (temp != null) {
                    System.out.print("| " + temp.value + " |");
                    if(temp.next!=null)
                    System.out.print("<----->");
                    temp = temp.next;
                }
                return;
            }
            System.out.print("List is Empty");
        }
    }

    static class Stack {
        deque d = new deque();

        public void push(int element) {
            d.insert_last(element);
        }

        public int size() {
            return d.size();
        }

        public void pop() {
            d.remove_last();
        }

        public void display() {
            d.display();
        }
    }

    static class Queue {
        deque d = new deque();

        public void enqueue(int element) {
            d.insert_last(element);
        }

        public void dequeue() {
            d.remove_first();
        }

        public void display_queue() {
            d.display_queue();
        }

        public int size() {
            return d.size();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Stack stk = new Stack();
        Queue que = new Queue();
        deque d = new deque();
        System.out.println("-------------------------------------------------");

        boolean flag1 = true;
        while (flag1) {
            System.out.println("CHOOSE THE OPTION ");
            System.out.println("1)STACK");
            System.out.println("2)QUEUE");
            System.out.println("3)DEQUE");
            System.out.println("4)EXIT");
            int choice = sc.nextInt();
            if (choice == 1) {
                System.out.println("ENTER THE NUMBER OF ELEMENTS YOU WANT TO ENTER IN THE STACK");
                int n = sc.nextInt();

                for (int i = 0; i < n; i++) {
                    System.out.println("ENTER THE " + (i + 1) + " ELEMENT");
                    int element = sc.nextInt();
                    stk.push(element);
                }
                System.out.println("-------------------------------------------------");

                boolean flag2 = true;
                while (flag2) {
                    System.out.println("CHOOSE THE OPERATION");
                    System.out.println("1)PUSH ELEMENT");
                    System.out.println("2)POP ELEMENT");
                    System.out.println("3)DISPLAY STACK");
                    System.out.println("4)EXIT");
                    int choice2 = sc.nextInt();
                    switch (choice2) {
                        case 1:
                            System.out.println("ENTER THE NUMBER OF ELEMENTS YOU WANT TO PUSH IN THE STACK");
                            int n2 = sc.nextInt();

                            for (int i = 0; i < n2; i++) {
                                System.out.println("ENTER THE " + (i + 1) + " ELEMENT");
                                int element = sc.nextInt();
                                stk.push(element);
                            }
                            break;
                        case 2:
                            stk.pop();
                            System.out.println();
                            System.out.println("ELEMENT IS POPPED");
                            System.out.println();
                            break;
                        case 3:
                            System.out.println();
                            System.out.println("DISPLAYING THE STACK");
                            stk.display();
                            System.out.println("  ^");
                            System.out.println("  |");
                            System.out.println(" Top");
                            System.out.println();
                            System.out.println("SIZE OF STACK IS  " + stk.size());
                            System.out.println();
                            break;
                        case 4:
                            flag2 = false;
                            break;
                        default:
                            System.out.println("ENTER A VALID CHOICE");
                            break;
                    }
                }
            } else if (choice == 2) {
                System.out.println("-----------------------------------------------------");
                System.out.println("ENTER THE NUMBER OF ELEMENTS YOU WANT TO ENTER IN THE QUEUE");
                int n = sc.nextInt();

                for (int i = 0; i < n; i++) {
                    System.out.println("ENTER THE " + (i + 1) + " ELEMENT");
                    int element = sc.nextInt();
                    que.enqueue(element);
                }
                System.out.println("-------------------------------------------------");

                boolean flag3 = true;
                while (flag3) {
                    System.out.println("CHOOSE THE OPERATION");
                    System.out.println("1)INSERT ELEMENT");
                    System.out.println("2)DEQUEUE ELEMENT");
                    System.out.println("3)DISPLAY QUEUE");
                    System.out.println("4)EXIT");
                    int choice2 = sc.nextInt();
                    switch (choice2) {
                        case 1:
                            int insert_choice = sc.nextInt();
                            if (insert_choice == 1) {
                                System.out.println("ENTER THE NUMBER OF ELEMENTS YOU WANT TO ENQUEUE IN QUEUE");
                                int n2 = sc.nextInt();

                                for (int i = 0; i < n2; i++) {
                                    System.out.println("ENTER THE " + (i + 1) + " ELEMENT");
                                    int element = sc.nextInt();
                                    que.enqueue(element);
                                }
                            }
                            break;
                        case 2:
                            que.dequeue();
                            System.out.println("ELEMENT IS DELETED ");
                            break;
                        case 3:
                            System.out.println("DISPLAYING QUEUE");
                            System.out.println();
                            que.display_queue();
                            System.out.println();
                            System.out.println("Size of queue is " + que.size());
                            break;
                        case 4:
                            flag3 = false;
                            break;
                        default:
                            System.out.println("ENTER A VALID CHOICE");
                            break;
                    }
                }
            } else if (choice == 3) {
                System.out.println("-----------------------------------------------------");
                System.out.println("ENTER THE NUMBER OF ELEMENTS YOU WANT TO ENTER IN THE DEQUEUE");
                int n = sc.nextInt();

                for (int i = 0; i < n; i++) {
                    System.out.println("ENTER THE " + (i + 1) + " ELEMENT");
                    int element = sc.nextInt();
                    d.insert_last(element);
                }
                System.out.println("-------------------------------------------------");

                boolean flag3 = true;
                while (flag3) {
                    System.out.println("CHOOSE THE OPERATION");
                    System.out.println("1)INSERT ELEMENT");
                    System.out.println("2)DEQUEUE ELEMENT");
                    System.out.println("3)DISPLAY QUEUE");
                    System.out.println("4)EXIT");
                    int choice2 = sc.nextInt();
                    switch (choice2) {
                        case 1:
                            System.out.println("HOW DO YOU WANT TO INSERT");
                            System.out.println("1)INSERT FIRST");
                            System.out.println("2)INSERT LASAT");
                            int insert_choice = sc.nextInt();
                            if (insert_choice == 1) {
                                System.out.println("ENTER THE NUMBER OF ELEMENTS YOU WANT TO ENQUEUE IN QUEUE");
                                int n2 = sc.nextInt();

                                for (int i = 0; i < n2; i++) {
                                    System.out.println("ENTER THE " + (i + 1) + " ELEMENT");
                                    int element = sc.nextInt();
                                    d.insert_first(element);
                                }
                            } else if (insert_choice == 2) {
                                System.out.println("ENTER THE NUMBER OF ELEMENTS YOU WANT TO ENQUEUE IN QUEUE");
                                int n2 = sc.nextInt();

                                for (int i = 0; i < n2; i++) {
                                    System.out.println("ENTER THE " + (i + 1) + " ELEMENT");
                                    int element = sc.nextInt();
                                    d.insert_last(element);
                                }
                            } else {
                                System.out.println("ENTER A VALID CHOICE");
                            }
                            break;
                        case 2:
                            System.out.println("HOW DO YOU WANT TO DELETE");
                            System.out.println("1)DELETE FIRST");
                            System.out.println("2)DELETE LAST");
                            int delete_choice = sc.nextInt();
                            if (delete_choice == 1) {
                                d.remove_first();
                            } else if (delete_choice == 2) {
                                d.remove_last();
                            } else {
                                System.out.println("ENTER A VALID CHOICE");
                            }
                            break;
                        case 3:
                            System.out.println("DISPLAYING DEQUEUE");
                            System.out.println();
                            d.display_dequeue();
                            System.out.println();
                            System.out.println("SIZE OF DEQUE " + d.size());
                            break;
                        case 4:
                            flag3 = false;
                            break;
                        default:
                            System.out.println("ENTER A VALID CHOICE");
                            break;
                    }
                }
            } else if (choice == 4) {
                flag1 = false;

            } else {
                System.out.println("ENTER A VALID CHOICE");
            }
        }
        System.out.println("---------------------------------");
        sc.close();
    }
}