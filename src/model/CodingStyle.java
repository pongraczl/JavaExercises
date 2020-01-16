package model;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CodingStyle {
    public static final String ONE_INDENTATION = "    ";
    public final String currentIndentation;
    public final boolean openingCurlyBraceOnNewLine = false;
    public final boolean isClassnamesFirstCharacterUpperCase = true;
    public final boolean isVariablesFirstCharacterUpperCase = false;
    public final boolean isMethodsFirstCharacterUpperCase = false;
    public final boolean isSpaceAfterMethodName = false;
    public final boolean isSpaceBetweenParameters = true;

    public CodingStyle () {
        currentIndentation = "";
    }

    public CodingStyle(CodingStyle baseStyle, boolean addIndentation) {
        currentIndentation = (baseStyle == null ? "" : baseStyle.currentIndentation)
                + (addIndentation ? ONE_INDENTATION : "");

    }
}