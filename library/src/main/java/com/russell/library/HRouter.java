package com.russell.library;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class HRouter {

    private static final String TAG = "'HRouter'";

    @SuppressLint("StaticFieldLeak")
    private static final HRouter hRouter=new HRouter();

    private static Context mContext;

    private static Map<String, Class<? extends Activity>> routers= new HashMap<>();

    private HRouter(){

    }

    public static HRouter getInstance(){
        return hRouter;
    }

    public static void init(Application routerApplication) {
        Log.d(TAG, "HRouter: ");
        mContext=routerApplication;
        try {

            Class<?> aClass=Class.forName("com.russell.router.AppRegister");

            if(RegisterRouter.class.isAssignableFrom(aClass)){
                RegisterRouter registerRouter= (RegisterRouter) aClass.newInstance();
                registerRouter.load(routers);
            }

            Class<?> bClass=Class.forName("com.russell.android.common.CommonRegister");

            if(RegisterRouter.class.isAssignableFrom(bClass)){
                RegisterRouter registerRouter= (RegisterRouter) bClass.newInstance();
                registerRouter.load(routers);
            }
            Log.d(TAG, "HRouter: size="+routers.size());
        }catch (Exception e){

            Log.d(TAG, "HRouter: "+e.toString());

        }
    }


    public void build(String path){

    }

    public void startActivity(String s) {
        if(routers.containsKey(s)){
            Intent intent=new Intent(mContext,routers.get(s));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        }

    }
}
