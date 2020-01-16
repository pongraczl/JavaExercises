package model.langElements.procedural;

import model.CodeText;
import model.CodingStyle;
import model.langElements.general.DataType;

import java.util.ArrayList;
import java.util.List;

public class FormalParameter implements CodeText {
    protected DataType type;
    protected String name;
    protected boolean isFinal = false;

    public FormalParameter(DataType type, String name) {
        this.type = type;
        this.name = name;
    }

    public static List<FormalParameter> create(DataType type, String name) {
        return create(new FormalParameter(type, name));
    }

    public static List<FormalParameter> create(FormalParameter... parameters) {
        List<FormalParameter> list = new ArrayList<>();
        for (FormalParameter param : parameters) {
            list.add(param);
        }
        return list;
    }


    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    @Override
    public String getCodeText(CodingStyle style) {
        return (isFinal ? "final " : "")
                + type.getCodeText(style) + " "
                + name;
    }
}
