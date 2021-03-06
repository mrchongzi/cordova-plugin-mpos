package chongzi.cordova.mpos;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import android.bluetooth.BluetoothDevice;
/**
 * Created by Hran on 2015/4/9.
 */
public class MposPlugin extends CordovaPlugin {
	BluetoothManager bluetoothManager;
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("show".equals(action)) {
            showToast(args.getString(0)+"_1", args.getInt(1));
            callbackContext.success("success");
        }else if("getdevicelist".equals(action)){
            callbackContext.success(getdevicelist());
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
    private String getdevicelist(){
        
        return "aaa,bbb,ccc";
    }
}
