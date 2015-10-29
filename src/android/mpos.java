public class mpos extends CordovaPlugin
{
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException 
    {
        webView.postMessage("splashscreen", "hide");
        callbackContext.success();
        return true;
    }
}