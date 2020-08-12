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

    private boolean checket = false;
    private FileDescriptor instancia = null;
    private FileInputStream mFileInputStream = null;
    private FileOutputStream mFileOutputStream = null;

    public managerPort(String path,int baudrate) {

            instancia = open(path,baudrate,0);
            if (instancia!=null){
                mFileInputStream = new FileInputStream(instancia);
                mFileOutputStream = new FileOutputStream(instancia);
                checket = true;
            }else{
                checket = false;
            }




    }


    public boolean isChecket() {
        return checket;
    }

    public void setChecket(boolean checket) {
        this.checket = checket;
    }

    public FileInputStream getmFileInputStream() {
        return mFileInputStream;
    }

    public FileOutputStream getmFileOutputStream() {
        return mFileOutputStream;
    }

}
