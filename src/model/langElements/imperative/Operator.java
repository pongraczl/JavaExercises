package model.langElements.imperative;

public abstract class Operator {
    public static final UnaryOperator POST_INCREMENT = new UnaryOperator("++", false);
    public static final UnaryOperator POST_DECREMENT = new UnaryOperator("--", false);
    public static final UnaryOperator PRE_INCREMENT = new UnaryOperator("++", true);
    public static final UnaryOperator PRE_DECREMENT = new UnaryOperator("--", true);

    public static final UnaryOperator UNARY_MINUS = new UnaryOperator("-", true);
    public static final UnaryOperator UNARY_PLUS = new UnaryOperator("+", true);

    public static final BinaryOperator ADDITION = new BinaryOperator("+");
    public static final BinaryOperator SUBSTRACTION = new BinaryOperator("-");
    public static final BinaryOperator MULTIPLICATION = new BinaryOperator("*");
    public static final BinaryOperator DIVISION = new BinaryOperator("/");
    public static final BinaryOperator MODULUS = new BinaryOperator("%");

    public static final BinaryOperator INSTANCEOF = new BinaryOperator("instanceof");

    public static final AssignmentOperator ASSIGN = new AssignmentOperator("=" , null);
    public static final AssignmentOperator ADD_AND_ASSIGN = new AssignmentOperator("+=" , ADDITION);

    public static final TernaryOperator CONDITIONAL = new TernaryOperator("?", ":");

    protected String sign;

    private Operator(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

    public static class UnaryOperator extends Operator {
        protected boolean isPrefix; //postfix anyway

        public UnaryOperator(String sign, boolean isPrefix) {
            super(sign);
            this.isPrefix = isPrefix;
        }

        public boolean isPrefix() {
            return isPrefix;
        }
    }

    public static class BinaryOperator extends Operator {
        public BinaryOperator(String sign) {
            super(sign);
        }
    }

    public static class AssignmentOperator extends BinaryOperator {
        protected BinaryOperator relatedBinaryOperator;

        public AssignmentOperator(String sign, BinaryOperator relatedBinaryOperator) {
            super(sign);
            this.relatedBinaryOperator = relatedBinaryOperator;
        }

        public BinaryOperator getRelatedBinaryOperator() {
            return relatedBinaryOperator;
        }
    }

    public static class TernaryOperator extends Operator {
        protected String secondSign;

        public TernaryOperator(String sign, String secondSign) {
            super(sign);
            this.secondSign = secondSign;
        }

        public String getSecondSign() {
            return secondSign;
        }
    }
}
