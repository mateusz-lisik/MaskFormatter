package it.lisik.maskformatter;

public class UppercaseLetterElement extends LetterElement {
    @Override
    public Character getSymbol() {
        return 'U';
    }

    @Override
    public boolean validate(final Character character) {
        return super.validate(character) && Character.isUpperCase(character);
    }
}
