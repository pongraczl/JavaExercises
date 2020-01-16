package model.langElements.general;

import model.CodeText;
import model.CodingStyle;

public class Literal implements CodeText {
    protected Value value;

    protected Literal(){}

    public static Literal createInt(int newValue) {
        Literal newLiteral = new Literal();
        newLiteral.value = Value.getInt(newValue);
        return newLiteral;
    }

    public static Literal createString(String newValue) {
        Literal newLiteral = new Literal();
        newLiteral.value = Value.getString(newValue);
        return newLiteral;
    }

    public static Literal createByValue(Value value) {
        Literal newLiteral = new Literal();
        newLiteral.value = value;
        return newLiteral;
    }

    public Value getValue() {
        return value;
    }

    public DataType getDataType() {
        return value.dataType;
    }

    @Override
    public String getCodeText(CodingStyle style) {
        if (value instanceof Value.IntegerValue) {
            return String.valueOf(
                    ((Value.IntegerValue) value).value);
        } else if (value instanceof Value.FloatingPointValue) {
            return String.valueOf(
                    ((Value.FloatingPointValue) value).value);
        } else if (value instanceof Value.StringValue) {
            return "\"" + ((Value.StringValue) value).value + "\"";
        } else if (value instanceof Value.BooleanValue) {
            return String.valueOf(
                    ((Value.BooleanValue) value).value);
        } else if (value instanceof Value.CharValue) {
            return "'" + ((Value.CharValue) value).value + "'";
        } else if (value instanceof Value.NullValue) {
            return "null";
        } else {
            return "unknown";
        }
    }
}
