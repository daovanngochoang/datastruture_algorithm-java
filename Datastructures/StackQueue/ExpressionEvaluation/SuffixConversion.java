package Datastructures.StackQueue.ExpressionEvaluation;


import java.util.Stack;

public class SuffixConversion {
    /*

    postfix or suffix expression conversion.
    - bring the normal expression that readable for human and convert it to the postfix
        so we can read and tell the computer evaluate the result, because the operator has
        different precedence, we can not use the normal way to express it.

      for example : "2+5*3+6*(5+8+10+16)"
      we can bring them to : 2  5 + 3  6 +*  5  8 + 10 + 16 +.

      the idea here is :
      - we have a stack to store operators.
      - an output string to store expression.
      run a loop into the expression:
        get the charAt the index.
        if it is an operator:
            check that operator
            - if it's precedence <= the precedence of the top of the operator stack
                => add all the operators that is satisfied with the condition to output.
                and push that operator to the stack.
            - if that operator is '(', push to stack.
            - if that operator is ')', pop all operator until meet '('.
        if it is a number:
            - run a while loop to check the adjacent, if they are all number ==> add to result.

        run a loop to add all operator in stack at the end.


     */


    public boolean isNumber (char c){return (c >= '0' && c <='9');}

    public int checkPrecedence (char operator){ // check precedence.
        if (operator == '+' || operator == '-')return 1;
        else if (operator == '*' || operator == '/') return 2;
        else if (operator == '^') return 3;
        return 0;
    }

    public String SuffixConvert(String express){

        Stack<Character> Operators = new Stack<>();
        String result = "";
        char character;

        for (int i = 0; i < express.length(); i++){ // run a loop to the expression.

            character = express.charAt(i); // get each char in the expression.
            if (checkPrecedence(character) > 0){ // it it is an operator
                // pop and add to result all operator that satisfied the condition of the priority of the current operator is
                // smaller than the priority of the top operator in stack.

                while (!(Operators.isEmpty()) && checkPrecedence(Operators.peek()) >= checkPrecedence(character)){
                    result += (Operators.pop() + " ");
                }
                Operators.push(character); // add that operator to stack.

            }else if (character == ')'){
                // if the character is ')' ==> pop and add all operators in stack until
                // it meet ')' bracket.
                char temp = Operators.pop(); // pop first to compare

                while (temp != '('){ // run loop to pop operator until meet '('
                    result += (temp + " ");
                    temp = Operators.pop(); // pop the last that mean pop the '(' out and remove.
                }
            }else if (character == '('){
                Operators.push(character); // it this bracket it is push to stack as normal
            }
            else { // check and get all 1 digit or multi digits number.
                while (isNumber(character)){ // if that character is a number ==> check all the next character from that char to the end.
                    try { // add that character to result.
                        result+=character;
                        character = express.charAt(++i); // check the next item
                    }catch (Exception e){
                        result+=Operators.pop();
                        break;
                    }
                }
                i--;
            }
            result += (" "); // add space in each time.
        }
        // pop and add all the rest of operators in stack.
        for (int stack_op = 0; stack_op < Operators.size(); stack_op++) result += (Operators.pop() + " ");
        return result;
    }


    public static void main (String[] args){
        SuffixConversion test = new SuffixConversion();
        EvaluateExpression evaluateExpression = new EvaluateExpression();
        String exp = test.SuffixConvert("2+5*3+6*(5+8+10+16)");
        int result = evaluateExpression.Evaluate_value(exp);

        System.out.println(result);
    }

}
