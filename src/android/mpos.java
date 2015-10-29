package me.hran.cordova.toast;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Hran on 2015/4/9.
 */
public class ToastPlugin extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("show".equals(action)) {
            showToast(args.getString(0), args.getInt(1));
        }
        return super.execute(action, args, callbackContext);
    }

    private void showToast(String text, int type) {
        if (type == 1) {
            android.widget.Toast.makeText(cordova.getActivity(), text, android.widget.Toast.LENGTH_LONG).show();
        } else {
            android.widget.Toast.makeText(cordova.getActivity(), text, android.widget.Toast.LENGTH_SHORT).show();
        }
    }
}