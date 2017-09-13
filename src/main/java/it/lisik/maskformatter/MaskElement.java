package it.lisik.maskformatter;

public interface MaskElement {
    default boolean isLiteral() {
        return false;
    }

    Character getSymbol();
    boolean validate(Character character);
}
