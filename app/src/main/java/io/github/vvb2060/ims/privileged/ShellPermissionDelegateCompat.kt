package io.github.vvb2060.ims.privileged

import android.app.IActivityManager
import android.system.Os
import android.util.Log
import java.lang.reflect.InvocationTargetException

internal object ShellPermissionDelegateCompat {

    fun start(
        activityManager: IActivityManager,
        tag: String,
    ): Boolean {
        return try {
            val method = activityManager.javaClass.methods.firstOrNull {
                it.name == "startDelegateShellPermissionIdentity" &&
                    it.parameterTypes.size == 2
            }

            if (method == null) {
                Log.w(
                    tag,
                    "startDelegateShellPermissionIdentity is unavailable; " +
                        "using the current Shizuku instrumentation identity",
                )
                false
            } else {
                method.isAccessible = true
                method.invoke(activityManager, Os.getuid(), null)
                Log.i(tag, "started shell permission delegation")
                true
            }
        } catch (error: Throwable) {
            Log.w(
                tag,
                "failed to start shell permission delegation; " +
                    "using the current Shizuku instrumentation identity",
                unwrap(error),
            )
            false
        }
    }

    fun stop(
        activityManager: IActivityManager,
        tag: String,
        delegated: Boolean,
    ) {
        if (!delegated) return

        try {
            val method = activityManager.javaClass.methods.firstOrNull {
                it.name == "stopDelegateShellPermissionIdentity" &&
                    it.parameterTypes.isEmpty()
            }

            if (method == null) {
                Log.w(
                    tag,
                    "stopDelegateShellPermissionIdentity is unavailable on this Android build",
                )
                return
            }

            method.isAccessible = true
            method.invoke(activityManager)
            Log.i(tag, "stopped shell permission delegation")
        } catch (error: Throwable) {
            Log.w(
                tag,
                "failed to stop shell permission delegation; operation result is preserved",
                unwrap(error),
            )
        }
    }

    private fun unwrap(error: Throwable): Throwable {
        return if (error is InvocationTargetException && error.targetException != null) {
            error.targetException
        } else {
            error
        }
    }
}
