package ru.innopolis.stc12.classloaders2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

public class HumanLoader extends ClassLoader {

    HumanLoader(ClassLoader parent) {
        super(parent);
    } // make IDE happy

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.equals(HumanInvoker.CLASS_NAME_FOR_CHECK)) {
            byte[] classData = null;
            try {
                classData = getClassDataInBytes(new URL(HumanInvoker.CLASS_NAME_FOR_LOAD));
            } catch (IOException e) {
                System.err.println(e.getMessage()); // make IDE happy
            }
            return defineClass(name, classData, 0, Objects.requireNonNull(classData).length);
        }

        return super.loadClass(name);
    }

    private byte[] getClassDataInBytes(URL url) throws IOException {
        InputStream inputStream = url.openConnection().getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int data = inputStream.read();
        while (data != -1) {
            byteArrayOutputStream.write(data);
            data = inputStream.read();
        }
        inputStream.close();

        return byteArrayOutputStream.toByteArray();
    }
}
