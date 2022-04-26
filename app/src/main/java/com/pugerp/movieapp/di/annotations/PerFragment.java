package com.pugerp.movieapp.di.annotations;

import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;

import javax.inject.Scope;

@Scope
@Retention(CLASS)
public @interface PerFragment {
}
