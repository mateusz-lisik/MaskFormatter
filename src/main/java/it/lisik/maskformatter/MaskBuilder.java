package it.lisik.maskformatter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MaskBuilder {
    private final Set<MaskElement> elements = new LinkedHashSet<>();

    private MaskBuilder() {
        elements.add(new NumberElement());
        elements.add(new LetterElement());
        elements.add(new LowercaseLetterElement());
        elements.add(new UppercaseLetterElement());
    }

    public static Mask build(final String pattern) {
        final MaskBuilder builder = new MaskBuilder();

        final List<MaskElement> elements = pattern.chars()
                .mapToObj(c -> builder.getElement((char) c))
                .collect(Collectors.toList());

        return new Mask(elements);
    }

    private MaskElement getElement(char c) {
        for (MaskElement element : elements) {
            if (!element.getSymbol().equals(c)) continue;
            return element;
        }

        return new MaskLiteral(c);
    }
}
