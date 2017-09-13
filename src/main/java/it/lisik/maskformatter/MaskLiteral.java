package it.lisik.maskformatter;

public class MaskLiteral implements MaskElement {
    private final Character character;

    public MaskLiteral(final Character character) {
        this.character = character;
    }

    public Character getCharacter() {
        return character;
    }

    @Override
    public boolean isLiteral() {
        return true;
    }

    @Override
    public Character getSymbol() {
        return getSymbol();
    }

    @Override
    public boolean validate(final Character character) {
        return this.character.equals(character);
    }
}
