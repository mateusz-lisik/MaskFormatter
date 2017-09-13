package it.lisik.maskformatter;

public class LowercaseLetterElement extends LetterElement {
    @Override
    public Character getSymbol() {
        return 'L';
    }

    @Override
    public boolean validate(final Character character) {
        return super.validate(character) && Character.isLowerCase(character);
    }
}
