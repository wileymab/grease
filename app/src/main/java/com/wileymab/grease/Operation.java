package com.wileymab.grease;

/**
 * Created by m441527 on 2/8/17.
 */

public class Operation {
    private long delay = 0L;
    private Runnable action;


    public Operation setAction(Runnable action) {
        this.action = action;
        return this;
    }

    public Operation setDelay(long milliseconds) {
        if ( milliseconds < 0 ) {
            delay = 0;
        }
        else {
            delay = milliseconds;
        }
        return this;
    }


    public Runnable getAction() {
        return action;
    }

    public long getDelay() {
        return delay;
    }
}
