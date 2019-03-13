package com.example.clsalgado.tccws.component;

import com.example.clsalgado.tccws.EmpresaActivity;
import com.example.clsalgado.tccws.MainActivity;
import com.example.clsalgado.tccws.module.TccModule;

import dagger.Component;

@Component(modules = TccModule.class)
public interface TccComponent {

    void inject(MainActivity activity);

    void inject(EmpresaActivity empresaActivity);
}
