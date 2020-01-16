package model.langElements.procedural;

import model.CodeText;
import model.CodingStyle;
import model.langElements.general.Value;
import model.langElements.imperative.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MethodCall implements CodeText {
    protected Method calledMethod;
    protected List<Expression> actualParameters = new ArrayList<>();

    public MethodCall(Method calledMethod, List<Expression> actualParameters) {
        this.calledMethod = calledMethod;
        if (actualParameters != null) this.actualParameters = actualParameters;
    }

    public MethodCall(Method calledMethod, Expression... actualParameters) {
        this.calledMethod = calledMethod;
        for (Expression expression : actualParameters) {
            this.actualParameters.add(expression);
        }
    }

    public Method getCalledMethod() {
        return calledMethod;
    }

    public void setCalledMethod(Method calledMethod) {
        this.calledMethod = calledMethod;
    }

    public List<Expression> getActualParameters() {
        return actualParameters;
    }

    @Override
    public String getCodeText(CodingStyle style) {
        return calledMethod.getName()
                + "("
                + actualParameters.stream()
                    .map(expression -> expression.getCodeText(style))
                    .collect(Collectors.joining(", "))
                + ")";
    }
}
