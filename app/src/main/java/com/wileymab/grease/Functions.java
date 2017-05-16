package com.wileymab.grease;

/**
 * Created by m441527 on 2/3/17.
 */

public class Functions {

    public static boolean allAreNull(Object... objs) {
        for ( Object o : objs ) {
            if ( o != null ) return false;
        }
        return true;
    }

    public static boolean anyAreNull(Object... objs) {

        for( Object o : objs ) {
            if ( o == null ) return true;
        }

        return false;
    }

    public static boolean noneAreNull(Object... objs) {
        return !anyAreNull(objs);
    }

}
