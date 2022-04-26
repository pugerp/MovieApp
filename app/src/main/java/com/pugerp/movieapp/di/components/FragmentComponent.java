package com.pugerp.movieapp.di.components;

import com.pugerp.movieapp.di.annotations.PerFragment;
import com.pugerp.movieapp.di.modules.FragmentModule;

import dagger.Component;

@Component(dependencies = ActivityComponent.class, modules = FragmentModule.class)
@PerFragment
public interface FragmentComponent {
}
