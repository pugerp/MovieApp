package com.pugerp.movieapp.di.annotations;

import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

@Qualifier
@Retention(CLASS)
public @interface AppContext {
}
