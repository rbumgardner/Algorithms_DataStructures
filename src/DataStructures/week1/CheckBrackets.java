package DataStructures.week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class CheckBrackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();

        if (text.length() < 2) {
            System.out.println("1");
            return;
        }

        Stack<Bracket> openingBracketStack = new Stack<Bracket>();
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                openingBracketStack.push(new Bracket(next, position));
            }

            if (next == ')' || next == ']' || next == '}') {
                if (!openingBracketStack.empty()) {
                    Bracket topBracket = openingBracketStack.peek();
                    if (topBracket.match(next)) {
                        openingBracketStack.pop();
                    } else {
                        System.out.println(position + 1);
                        return;
                    }
                } else {
                    System.out.println(position + 1);
                    return;
                }
            }
        }

        if (!openingBracketStack.empty()) {
            System.out.println(openingBracketStack.peek().position + 1);
        } else {
            System.out.println("Success");
        }
    }

    static class Bracket {
        Bracket(char type, int position) {
            this.type = type;
            this.position = position;
        }

        boolean match(char c) {
            if (this.type == '[' && c == ']')
                return true;
            if (this.type == '{' && c == '}')
                return true;
            if (this.type == '(' && c == ')')
                return true;
            return false;
        }

        char type;
        int position;
    }
}
