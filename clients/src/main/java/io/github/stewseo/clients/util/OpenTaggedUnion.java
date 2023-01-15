package io.github.stewseo.clients.util;

import javax.annotation.Nullable;

/**
 * A union that is open, i.e. non-exhaustive, where new variants can be defined on the server using extension plugins.
 *
 * @see TaggedUnion
 */
public interface OpenTaggedUnion<Tag extends Enum<?>, BaseType> extends TaggedUnion<Tag, BaseType> {

    /**
     * Get the actual kind when {@code _kind()} equals {@code _Custom} (plugin-defined variant).
     */
    @Nullable
    String _customKind();
}

