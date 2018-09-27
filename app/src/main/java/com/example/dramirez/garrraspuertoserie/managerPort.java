package com.example.dramirez.garrraspuertoserie;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class managerPort {
    public native static FileDescriptor open(String path, int baudrate, int flags);
    public native void close();
    static {
        System.loadLibrary("native-lib");
    }

    private FileDescriptor instancia = null;
    private FileInputStream mFileInputStream = null;
    private FileOutputStream mFileOutputStream = null;

    public managerPort(String path,int baudrate) {
        instancia = open(path,baudrate,0);
        mFileInputStream = new FileInputStream(instancia);
        mFileOutputStream = new FileOutputStream(instancia);
    }

    public FileInputStream getmFileInputStream() {
        return mFileInputStream;
    }

    public FileOutputStream getmFileOutputStream() {
        return mFileOutputStream;
    }
}
