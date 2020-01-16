package model.langElements.imperative;

import model.CodeText;
import model.CodingStyle;
import model.langElements.general.DataType;
import model.langElements.general.Value;
import model.langElements.general.Variable;

import java.util.*;
import java.util.stream.Collectors;

public class Declaration implements CodeText {
    protected DataType dataType;
    protected Map<Variable, Expression> variableInitializers = new LinkedHashMap<>();

    protected Declaration(){}

    public Declaration(DataType dataType) {
        this.dataType = dataType;
    }

    public static Declaration getNew(Variable variable) {
        Declaration declaration = new Declaration();
        declaration.dataType = variable.getDataType();
        declaration.variableInitializers.put(variable, null);
        return declaration;
    }

    public static Declaration getNew(Variable variable, Expression initExpression) {
        Declaration declaration = getNew(variable);
        declaration.variableInitializers.put(variable, initExpression);
        return declaration;
    }

    public Declaration add(Variable variable) {
        variableInitializers.put(variable, null);
        return this;
    }

    public Declaration add(Variable variable, Expression initExpression) {
        variableInitializers.put(variable, initExpression);
        return this;
    }

    public DataType getDataType() {
        return dataType;
    }

    public Map<Variable, Expression> getVariableInitializers() {
        return variableInitializers;
    }

    public Variable getFirstVariable() {
        for (Variable variable : variableInitializers.keySet()) {
            return variable;
        }
        return null;
    }

    public Value getFirstVariableInitValue() {
        for (Expression expression : variableInitializers.values()) {
            if (expression instanceof Assignment) {
                return ((Assignment)expression).evaluate();
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public String getCodeText(CodingStyle style) {
        String code = dataType.getCodeText(style) + " ";
        //variables.stream().map(v -> variableInitializers)
        code += variableInitializers.entrySet().stream()
                .map( entry -> (entry.getKey().getName()
                                + (entry.getValue() == null ? "" :
                                    (" = " + entry.getValue().getCodeText(style)))
                ))
                .collect(Collectors.joining(", "));
        return code;
    }
}
