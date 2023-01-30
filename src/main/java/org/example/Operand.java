package org.example;

enum OPERAND_TYPE {
    ARABIC,
    ROMAN
}

class Operand {
    int value;
    OPERAND_TYPE type;

    public Operand(String input) throws Exception {
        if (input.matches("[1-9]\\d*")) {
            value = Integer.parseInt(input);
            if (value > 10) {
                throw new Exception("input values should be less than or equal to 10. Here it is " + value);
            }
            type = OPERAND_TYPE.ARABIC;
        }
        else {
            value = parseRoman(input);
            type = OPERAND_TYPE.ROMAN;
        }
    }

    private int parseRoman(String input) throws Exception {

        enum ROMAN_NUMBER {
            NULL, I, II, III, IV, V, VI, VII, VIII, IX, X,
            NONE
        }

        ROMAN_NUMBER number = ROMAN_NUMBER.NONE;

        boolean completionFlag = false;

        if (input.length() > 4) {
            throwIllegalSyntaxException(input);
        }

        for (int i = 0; i < input.length(); i++) {
            if (completionFlag) {
                throwIllegalSyntaxException(input);
            }
            char ch = input.charAt(i);
            switch (ch) {
                case 'I' -> {
                    switch (number) {
                        case NONE -> number = ROMAN_NUMBER.I;
                        case I -> number = ROMAN_NUMBER.II;
                        case II -> {
                            number = ROMAN_NUMBER.III;
                            completionFlag = true;
                        }
                        case V -> number = ROMAN_NUMBER.VI;
                        case VI -> number = ROMAN_NUMBER.VII;
                        case VII -> {
                            number = ROMAN_NUMBER.VIII;
                            completionFlag = true;
                        }
                        default -> throwIllegalSyntaxException(input);
                    }
                }
                case 'V' -> {
                    switch (number) {
                        case NONE -> number = ROMAN_NUMBER.V;
                        case I -> {
                            number = ROMAN_NUMBER.IV;
                            completionFlag = true;
                        }
                        default -> throwIllegalSyntaxException(input);
                    }
                }
                case 'X' -> {
                    switch (number) {
                        case NONE -> number = ROMAN_NUMBER.X;
                        case I -> {
                            number = ROMAN_NUMBER.IX;
                            completionFlag = true;
                        }
                        default -> throwIllegalSyntaxException(input);
                    }
                }
                default ->throwIllegalSyntaxException(input);
            }
        }

        return number.ordinal();
    }

    private void throwIllegalSyntaxException(String s) throws Exception {
        throw new Exception("Illegal syntax or value in a number's expression \"" + s + "\"");
    }

    public int getValue() {
        return value;
    }

    public OPERAND_TYPE getType() {
        return type;
    }



    @Override
    public String toString() {
        return " ( " +
                value + ", " +
                type +
                " ) ";
    }
}