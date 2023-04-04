package com.jx.androiddemo.di.module;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.jx.androiddemo.di.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule
{

    private Fragment fragment;

    public FragmentModule(Fragment fragment)
    {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity()
    {
        return fragment.getActivity();
    }
}
