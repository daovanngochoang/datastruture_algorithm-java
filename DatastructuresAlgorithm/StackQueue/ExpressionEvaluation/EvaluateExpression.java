package DatastructuresAlgorithm.StackQueue.ExpressionEvaluation;


import java.util.Stack;

public class EvaluateExpression {

    public int Evaluate_value(String expression){
        /*
        - need a stack to carry number.

        - if it is a number:
            ==> cast to int by calculating digit in the decimal number from right to left:
                125 : with n = 0;
                    n = n*10 + (int) (character - '0')

                    0*10 + 1 = 1;
                    1*10 +2 = 12;
                    12*10 +5 = 125
                then push n to stack.
        - if character == space ==> do nothing;
        - if it is operator :
             => pop value from stack and do the operation with that values.
         */

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++){ // run loop as normal

            char c = expression.charAt(i); // get the char item

            if (c == ' '){ // if it is space ==> does no thing.
                continue;

            } else if (Character.isDigit(c)){ // if it is digit (number)
                int n = 0; // we initial assign n = 0

                while (Character.isDigit(c)){ // run a loop from that value to calculate that number

                    n = n*10 + (int) (c - '0'); // this is the formula of calculate value of decimal digit
                    c = expression.charAt(++i); // get the next digit to continue the loop
                }
                    // if there is no adjacent is digit ==> decrease i
                    i --;
                    stack.push(n); // put the result to stack.
            }
            else  {
                //if character is operator ==> pop item from stack and do operation and push the result to stack
                int value1 = stack.pop();
                int value2 = stack.pop();

                switch (c) {
                    case '+' -> stack.push(value2 + value1);
                    case '-' -> stack.push(value2 - value1);
                    case '*' -> stack.push(value2 * value1);
                    case '/' -> stack.push(value2 / value1);
                    case '^' -> stack.push(value2 ^ value1);
                }

            }

        }
        return stack.pop(); // the final result is the last one in stack.
    }
}
