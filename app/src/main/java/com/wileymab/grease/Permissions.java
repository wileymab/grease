package com.wileymab.grease;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import java.util.LinkedList;
import java.util.List;

import static com.wileymab.grease.Logging.debug;
import static com.wileymab.grease.Logging.error;
import static com.wileymab.grease.Logging.tag;

/**
 * Created by m441527 on 2/1/17.
 */

public class Permissions {

    public static class PermissionRequest {

        private static final int REQUEST_PERMISSIONS = 127;

        private PermissionRequestListener listener;
        private List<String> denied = new LinkedList<>();
        private List<String> allowed = new LinkedList<>();

        public PermissionRequest(PermissionRequestListener listener) {
            this.listener = listener;
        }

        public void checkPermissions(final Activity activity, String[] permissions) {

            if ( Build.VERSION.SDK_INT >= 23 ) {

                debug(tag(PermissionRequest.class),"Requesting runtime permissions ... ");

                List<String> permissionsToRequest = new LinkedList<>();

                for(String permission : permissions) {
                    if ( ActivityCompat.checkSelfPermission(activity,permission) != PackageManager.PERMISSION_GRANTED ) {
                        permissionsToRequest.add(permission);
                    }
                    else {
                        debug(tag(PermissionRequest.class), "Permission granted = %s", permission);
                        allowed.add(permission);
                    }
                }

                if ( permissionsToRequest.size() > 0 ) {
                    activity.requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]), REQUEST_PERMISSIONS);
                    return;
                }
            }

            listener.onPermissionsAllowed(permissions);

        }

        public void onResult(int requestCode, String[] permissions, int[] grants) {

            switch( requestCode ) {
                case REQUEST_PERMISSIONS:

                    if ( grants.length > 0 ) {

                        boolean success = true;
                        for ( int i = 0; i < grants.length; i++ ) {
                            boolean granted = grants[i] == PackageManager.PERMISSION_GRANTED;
                            success = success && granted;
                            if (!granted) {
                                error(tag(PermissionRequest.class),"Permission denied: %s", permissions[i]);
                                denied.add(permissions[i]);
                            }
                            else
                                allowed.add(permissions[i]);
                        }

                        if ( !success) listener.onPermissionsDenied(denied.toArray(new String[denied.size()]));
                        listener.onPermissionsAllowed(allowed.toArray(new String[allowed.size()]));
                    }

                    break;
            }

        }

        public interface PermissionRequestListener {
            void onPermissionsDenied(String[] denied);
            void onPermissionsAllowed(String[] granted);
        }

    }

}
