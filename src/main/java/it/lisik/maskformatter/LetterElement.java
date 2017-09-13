package it.lisik.maskformatter;

public class LetterElement implements MaskElement {
    @Override
    public Character getSymbol() {
        return 'A';
    }

    @Override
    public boolean validate(final Character character) {
        return Character.isLetter(character);
    }
}
