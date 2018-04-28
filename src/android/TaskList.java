package com.cordova.plugins.tasklist;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.os.Build;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;

public class TaskList extends CordovaPlugin {
    private static final String TAG = "TaskList";

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("includeInTaskList")) {
            includeInTaskList();
        }

        return true;
    }

    /**
     * Include the app In the recent tasks list.
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void includeInTaskList() {
        ActivityManager am = (ActivityManager) this.cordova.getActivity().getSystemService(ACTIVITY_SERVICE);

        if (am == null || Build.VERSION.SDK_INT < 21)
            return;

        List<ActivityManager.AppTask> tasks = am.getAppTasks();

        if (tasks == null || tasks.isEmpty())
            return;

        tasks.get(0).setExcludeFromRecents(false);
    }
}
