package it.lisik.maskformatter;

public class MaskingResult {
    private final boolean valid;
    private final String text;

    MaskingResult(final boolean valid, final String text) {
        this.valid = valid;
        this.text = text;
    }

    static MaskingResult failed(final String text) {
        return new MaskingResult(false, text);
    }

    static MaskingResult success(final String text) {
        return new MaskingResult(true, text);
    }

    public String getText() {
        return text;
    }

    public boolean isValid() {

        return valid;
    }
}
