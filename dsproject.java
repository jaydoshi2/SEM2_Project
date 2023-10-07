import java.util.*;
class LinkedList2 {
   public class Node {
        Emp_details ed;
        Node next;

        public Node(Emp_details ed) {
            this.ed = ed;
            this.next = null;
        }
    }

    Node first = null;

    void insertAtLast(Emp_details ed) {
        Node newNode = new Node(ed);

        if (first == null) {
            first = newNode;
        } else {
            Node temp = first;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    void display() {
        Node temp = first;

        while (temp != null) {
            System.out.print(temp.ed + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    void deleteAtLast() {
        if (first == null) {
            System.out.println("No element in the list");
        } else if (first.next == null) {
            first = null;
            System.out.println("The only element has been deleted");
        } else {
            Node temp = first;
            while (temp.next.next != null) {
                temp = temp.next;
            }
            temp.next = null;
        }
    }

    Node mergeLists(Node l1, Node l2) {
        if (l1 == null) {
            return l2;
        } else {
            Node temp = l1;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = l2;
            return l1;
        }
    }
}

class Emp_details {
    String name;
    double salary;
    String dept;

    public Emp_details(String name, double salary, String dept) {
        this.name = name;
        this.salary = salary;
        this.dept = dept;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public String getDept() {
        return dept;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "emp_details [name=" + name + ", salary=" + salary + ", dept=" + dept + "]";
    }
}

public class dsproject {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList2 ll1 = new LinkedList2();
        LinkedList2 ll2 = new LinkedList2();

        while (true) {
            System.out.println("1. Enter the details of an employee");
            System.out.println("2. Delete the details of an employee");
            System.out.println("3. Update the details of an employee");
            System.out.println("4. Display the details of employees");
            System.out.println("5. Merge the details of employees");
            System.out.println("6. Exit");

            int ch = sc.nextInt();

            switch (ch) {
                case 1: {
                    System.out.println("Enter the employee name");
                    String name = sc.next();
                    System.out.println("Enter the salary of the employee");
                    double salary = sc.nextDouble();
                    System.out.println("Enter the employee department");
                    String dept = sc.next();
                    Emp_details ed = new Emp_details(name, salary, dept);

                    if (dept.equalsIgnoreCase("It")) {
                        ll1.insertAtLast(ed);
                    } else {
                        ll2.insertAtLast(ed);
                    }
                    break;
                }
                case 2: {
                    System.out.println("Enter the employee name you want to remove");
                    String name = sc.next();
                    System.out.println("Enter the employee department from which you want to remove");
                    String dept = sc.next();
                    if (dept.equalsIgnoreCase("It")) {
                        ll1.deleteAtLast();
                    } else if (dept.equalsIgnoreCase("ce")) {
                        ll2.deleteAtLast();
                    } else {
                        System.out.println("Department not found");
                    }
                    break;
                }
                case 3: {
                    System.out.println("Enter the employee name you want to update the salary of");
                    String name = sc.next();
                    System.out.println("Enter the new salary");
                    double n_salary = sc.nextDouble();
                    System.out.println("Enter the department of the employee");
                    String dept = sc.next();
                    if (dept.equalsIgnoreCase("It")) {
                        // Update in ll1
                        LinkedList2.Node temp = ll1.first;
                        while (temp != null) {
                            if (temp.ed.getName().equals(name)) {
                                temp.ed.setSalary(n_salary);
                                System.out.println("Element updated");
                                break;
                            }
                            temp = temp.next;
                        }
                    } else if (dept.equalsIgnoreCase("ce")) {
                        // Update in ll2
                        LinkedList2.Node temp = ll2.first;
                        while (temp != null) {
                            if (temp.ed.getName().equals(name)) {
                                temp.ed.setSalary(n_salary);
                                System.out.println("Element updated");
                                break;
                            }
                            temp = temp.next;
                        }
                    } else {
                        System.out.println("Department not found");
                    }
                    break;
                }
                case 4: {
                    System.out.println("The employees in the IT department are:");
                    int i = 0;
                    LinkedList2.Node temp = ll1.first;
                    while (temp != null) {
                        System.out.println(i++ + ". " + temp.ed);
                        temp = temp.next;
                    }

                    System.out.println("The employees in the CE department are:");
                    int j = 0;
                    temp = ll2.first;
                    while (temp != null) {
                        System.out.println(j++ + ". " + temp.ed);
                        temp = temp.next;
                    }
                    break;
                }
                case 5: {
                    System.out.println("Merging the two linked lists");
                    LinkedList2 mergedList = new LinkedList2();
                    mergedList.first = mergedList.mergeLists(ll1.first, ll2.first);
                    break;
                }
                case 6: {
                    System.out.println("Exiting the system...");
                    sc.close();
                    System.exit(0);
                }
            }
        }
    }
}
