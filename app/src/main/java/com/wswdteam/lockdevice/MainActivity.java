package com.wswdteam.lockdevice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
<<<<<<< HEAD
    //public static final int RESULT_ENABLE = 11;
    private ComponentName compName;
=======
>>>>>>> 20c8d00 (20250512)
    private Boolean first = true;

    // android:theme="@style/Theme.LockDevice"
    // android:theme="@android:style/Theme.NoDisplay"

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);

        try {
            devicePolicyManager.lockNow();
            quitApp();
        } catch (Exception e) {
<<<<<<< HEAD
            //ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
            compName = new ComponentName(this, LDAdmin.class);
=======
            var compName = new ComponentName(this, LDAdmin.class);
>>>>>>> 20c8d00 (20250512)
            boolean active = devicePolicyManager.isAdminActive(compName);
            if (active) {
                devicePolicyManager.lockNow();
                quitApp();
            } else {
                Toast.makeText(this, R.string.service_disable, Toast.LENGTH_SHORT).show();
                adminService();
<<<<<<< HEAD
                quitApp();
=======
                //quitApp();
>>>>>>> 20c8d00 (20250512)
            }
        }
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        } catch (Exception e2) {
            quitApp();
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        if (!first) {
            quitApp();
        } else {
            first = false;
        }
    }

    public void adminService() {
<<<<<<< HEAD
        //boolean active = devicePolicyManager.isAdminActive(compName);
        //if (!active) {
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, compName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, R.string.needadmin);
        //startActivityForResult(intent, RESULT_ENABLE);
        //}else{
            //devicePolicyManager.removeActiveAdmin(compName);
        //}
=======
        startActivity(new Intent().setComponent(new ComponentName("com.android.settings", "com.android.settings.DeviceAdminSettings")));
>>>>>>> 20c8d00 (20250512)
    }

    public void quitApp() {
        finishAndRemoveTask();
        int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);
    }


}