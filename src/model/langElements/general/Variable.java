package model.langElements.general;

public class Variable {
    protected String name;
    protected DataType dataType;
    protected Value value;

    public Variable(String name, DataType dataType) {
        this.name = name;
        this.dataType = dataType;
    }

    public Variable(String name, DataType dataType, Value value) {
        this.name = name;
        this.dataType = dataType;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
