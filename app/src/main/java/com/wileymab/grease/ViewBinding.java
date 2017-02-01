package com.wileymab.grease;

import android.app.Activity;
import android.app.Fragment;
import android.view.View;

/**
 * Created by matt on 2/1/17.
 */

public class ViewBinding {

    public static <T extends View> T getView(Activity activity, int resourceId) {
        return (T)activity.findViewById(resourceId);
    }

    public static <T extends View> T getView(Fragment fragment, int resourceId) {
        return getView(fragment.getView(), resourceId);
    }

    public static <T extends View> T getView(View rootView, int resourceId) {
        return (T)rootView.findViewById(resourceId);
    }

}
