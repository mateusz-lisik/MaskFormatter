package it.lisik.maskformatter;

import static it.lisik.maskformatter.MaskingResult.failed;
import static it.lisik.maskformatter.MaskingResult.success;

public class MaskFormatter {
    private final Mask mask;
    private final Character placeholderCharacter;
    private final boolean autoPlaceLiterals;

    public MaskFormatter(final Mask mask, final Character placeholderCharacter, final boolean autoPlaceLiterals) {
        this.mask = mask;
        this.placeholderCharacter = placeholderCharacter;
        this.autoPlaceLiterals = autoPlaceLiterals;
    }

    public MaskingResult process(String input) {
        if (input == null || input.isEmpty()) {
            return failed(getPlaceholderMask());
        }

        if (input.length() > mask.getLength()) {
            input = input.substring(0, mask.getLength());
        }

        final String output = buildMaskedOutput(input);
        if (output.length() != getPlaceholderMask().length()) {
            return failed(output + getPlaceholderMask().substring(output.length(), getPlaceholderMask().length()));
        }

        return success(output);
    }

    public String getPlaceholderMask() {
        final StringBuilder builder = new StringBuilder(mask.getElements().size());

        for (MaskElement element : mask.getElements()) {
            if (element.isLiteral()) {
                builder.append(((MaskLiteral) element).getCharacter());
            } else {
                builder.append(placeholderCharacter);
            }
        }

        return builder.toString();
    }

    private String buildMaskedOutput(final String input) {
        final StringBuilder builder = new StringBuilder(mask.getLength());
        int missedLiterals = 0;

        final int inputLength = calculateTotalInputLength(input);
        for (int i = 0; i < inputLength; i++) {
            if (input.length() + missedLiterals <= i) break;
            if (!mask.getElement(i).isPresent()) break;

            final MaskElement maskElement = mask.getElement(i).get();
            final char character = input.charAt(i - missedLiterals);

            final boolean characterValid = maskElement.validate(character);

            if (maskElement.isLiteral() && !characterValid && autoPlaceLiterals) {
                builder.append(((MaskLiteral) maskElement).getCharacter());
                missedLiterals++;
                continue;
            }

            if (!characterValid) {
                break;
            }

            builder.append(character);
        }
        return builder.toString();
    }

    private int calculateTotalInputLength(final String input) {
        return input.length() + mask.getLiteralsCount();
    }
}
