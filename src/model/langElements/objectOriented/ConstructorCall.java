package model.langElements.objectOriented;

import model.CodingStyle;
import model.langElements.general.Value;
import model.langElements.imperative.Expression;
import model.langElements.procedural.Method;
import model.langElements.procedural.MethodCall;

import java.util.List;

public class ConstructorCall extends MethodCall {
    public ConstructorCall(Constructor calledConstructor, List<Expression> actualParameters) {
        super(calledConstructor, actualParameters);
    }

    public ConstructorCall(Constructor calledConstructor, Expression... actualParameters) {
        super(calledConstructor, actualParameters);
    }

    @Override
    public String getCodeText(CodingStyle style) {
        return "new " + super.getCodeText(style);
    }
}
