package model.langElements.general;

import model.langElements.objectOriented.ObjectElement;

import java.util.ArrayList;

public abstract class Value {
    protected DataType dataType;

    public static VoidValue getVoid() { return new VoidValue(); }

    public static IntegerValue getInt(int value) {
        IntegerValue newValue = new IntegerValue(value);
        return newValue;
    }

    public static StringValue getString(String value) {
        StringValue newValue = new StringValue(value);
        return newValue;
    }

    public static BooleanValue getBool(boolean value) {
        BooleanValue newValue = new BooleanValue(value);
        newValue.dataType = DataType.BOOLEAN;
        return newValue;
    }

    //TODO: other values


    public static class VoidValue extends Value {
        {
            dataType = DataType.VOID;
        }
    }
    public static abstract class PrimitiveValue extends Value {}
    public static abstract class NumericValue extends PrimitiveValue {}
    public static abstract class NonPrimitiveValue extends Value {}

    public static class IntegerValue extends NumericValue {
        public long value;
        private IntegerValue (long value) {
            this.value = value;
            dataType = DataType.INT;
        }
    }
    public static class FloatingPointValue extends NumericValue {
        public double value;
        private FloatingPointValue (double value) {
            this.value = value;
            dataType = DataType.FLOAT;
        }
    }
    public static class CharValue extends PrimitiveValue {
        public char value;
        private CharValue (char value) {
            this.value = value;
            dataType = DataType.CHAR;
        }
    }
    public static class BooleanValue extends PrimitiveValue {
        public boolean value;
        private BooleanValue (boolean value) {
            this.value = value;
            dataType = DataType.FLOAT;
        }
    }
    public static class StringValue extends NonPrimitiveValue {
        public String value;
        private StringValue (String value) {
            this.value = value;
            dataType = DataType.STRING;
        }
    }
    public static class ObjectValue extends NonPrimitiveValue {
        public ObjectElement value;
        private ObjectValue (ObjectElement value) {
            this.value = value;
            dataType = DataType.makeObjectType(value);
        }
    }
    public static class ArrayValue extends NonPrimitiveValue {
        public ArrayList<Value> value;
        private ArrayValue (ArrayList<Value> values) {
            this.value = values;
            dataType = DataType.makeArrayType(
                    (values==null || values.isEmpty())
                    ? DataType.NULL
                    : value.get(0).dataType
            );
        }
    }
    public static class NullValue extends NonPrimitiveValue {
        private NullValue () {
            dataType = DataType.NULL;
        }
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }
}
