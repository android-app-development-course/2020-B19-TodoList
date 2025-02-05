package com.example.todolist.Utils;

import android.content.Context;
import android.os.PowerManager;

/**
 * 闹钟提醒帮助类
 */
public class WakeLockHelper {
    private final String mWakeLockId;
    private PowerManager.WakeLock mWakeLock;

    public WakeLockHelper(final String wakeLockId) {
        mWakeLockId = wakeLockId;
    }

    public void acquire(final Context context) {
        if (mWakeLock == null) {
            final PowerManager pm = (PowerManager)
                    context.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, mWakeLockId);
            mWakeLock.acquire();
        }
    }

    public void release() {
        if (mWakeLock != null) {
            try {
                mWakeLock.release();
                mWakeLock = null;
            } catch (RuntimeException ex) {
                ex.printStackTrace();
            }
        }
    }
}
