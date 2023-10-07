// import java.util.Scanner;

// import Group_project.circularqueue;
// import Group_project.deque;
// import Group_project.simple_queue;
// import Group_project.stack;

// public class dsgroup {
//     public static void main(String[] args) throws Exception{
//         Scanner sc=new Scanner(System.in);
//         boolean check=true;
//         System.out.println("CONCEPTS OF DATA STRUCTURE");
//         do
//         {
//             System.out.println();   
//             System.out.println("ENTER YOUR CHOICE");
//             System.out.println("1.STACK");
//             System.out.println("2.SIMPLE QUEUE");
//             System.out.println("3.CIRCULAR QUEUE");
//             System.out.println("4.DOUBLE ENDED QUEUE (DEQUE)");
//             System.out.println("5.SINGLY LINKED LIST");
//             System.out.println("6.CIRCULAR LINKED LIST");
//             System.out.println("7.DOUBLY LINKED LIST");
//             System.out.println("8.EXIT");
//             int choice=sc.nextInt();
//             switch (choice) 
//             {
//                 case 1:
//                 {
//                     System.out.println("---------------------------------------");
//                     System.out.println("-------------STACK-------------");
//                     System.out.println("A STACK IS A LINEAR TYPE OF DATA STRUCTURE IN WHICH INSERTION AND DELETION OF ELEMENTS TAKE PLACE AT END");
//                     System.out.println("THE ORDER OF STACK IS LIFO OR FILO");
//                     System.out.println("LIFO := LAST IN FIRST OUT");
//                     System.out.println("FILO := FIRST IN LAST OUT");
//                     System.out.println();
//                     System.out.println("ENTER SIZE FOR STACK");
//                     int stack_size=sc.nextInt();
//                     stack st1=new stack(stack_size);
                            
//                             System.out.println();
//                             System.out.println("PUSH (INSERION)");
//                             for(int i=1;i<=stack_size;i++)
//                             {
//                                 System.out.println("ENTER NUM "+i+" TO PUSH");
//                                 int num1=sc.nextInt();
//                                 st1.push(num1);
//                             }
//                             System.out.println();
//                             st1.display();
//                             System.out.println();
//                             System.out.println("THERE ARE OTHER OPERATIONS IN STACK SUCH AS PEEP,CHANGE,POP");
//                             System.out.println();
//                             boolean check2=true;
//                             do {
//                                 System.out.println();
//                                 System.out.println("ENTER OPERATION YOU WANT TO PERFORM");
//                                 System.out.println("1.PEEP := SHOWS ELEMENT OF PARTICULAR INDEX");
//                                 System.out.println("2.CHANGE := CHANGES THE ELEMENT OF PARTICULAR INDEX TO NEW ELEMENT");
//                                 System.out.println("3.POP := DELETEION OF AN ELEMENT");
//                                 System.out.println("4.EXIT FROM LOOP");
//                                 int ch=sc.nextInt();
//                                 switch (ch) 
//                                 {
//                                     case 1:
//                                     {
//                                         System.out.println("ENTER INDEX");
//                                         int num3=sc.nextInt();
//                                         st1.peep(num3);
//                                         System.out.println();
//                                         st1.display();
//                                         break;
//                                     }
//                                     case 2:
//                                     {
//                                         System.out.println("ENTER INDEX NUM");
//                                         int index=sc.nextInt();
//                                         System.out.println("ENTER NEW ELEMENT");
//                                         int change=sc.nextInt();
//                                         st1.change(index, change);
//                                         System.out.println();
//                                         st1.display();
//                                         break;                                        
//                                     }
//                                     case 3:
//                                     {
//                                         System.out.println("ENTER NUM OF TIMES YOU WANT TO POP");
//                                         int num2=sc.nextInt();
//                                         for (int i = 1; i <= num2; i++) 
//                                         {
//                                             st1.pop();    
//                                         }
                                        
//                                         System.out.println();
//                                         st1.display();
//                                         break;
//                                     }
//                                     case 4:
//                                     {
//                                         check2=false;
//                                         break;
//                                     }
//                                     default:
//                                     {
//                                         System.out.println("ENTER VALID CHOICE");
//                                         break;
//                                     }
//                                 }
//                             } while (check2);
//                             System.out.println("---------------------------------------");
//                     break;
//                 }    
//                 case 2:
//                 {
//                     System.out.println("---------------------------------------");
//                     System.out.println("-------------SIMPLE QUEUE-------------");
//                     System.out.println("A QUEUE IS A LINEAR TYPE OF DATA STRUCTURE WHICH IS OPEN AT BOTH END");
//                     System.out.println("THE ORDER IS FIFO");
//                     System.out.println("FIFO := FIRST IN FIRST OUT");
//                     System.out.println();
//                     System.out.println("ENTER SIZE FOR QUEUE");
//                     int queue_size=sc.nextInt();
//                     simple_queue sq1=new simple_queue(queue_size);

//                             System.out.println();
//                             System.out.println("ENQUEUE (INSERION)");
//                             for(int i=1;i<=queue_size;i++)
//                             {
//                                 System.out.println("ENTER NUM "+i+" TO ADD");
//                                 int num1=sc.nextInt();
//                                 sq1.enqueue(num1);
//                             }
//                             sq1.display();
//                             System.out.println();

//                             boolean check3=true;
//                             do {
//                                 System.out.println("ENTER OPERATION YOU WANT TO PERFORM");
//                                 System.out.println("1.POSITION := IT SHOWS POSITION OF FRONT AND REAR ELEMENT");
//                                 System.out.println("2.DEQUEUE := IT DELETES THE FIRST ELEMENT ");
//                                 System.out.println("3.EXIT FROM LOOP");
//                                 int ch=sc.nextInt();
//                                 switch (ch) 
//                                 {
//                                         case 1:
//                                         {
//                                             sq1.position();
//                                             System.out.println();
//                                             break;
//                                         }
//                                         case 2:
//                                         {
//                                             System.out.println("ENTER NUM OF TIMES YOU WANT TO DEQUEUE");
//                                             int num2=sc.nextInt();
//                                             for (int i = 1; i <= num2; i++) 
//                                             {
//                                                 sq1.dequeue();    
//                                             }
//                                             sq1.display();
//                                             System.out.println();
//                                             break;
//                                         }
//                                         case 3:
//                                         {
//                                             check3=false;
//                                             break;
//                                         }
//                                         default:
//                                         {
//                                             System.out.println("ENTER VALID CHOICE");
//                                             break;
//                                         }
//                                 }
//                             } while (check3);
//                     System.out.println("---------------------------------------");
//                     break;
//                 }    
//                 case 3:
//                 {
//                     System.out.println("---------------------------------------");
//                     System.out.println("-------------CIRCULAR QUEUE-------------");
//                     System.out.println("CIRCULAR QUEUE IS A LINEAR TYPE OF DATA STRUCTURE IN WHICH THE LAST IS SUBJECTED TO FIRST POINTER");
//                     System.out.println("ENTER SIZE FOR CIRCULAR QUEUE");
//                     int queue_size=sc.nextInt();
//                     circularqueue sq1=new circularqueue(queue_size);
            
//                             System.out.println();
//                             System.out.println("ENQUEUE (INSERION)");
//                             for(int i=1;i<=queue_size;i++)
//                             {
//                                 System.out.println("ENTER NUM "+i);
//                                 int num1=sc.nextInt();
//                                 sq1.enqueue(num1);
//                             }
//                             sq1.display();
//                             boolean check4=true;
//                             do {
//                                 System.out.println();
//                                 System.out.println("ENTER OPERATION YOU WANT TO PERFORM");
//                                 System.out.println("1.POSITION := IT SHOWS POSITION OF FRONT AND REAR ELEMENT");
//                                 System.out.println("2.DEQUEUE := IT DELETES THE FIRST ELEMENT ");
//                                 System.out.println("3.INSERTION := TO ADD NEW ELEMENT");
//                                 System.out.println("4.EXIT FROM LOOP");
//                                 int ch=sc.nextInt();
//                                 switch (ch) 
//                                 {
//                                         case 1:
//                                         {
//                                             sq1.position();
//                                             System.out.println();
//                                             break;
//                                         }
//                                         case 2:
//                                         {
//                                             System.out.println("ENTER NUM OF TIMES YOU WANT TO DEQUEUE");
//                                             int num2=sc.nextInt();
//                                             for (int i = 1; i <= num2; i++) 
//                                             {
//                                                 sq1.dequeue();    
//                                             }
//                                             System.out.println();
//                                             sq1.display();
//                                             break;
//                                         }
//                                         case 3:
//                                         {
//                                             System.out.println("ENTER NUM TO INSERT");
//                                             int num=sc.nextInt();
//                                             sq1.enqueue(num);
//                                             sq1.display();
//                                             break;
//                                         }
//                                         case 4:
//                                         {
//                                             check4=false;
//                                             break;
//                                         }
//                                         default:
//                                         {
//                                             System.out.println("ENTER VALID CHOICE");
//                                             break;
//                                         }
//                                 }
//                             } while (check4);

//                     System.out.println("---------------------------------------");

//                     break;
//                 }    
//                 case 4:
//                 {
//                     System.out.println("---------------------------------------");
//                     System.out.println("-------------DEQUEUE-------------");
//                     System.out.println("DEQUE IS A LINEAR TYPE OF DATA STRUCTURE IN WHICH INSERTIN AND DELETION IS OPEN AT BOTH ENDS");
//                     System.out.println("ENTER SIZE FOR DEQUE QUEUE");
//                     int queue_size=sc.nextInt();
//                     deque dq=new deque(queue_size);
//                     System.out.println();
//                             System.out.println("INSERT AT FIRST (INSERION)");
//                             for(int i=1;i<=queue_size;i++)
//                             {
//                                 System.out.println("ENTER NUM "+i);
//                                 int num1=sc.nextInt();
//                                 dq.insertfront(num1);
//                             }
//                             dq.display();

//                             System.out.println("INSERT AT LAST (INSERION)");
//                             for(int i=1;i<=queue_size;i++)
//                             {
//                                 System.out.println("ENTER NUM "+i);
//                                 int num1=sc.nextInt();
//                                 dq.insertrear(num1);
//                             }
//                             dq.display();

//                             System.out.println();
//                             System.out.println("DELETE AT FRONT (DELETION)");
//                             System.out.println("ENTER NUM OF TIMES YOU WANT TO DELETE");
//                             int num2=sc.nextInt();
//                             for (int i = 1; i <= num2; i++) 
//                             {
//                                 dq.deletefront();    
//                             }
//                             dq.display();
//                             System.out.println();

//                             System.out.println("DELETE AT REAR (DELETION)");
//                             System.out.println("ENTER NUM OF TIMES YOU WANT TO DELETE");
//                             int num3=sc.nextInt();
//                             for (int i = 1; i <= num3; i++) 
//                             {
//                                 dq.deleterear();    
//                             }
//                             dq.display();
//                             System.out.println();

//                     System.out.println("---------------------------------------");

//                     break;
//                 }    
//                 case 5:
//                 {
//                     break;
//                 }    
//                 case 6:
//                 {
//                     break;
//                 }    
//                 case 7:
//                 {
//                     break;
//                 }    
//                 case 8:
//                 {
//                     check=false;
//                     break;
//                 }
//                 default:
//                 {
//                     System.out.println("ENTER VALID CHOICE");
//                     break;
//                 }    
//             }
//         }while(check);

//     }
// }





// class stack
// {
//     static int top;
//     int capacity,s[];
//     stack(int size)
//     {
//         s=new int[size];
//         top=-1;
//         capacity=size;
//     }
//     void push(int x)
//     {
//         if(top >= capacity-1)
//         {
//             System.out.println("OVERFLOW");
//         }
//         else
//         {
//             top++;
//             s[top]=x;
//         }
//     }
//     void pop()
//     {
//         if(top==-1)
//         {
//             System.out.println("UNDERFLOW");
//         }
//         else
//         {
//             System.out.println("POPPED ELEMENT = "+s[top]);
//             top--;
//         }
//     }
//     void peep(int i)
//     {
//         if(top == -1)
//         {
//             System.out.println("UNDERFLOW");
//         }
//         else
//         {
//             System.out.println("PEEPED ELEMENT = "+s[top-i+1]);
//         }
//     }
//     void change(int i,int x)
//     {
//         if(top -i +1 <= -1)
//         {
//             System.out.println("UNDERFLOW");
//         }
//         else
//         {
//             s[top - i + 1]=x;
//         }

//     }
//     void display()
//     {
//         if(top==-1)
//         {
//             System.out.println("EMPTY STACK");
//         }
//         else
//         {
//             System.out.println("STACK ELEMENTS");
//             for (int i = top; i >= 0; i--) 
//             {
//                 System.out.print(s[i]+" ");   
//             }
//             System.out.println();
//         }
// }
// }


// class simple_queue
// {
//     int q[];
//     static int front,rear;
//     int capacity;
//     simple_queue(int size)
//     {
//         front = -1;
//         rear = -1;
//         q=new int[size]; 
//         capacity=size;
//     }
//     void enqueue(int y)
//     {
//         if(rear >= capacity-1)
//         {
//             System.out.println("OVERFLOW "+rear);
//         }
//         else
//         {
//             rear++;
//             q[rear]=y;
//             if(front==-1)
//             {
//                 front=0;
//             }
//         }
//     }
//     void dequeue()
//     {
//         if(front==-1)
//         {
//             System.out.println("UNDERFLOW");
//         }
//         else
//         {
//             int y;
//             y=q[front];
//             if(front==rear-1)
//             {
//                 front=-1;
//                 rear=-1;
//             }
//             else
//             {
//                 front++;
//             }
//             System.out.println("DELETED ELEMENT = "+y);
//         }
//     }
//     void position()
//     {
//         System.out.println("FRONT ELEMENT = "+q[front]);
//         System.out.println("REAR ELEMENT = "+q[rear]);

//     }
//     void display()
//     {
//         System.out.println("SIMPLE QUEUE");
//         for(int i=front;i<=rear;i++)
//         {
//             System.out.print(q[i]+" ");
//         }
//         System.out.println();
//     }
// }


// class circularqueue
// {
//     int front,rear,size;
//     int q[];
//     circularqueue(int size)
//     {
//         this.size=size;
//         front=-1;
//         rear=-1;
//         q=new int[size];
//     }
//     void enqueue(int y)
//     {
//         if(front == (rear+1)% size)
//         {
//             System.out.println("OVERFLOW");
//         }
//         else
//         {
//             rear = (rear+1)%size;
//             q[rear]=y;
//             if(front==-1)
//             {
//                 front=0;
//             }
//         }
//     }
//     void dequeue()
//     {
//         if(front==-1)
//         {
//             System.out.println("UNDERFLOW");
//         }
//         else
//         {
//             int y;
//             y=q[front];
//             if(front==rear)
//             {
//                 front=-1;
//                 rear=-1;
//             }
//             else
//             {
//                 if(front == size-1)
//                 {
//                     front=0;
//                 }
//                 else
//                 {
//                     front++;
//                 }
//             }
//             System.out.println("DELETED ELEMENT = "+y);
//         }
//     }
//     void position()
//     {
//         System.out.println("FRONT POINTER = "+q[front]);
//         System.out.println("REAR POINTER = "+q[rear]);
//     }
//     void display()
//     {
//         if(front==-1)
//         {
//             System.out.println("EMPTY QUEUE");
//         }
//         else
//         { 
//             System.out.println("CIRCULAR QUEUE");
//             int i;
//             for(i=front;i!=rear;i=(i+1)%size)
//             {
//                 System.out.print(q[i]+" ");
//             }
//             System.out.println(q[i]);
//             System.out.println();
//         }
//     }
// }


// class deque
// {
//     int front,rear,size;
//     int q[];
//     deque(int size)
//     {
//         this.size=size;
//         front=-1;
//         rear=-1;
//         q=new int[size];
//     }
//     void insertfront(int y)
//     {
//         if(front == (rear+1)%size)
//         {
//             System.out.println("OVERFLOW");
//         }
//         else if(front == -1 && rear ==-1 )
//         {
//             front=0;rear=0;
//             q[front]=y;
//         }
//         else if(front == 0)
//         {
//             front = size-1;
//             q[front]=y;
//         }
//         else
//         {
//             front--;
//             q[front]=y;
//         }
//     }    
//     void insertrear(int y)
//     {
//         if(front == (rear+1)%size)
//         {
//             System.out.println("OVERFLOW");
//         }
//         else if(front==-1 && rear==-1)
//         {
//             front=0;rear=0;
//             q[rear]=y;
//         }
//         else if(rear == size - 1)
//         {
//             rear=0;
//             q[rear]=y;
//         }
//         else
//         {
//             rear++;
//             q[rear]=y;
//         }
//     }
//     void deletefront()
//     {
//         if(front==-1)
//         {
//             System.out.println("UNDERFLOW");
//         }
//         else if(front==size-1)
//         {
//             System.out.println("DELETED ELEMENT = "+q[front]);
//             front=0;
//             rear=0;
//         }
//         else if(front==rear)
//         {
//             System.out.println("deleted element is "+q[front]);
//             front=-1;
//             rear=-1;
//         }
//         else
//         {
//             System.out.println("deleted element is "+q[front]);
//             front++;
//         }
//     }    
//     void deleterear()
//     {
//         if(front==-1)
//         {
//             System.out.println("underflow");
//         }
//         else if(rear==0)
//         {
//             System.out.println("deleted element is "+q[rear]);
//             rear=size-1;
//         }
//         else if(front==rear)
//         {
//             System.out.println("deleted element is "+q[rear]);
//             front=-1;
//             rear=-1;
//         }
//         else
//         {
//             System.out.println("deleted element is "+q[rear]);
//             rear--;
//         }
//     }
//     void display()
//     {
//         if(front==-1)
//         {
//             System.out.println("empty queue");
//         }
//         else
//         { int i;
//             for(i=front;i!=rear;i=(i+1)%size)
//             {
//                 System.out.print(q[i]+" ");
//             }
//             System.out.println(q[i]);
//             System.out.println();
//         }
//     }
//     void position()
//     {
//          if(front==-1)
//         {
//             System.out.println("empty queue");
//         }
//         else
//         {
//             System.out.println("front element is "+q[front]);            
//             System.out.println("rear element is "+q[rear]);

//         }
//     }

// } 