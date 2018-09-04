package ru.innopolis.stc12.classloaders2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HumanInvoker implements InvocationHandler {

    static final String CLASS_NAME_FOR_CHECK = "ru.innopolis.stc12.classloaders2.EuropeanHuman";
    // fix path for build (file must exists)
    static final String CLASS_NAME_FOR_LOAD = "file:/tmp/EuropeanHuman.class";

    HumanInvoker() {
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        HumanLoader humanLoader = new HumanLoader(proxy.getClass().getClassLoader());
        Class humanClass = humanLoader.loadClass(CLASS_NAME_FOR_CHECK);
        Method newMethod = humanClass.getMethod(method.getName());
        return newMethod.invoke(humanClass.newInstance(), args);
    }
}
