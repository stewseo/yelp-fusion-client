package io.github.stewseo.clients.util;

/**
 * Base interface for tagged union types (also known as sum types or variants).
 * <p>
 * It provides access to the current variant kind and its value.
 *
 * @param <Tag> the tag type that defines the possible variants (an enum).
 * @param <BaseType> the closest common ancestor type to all variant values.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Tagged_union">Tagged Union on Wikipedia</a>
 */
public interface TaggedUnion<Tag extends Enum<?>, BaseType> {

    /**
     * Get the of the kind of variant held by this object.
     *
     * @return the variant kind
     */
    Tag _kind();

    BaseType _get();
}