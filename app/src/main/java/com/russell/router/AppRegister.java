package com.russell.router;

import android.app.Activity;

import com.russell.library.RegisterRouter;

import java.util.Map;

/**
 * @author russell
 * @description:
 * @date : 2021/5/5 20:13
 */
public class AppRegister implements RegisterRouter {
    @Override
    public void load(Map<String, Class<? extends Activity>> routers) {
        routers.put("main/home",MainActivity.class);
        routers.put("main/second",SecondActivity.class);
    }
}
