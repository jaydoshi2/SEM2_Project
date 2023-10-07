import java.util.Stack;

 class StackVisualization {
    public static void main(String[] args) {
        // Create a stack using Java's built-in Stack class
        Stack<Integer> stack = new Stack<>();

        // Push elements onto the stack
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        // Print the stack
        System.out.println("Stack:");
        printStack(stack);

        // Pop an element from the stack
        int poppedElement = stack.pop();
        System.out.println("Popped Element: " + poppedElement);

        // Print the stack again
        System.out.println("Stack after popping:");
        printStack(stack);
    }

    // Helper method to print the contents of the stack
    public static void printStack(Stack<Integer> stack) {
        for (Integer element : stack) {
            System.out.println("| " + element + " |");
            System.out.println("-----");
        }
        System.out.println("  ^");
        System.out.println("  |");
        System.out.println("  Top");
    }
}
