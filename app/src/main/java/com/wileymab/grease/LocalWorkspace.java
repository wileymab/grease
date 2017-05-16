package com.wileymab.grease;

import java.util.HashMap;

/**
 * Created by m441527 on 2/3/17.
 */

public class LocalWorkspace {

    private static LocalWorkspace instance;

    public static LocalWorkspace get() {
        if ( instance == null ) {
            instance = new LocalWorkspace();
        }
        return instance;
    }

    private HashMap<Object, Object> units = new HashMap<>();


    public void put(Object key, Object value) {

        if ( Functions.noneAreNull(key,value) ) {

            Object valueForKey = units.get(key);
            if ( valueForKey == null ) {
                units.put(key,value);
            }
            else {
                throw new WorkspaceKeyCollisionException(key);
            }

        }
        else {
            throw new WorkspaceInputException(key,value);
        }

    }

    public <T> T get(Object key) {

        if ( key != null ) {

            Object value = units.get(key);
            if ( value != null ){
                T returnValue;
                try {
                    returnValue =(T) value;
                    return returnValue;
                }
                catch( ClassCastException e ) {
                    throw new WorkspaceReturnTypeException(key,value);
                }
            }
        }

        throw new WorkspaceMappingNotFoundException(key);

    }

    public void removeKey(Object key) {

        if ( key != null ) {
            if ( units.containsKey(key) ) {
                units.remove(key);
            }
        }
        else {
            throw new WorkspaceMappingNotFoundException(key);
        }

    }

    public class WorkspaceReturnTypeException extends IllegalStateException {
        public WorkspaceReturnTypeException(Object key, Object value) {
            super(String.format("Workspace was not able to cast data correctly. Found data type = %s, for key = %s",value.getClass().getSimpleName(), key));
        }
    }

    public class WorkspaceKeyCollisionException extends IllegalStateException {
        public WorkspaceKeyCollisionException(Object key) {
            super(String.format("Workspace key collision on key = %s", key));
        }
    }

    public class WorkspaceInputException extends IllegalStateException {
        public WorkspaceInputException(Object key, Object value) {
            super(String.format("Workspace cannot accept null key or null values. Attempted input = [key = %s, value = %s]",key, value));
        }
    }

    public class WorkspaceMappingNotFoundException extends IllegalStateException {
        public WorkspaceMappingNotFoundException(Object key) {
            super(
                    ( key == null ) ?
                            "Workspace cannot contain mappings for null keys." :
                            String.format("Workspace contains no mapping for key = %s", key)
            );
        }
    }

}
