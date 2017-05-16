package com.wileymab.grease;

import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;

import static com.wileymab.grease.Logging.debug;
import static com.wileymab.grease.Logging.tag;

/**
 * Created by m441527 on 2/3/17.
 */

public class ContextKeeper {

    private static ContextKeeper instance;

    public static ContextKeeper setContext(Application application) {
        if ( instance == null ) {
            instance = new ContextKeeper(application);
        }
        return instance;
    }

    public static void releaseContextAndClose() {
        instance.releaseContext();
        instance = null;
    }

    public static ContextKeeper get() {
        return instance;
    }

    private Context applicationContext;

    private void releaseContext() {
        debug(tag(getClass()), "Releasing local application context reference.");
    }

    private ContextKeeper(Application application) {
        debug(tag(getClass()), "Creating new ContextKeeper instance.");
        applicationContext = application.getApplicationContext();
    }

    public WeakReference<Context> getApplicationContext(){
        return new WeakReference<>(applicationContext);
    }

}

