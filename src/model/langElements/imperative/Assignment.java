package model.langElements.imperative;

import model.CodeText;
import model.CodingStyle;
import model.langElements.general.Value;
import model.langElements.general.Variable;

public class Assignment extends Expression implements CodeText {
    protected Expression leftHandSide;
    protected Operator.BinaryOperator assignmentOperator;
    protected Expression rightHandSide;

    public static Operator.AssignmentOperator DEFAULT_ASSIGNMENT_OPERATOR = Operator.ASSIGN;

    public Assignment(Expression leftHandSide, Expression rightHandSide) {
        this(leftHandSide, DEFAULT_ASSIGNMENT_OPERATOR, rightHandSide);
    }

    public Assignment(Expression leftHandSide, Operator.BinaryOperator assignmentOperator, Expression rightHandSide) {
        this.leftHandSide = leftHandSide;
        this.assignmentOperator = assignmentOperator;
        this.rightHandSide = rightHandSide;
    }

    public Assignment(Variable leftVariable, Expression rightHandSide) {
        this(Expression.make(leftVariable), rightHandSide);
    }

    @Override
    public Value evaluate() {
        return rightHandSide.evaluate();
    }

    @Override
    public String getCodeText(CodingStyle style) {
        return leftHandSide.getCodeText(style)
                + " " + assignmentOperator.getSign() + " "
                + rightHandSide.getCodeText(style);
    }
}
