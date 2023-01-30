package org.example;

class Expression {


    final private Operand operand1;
    final private Operand operand2;

    final private OPERAND_TYPE type;

    enum OPERATORS {
        PLUS,
        MINUS,
        MULT,
        DIVIDE
    }
    OPERATORS operator;

    public Expression(String input) throws Exception {
        String[] words = input.split(" ");

        if (words.length != 3) {
            throw new Exception("Input expression should have 3 parts separated with ' ': \"<number1> <operator> <number2>\"");
        }

        operand1 = new Operand(words[0]);
        operand2 = new Operand(words[2]);

        if (operand1.getType() != operand2.getType()) {
            throw new Exception("Both operands should be represented with the same format");
        }
        type = operand1.getType();


        //parse operator:
        if (words[1].length()>1) throw new Exception("Incorrect operator sign");
        switch(words[1].charAt(0)) {
            case '+' ->  operator = OPERATORS.PLUS;
            case '-' ->  operator = OPERATORS.MINUS;
            case '*' ->  operator = OPERATORS.MULT;
            case '/' ->  operator = OPERATORS.DIVIDE;
            default -> throw new Exception("Incorrect operator's sign: " + words[1]);
        }

        if(operator == OPERATORS.MINUS
                && type == OPERAND_TYPE.ROMAN
                && operand1.getValue() < operand2.getValue()) {
            throw new Exception("For Roman numbers it is not allowed to subtract more from less.");
        }


    }

    public String getResult()  {
        int result = calc();
        if(type == OPERAND_TYPE.ARABIC) {
            return String.valueOf(result);
        }

        StringBuilder sb = new StringBuilder();
        switch(result/10) {
            case 1 -> sb.append("X");
            case 2 -> sb.append("XX");
            case 3 -> sb.append("XXX");
            case 4 -> sb.append("XL");
            case 5 -> sb.append("L");
            case 6 -> sb.append("LX");
            case 7 -> sb.append("LXX");
            case 8 -> sb.append("LXXX");
            case 9 -> sb.append("XC");
            case 10 -> sb.append("C");
        }
        switch (result%10) {
            case 0 -> { if (result ==0) { sb.append("N"); } }
            case 1 -> sb.append("I");
            case 2 -> sb.append("II");
            case 3 -> sb.append("III");
            case 4 -> sb.append("IV");
            case 5 -> sb.append("V");
            case 6 -> sb.append("VI");
            case 7 -> sb.append("VII");
            case 8 -> sb.append("VIII");
            case 9 -> sb.append("IX");
        }
        return sb.toString();
    }

    public int calc(){
        int result = 0;
        switch (operator) {
            case PLUS ->  result = operand1.getValue() + operand2.getValue();
            case MINUS -> result = operand1.getValue() - operand2.getValue();
            case MULT ->  result = operand1.getValue() * operand2.getValue();
            case DIVIDE ->result = operand1.getValue() / operand2.getValue();
        }
        return result;
    }

    @Override
    public String toString() {
        return " {" +
                operand1.toString() +
                operator +
                operand2.toString() + '}';

    }
}





