package model.exerciseElements;

import model.langElements.general.DataType;

public class ExerciseParameter {
    private String id;
    private DataType dataType;
    private Role role;
    private String description;

    public ExerciseParameter(String id, DataType dataType, Role role) {
        this.id = id;
        this.dataType = dataType;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public DataType getDataType() {
        return dataType;
    }

    public Role getRole() {
        return role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public enum Role {
        ESSENTIAL, STRUCTURAL, NAME, OTHER
    }
}
