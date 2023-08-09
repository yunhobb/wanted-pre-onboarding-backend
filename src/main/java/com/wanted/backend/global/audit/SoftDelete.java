package com.wanted.backend.global.audit;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.hibernate.annotations.Where;

@Inherited
@Target(TYPE)
@Retention(RUNTIME)
@Where(clause = "deleted_at is null")
public @interface SoftDelete {}
