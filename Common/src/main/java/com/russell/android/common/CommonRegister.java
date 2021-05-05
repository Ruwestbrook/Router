package com.russell.android.common;

import android.app.Activity;

import com.russell.library.RegisterRouter;

import java.util.Map;

/**
 * @author russell
 * @description:
 * @date : 2021/5/5 20:13
 */
public class CommonRegister implements RegisterRouter {
    @Override
    public void load(Map<String, Class<? extends Activity>> routers) {
        routers.put("main/common",CommonActivity.class);
    }
}
