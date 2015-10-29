package com.example.chongzi.qd1;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hran on 2015/4/9.
 */
public class MposPlugin extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("show".equals(action)) {
            showToast(args.getString(0)+"_1", args.getInt(1));
            callbackContext.success("success");
        }else if("getdevicelist".equals(action)){
            List<MposDevice> listDevice=getdevicelist();
            callbackContext.success(gson.toJson(listDevice);
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
    private List<MposDevice> getdevicelist(){
        List<MposDevice> listDevice=new ArrayList<MposDevice>();
        MposDevice mposDevice1=new MposDevice();
        mposDevice1.name="aaa";
        mposDevice1.address="AAA";
        listDevice.add(mposDevice1);
        MposDevice mposDevice2=new MposDevice();
        mposDevice2.name="bbb";
        mposDevice2.address="BBB";
        listDevice.add(mposDevice2);
        return listDevice;
    }
}
 class MposDevice{
    public String name;
     public String address;
}