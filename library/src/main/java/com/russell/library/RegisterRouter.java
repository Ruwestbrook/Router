package com.russell.library;

import android.app.Activity;

import java.util.Map;

/**
 * @author russell
 * @description:
 * @date : 2021/5/5 20:00
 */
public interface RegisterRouter {

    void load(Map<String, Class<? extends Activity>> routers);
}
