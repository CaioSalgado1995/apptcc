package com.example.clsalgado.tccws.app;

import android.app.Application;

import com.example.clsalgado.tccws.component.DaggerTccComponent;
import com.example.clsalgado.tccws.component.TccComponent;

public class TccApplication extends Application {

    private TccComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerTccComponent.builder().build();
    }

    public TccComponent getComponent() {
        return component;
    }
}
