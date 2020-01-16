package model.langElements.imperative.statementBuilders;

import model.langElements.general.DataType;
import model.langElements.general.Value;
import model.langElements.general.Variable;
import model.langElements.imperative.Declaration;
import model.langElements.imperative.Expression;
import model.langElements.imperative.Statement;

import java.util.ArrayList;

public class StatementBuilder_Declare {

    public static class StatementBuilder_Declare_Builder
        implements  Stmt_Declare_DataType,
                    Stmt_Declare_VariableNameNeeded,
                    Stmt_Declare_Build {
        private DataType type;
        private ArrayList<Pair> variablesAndInits = new ArrayList<>();

        StatementBuilder_Declare_Builder() {}   //for package visibility (not to be public)

        //Mandatory: DataType -- from: Variable, DataType, init Value
        //Mandatory: name -- from: Variable, String name (case of new Variable)
        //Optional: init Value / init Expression


        @Override
        public Stmt_Declare_VariableNameNeeded dataType(DataType dataType) {
            this.type = dataType;
            return this;
        }

        @Override
        public Stmt_Declare_VariableNameNeeded dataType(Value value) {
            this.type = value.getDataType();
            return this;
        }

        @Override
        public Stmt_Declare_Build addVariable(String name) {
            Variable variable = new Variable(name, type);
            variablesAndInits.add(new Pair(variable, null));
            return this;
        }

        @Override
        public Stmt_Declare_Build addVariable(Variable variable) {
            variablesAndInits.add(new Pair(variable, null));
            return this;
        }

        @Override
        public Stmt_Declare_Build initLastVariable(Value value) {
            variablesAndInits.get(variablesAndInits.size() - 1).initExpression = Expression.make(value);
            return this;
        }

        @Override
        public Stmt_Declare_Build initLastVariable(Expression initExpression) {
            variablesAndInits.get(variablesAndInits.size() - 1).initExpression = initExpression;
            return this;
        }

        @Override
        public Statement.DeclarationStatement build() {
            Declaration declaration = new Declaration(type);
            for (Pair varAndInit : variablesAndInits) {
                declaration.add(varAndInit.variable, varAndInit.initExpression);
            }
            return new Statement.DeclarationStatement(declaration);
        }
    }

    private static class Pair {
            Variable variable;
            Expression initExpression;

            public Pair(Variable variable, Expression initExpression) {
                this.variable = variable;
                this.initExpression = initExpression;
            }
        }

    public interface Stmt_Declare_DataType {
        Stmt_Declare_VariableNameNeeded dataType(DataType dataType);
        Stmt_Declare_VariableNameNeeded dataType(Value value);
    }

    public interface Stmt_Declare_VariableNameNeeded {
        Stmt_Declare_Build addVariable(String name);
        Stmt_Declare_Build addVariable(Variable variable);
    }

    public interface Stmt_Declare_Build {
        Stmt_Declare_Build addVariable(String name);
        Stmt_Declare_Build addVariable(Variable variable);
        Stmt_Declare_Build initLastVariable(Value value);
        Stmt_Declare_Build initLastVariable(Expression initExpression);
        Statement.DeclarationStatement build();
    }
}



