package chongzi.cordova.mpos;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.ArrayList;
import java.util.List;

public class BluetoothManager {
    private static BluetoothAdapter mBluetoothAdapter;
    private Context context;
    private int mSearchSecond;
    private int lastTime;
    private List<BluetoothDevice> deviceList;
    private DeviceListener deviceListener;
    private final String TAG = "BluetoothManager";

    public void setDeviceListener(DeviceListener deviceListener) {
        this.deviceListener = deviceListener;
    }

    public boolean isListener() {
        return deviceListener != null;
    }

    public BluetoothManager(Context context) {
        if (context == null) {
            throw new RuntimeException("context is null");
        }
        this.context = context;
        if (mBluetoothAdapter == null) {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        }
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                final BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                for (BluetoothDevice deviceTmp : deviceList) {
                    if (device.getAddress().equals(deviceTmp.getAddress())) {
                        return;
                    }
                }
                deviceList.add(device);
                dispatchRun(new Runnable() {
                    @Override
                    public void run() {
                        if (isListener()) {
                            deviceListener.discovered(device);
                        }
                    }
                });
            }
        }
    };

    public void discover(int seconds) {
        if (!mBluetoothAdapter.isEnabled()) {
            dispatchRun(new Runnable() {
                @Override
                public void run() {
                    if (isListener())
                        deviceListener.discoverFail("蓝牙不可用");
                }
            });
            return;
        }
        if (mBluetoothAdapter.isDiscovering()) {
            return;
        }
        //默认3秒
        mSearchSecond = seconds <= 0 ? 3 : seconds;
        lastTime = 0;
        deviceList = new ArrayList<BluetoothDevice>();
        beginDiscovery();
    }


    private void beginDiscovery() {
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        context.registerReceiver(mReceiver, filter);
        mBluetoothAdapter.startDiscovery();
        new TimerThread().start();
    }

    private void stopDiscovery() {
        mBluetoothAdapter.cancelDiscovery();
        context.unregisterReceiver(mReceiver);
        dispatchRun(new Runnable() {
            @Override
            public void run() {
                if (isListener())
                {
                    deviceListener.discoverFinished(deviceList);
                }
            }
        });
    }

    private class TimerThread extends Thread {
        public void run() {
            try {
                for (; lastTime < mSearchSecond; lastTime++) {
                    Thread.sleep(1000);
                    if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
                        break;
                    }
                }
                stopDiscovery();
            } catch (InterruptedException e) {
                stopDiscovery();
            }
        }
    }


}


