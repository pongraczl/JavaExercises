package model.langElements.general;

import model.CodeText;
import model.CodingStyle;
import model.langElements.objectOriented.ClassElement;
import model.langElements.objectOriented.ObjectElement;

public class DataType implements CodeText {
    public enum DataTypeCategory { SIMPLE, ARRAY, OBJECT, VOID}

    public static final DataType BOOLEAN = new DataType("boolean");
    public static final DataType BYTE = new DataType("byte");
    public static final DataType SHORT = new DataType("short");
    public static final DataType INT = new DataType("int");
    public static final DataType LONG = new DataType("long");
    public static final DataType FLOAT = new DataType("float");
    public static final DataType DOUBLE = new DataType("double");
    public static final DataType CHAR = new DataType("char");
    public static final DataType STRING = new DataType("String");
    public static final DataType NULL = makeNullType();
    public static final DataType VOID = makeVoidType();

    private DataTypeCategory category = DataTypeCategory.SIMPLE;
    private String keyword;
    private DataType elementType = null;
    private ClassElement connectedClass = null;

    private DataType(String keyword) {
        this.keyword = keyword;
    }

    private static DataType makeVoidType() {
        DataType voidType = new DataType("void");
        voidType.category = DataTypeCategory.VOID;
        return voidType;
    }

    private static DataType makeNullType() {
        DataType nullType = new DataType("null");
        nullType.category = DataTypeCategory.OBJECT;
        return nullType;
    }

    public static DataType makeObjectType(ClassElement connectedClass) {
        DataType newDataType = new DataType("");
        newDataType.category = DataTypeCategory.OBJECT;
        newDataType.connectedClass = connectedClass;
        return newDataType;
    }

    public static DataType makeObjectType(ObjectElement objectOfTheClass) {
        DataType newDataType = new DataType("");
        newDataType.category = DataTypeCategory.OBJECT;
        newDataType.connectedClass = objectOfTheClass.getOwnClass();
        return newDataType;
    }

    public static DataType makeArrayType(DataType elementType) {
        DataType newDataType = new DataType("");
        newDataType.category = DataTypeCategory.ARRAY;
        newDataType.elementType = elementType;
        return newDataType;
    }

    public DataTypeCategory getCategory() {
        return category;
    }
    public DataType getElementType() {
        return elementType;
    }
    public ClassElement getConnectedClass() {
        return connectedClass;
    }


    @Override
    public String getCodeText(CodingStyle style) {
        switch (category) {
            case VOID:
            case SIMPLE: return keyword;
            case OBJECT: return connectedClass.getName();
            case ARRAY: return elementType.getCodeText(style) + "[]";
            default: return "";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DataType) {
            DataType objDT = (DataType) obj;
            switch (objDT.category) {
                case SIMPLE:
                case VOID:
                    return objDT == this;
                case OBJECT:
                    return objDT.connectedClass.equals(this.connectedClass);
                case ARRAY:
                    return objDT.elementType.equals(this.elementType);
                default:
                    return false;
            }
        } else {
            return false;
        }
    }
}
