package com.wswdteam.lockdevice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //public static final int RESULT_ENABLE = 11;
    private ComponentName compName;
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
            //ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
            compName = new ComponentName(this, LDAdmin.class);
            boolean active = devicePolicyManager.isAdminActive(compName);
            if (active) {
                devicePolicyManager.lockNow();
                quitApp();
            } else {
                Toast.makeText(this, R.string.service_disable, Toast.LENGTH_SHORT).show();
                adminService();
                quitApp();
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
        //boolean active = devicePolicyManager.isAdminActive(compName);
        //if (!active) {
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, compName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, R.string.needadmin);
        //startActivityForResult(intent, RESULT_ENABLE);
        //}else{
            //devicePolicyManager.removeActiveAdmin(compName);
        //}
    }


    public void quitApp() {
        finishAndRemoveTask();
        int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);
    }


}