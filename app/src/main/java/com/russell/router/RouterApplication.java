package com.russell.router;

import android.app.Application;

import com.russell.library.HRouter;

/**
 * @author russell
 * @description:
 * @date : 2021/5/5 20:24
 */
public class RouterApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        HRouter.init(this);
    }
}
