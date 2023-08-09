package com.wanted.backend.global;

public interface EntityLoader<T, ID> {
    T loadEntity(ID id);
}
