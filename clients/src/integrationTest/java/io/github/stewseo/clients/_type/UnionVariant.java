package io.github.stewseo.clients._type;

/**
 * An implementation of a variant type.
 */
public interface UnionVariant<Tag> {

    /**
     * Get the type of this object when used as a variant
     */
    Tag _variantType();
}