package it.lisik.maskformatter;

public class NumberElement implements MaskElement {
    @Override
    public Character getSymbol() {
        return '#';
    }

    @Override
    public boolean validate(final Character character) {
        return Character.isDigit(character);
    }
}
