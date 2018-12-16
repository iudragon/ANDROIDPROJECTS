package dragon.bakuman.iu.sqlitecoursedoc.utils;

//All the permissions we need inside here

import android.Manifest;

public class Init {

    public Init(){

    }


    public static final String[] PHONE_PERMISSIONS = {Manifest.permission.CALL_PHONE};
    public static final String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

    public static final int CAMERA_REQUEST_CODE = 5;

}
