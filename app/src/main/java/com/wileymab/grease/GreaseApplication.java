package com.wileymab.grease;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import static com.wileymab.grease.Logging.debug;
import static com.wileymab.grease.Logging.tag;

/**
 * Created by m441527 on 5/16/17.
 */

public class GreaseApplication extends Application {

    private int activityCount = 0;

    private Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityStarted(Activity activity) {
            debug(tag(this), "Incrementing activity count: %s", activity.getClass().getSimpleName() );
            activityCount++;
            if ( activityCount > 0 ) {
                startUp();
            }
        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {
            debug(tag(this), "Decrementing activity count: %s", activity.getClass().getSimpleName() );
            activityCount--;
            if ( activityCount < 1 ) {
                tearDown();
            }
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    private void startUp() {
        ContextKeeper.setContext(this);
    }

    private void tearDown() {
        ContextKeeper.releaseContextAndClose();
    }

}
