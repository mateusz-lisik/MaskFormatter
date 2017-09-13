package it.lisik.maskformatter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Mask {
    private final List<MaskElement> elements;

    public Mask(final List<MaskElement> elements) {
        this.elements = elements;
    }

    public Optional<MaskElement> getElement(int position) {
        try {
            return Optional.of(elements.get(position));
        } catch (Exception e) {
            return Optional.empty();
        }
    }


    /**
     * @return Unmodifiable list of elements
     */
    public List<MaskElement> getElements() {
        return Collections.unmodifiableList(elements);
    }

    public int getLength() {
        return elements.size();
    }

    public int getLiteralsCount() {
        return (int) elements.stream()
                .filter(MaskElement::isLiteral)
                .count();
    }
}
