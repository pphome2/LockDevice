package com.wswdteam.lockdevice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DevicePolicyManager devicePolicyManager;
    public static final int RESULT_ENABLE = 11;
    private ActivityManager activityManager;
    private ComponentName compName;

    // android:theme="@style/Theme.LockDevice"
    // android:theme="@android:style/Theme.NoDisplay"

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        devicePolicyManager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);

        try {
            this.devicePolicyManager.lockNow();
            quitApp();
        } catch (Exception e) {
            activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
            compName = new ComponentName(this, LDAdmin.class);
            boolean active = this.devicePolicyManager.isAdminActive(compName);
            if (active) {
                //this.devicePolicyManager.lockNow();
                //quitApp();
            } else {
                //Toast.makeText(this, R.string.service_disable, Toast.LENGTH_SHORT).show();
                adminService();
            }
        }
        //setTheme(R.style.Theme_LockDevice);
        //setTheme("@style/Theme.LockDevice");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void lockDev(View v) {
        //DevicePolicyManager devicePolicyManager;
        boolean active = this.devicePolicyManager.isAdminActive(compName);
        //active = true;
        if (active) {
            this.devicePolicyManager.lockNow();
            quitApp();
        } else {
            Toast.makeText(this, R.string.service_disable, Toast.LENGTH_SHORT).show();
        }
    }



    public void adminService() {
        //boolean active = devicePolicyManager.isAdminActive(compName);
        //if (!active) {
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, compName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, R.string.needadmin);
        startActivityForResult(intent, RESULT_ENABLE);
        //}else{
            //devicePolicyManager.removeActiveAdmin(compName);
        //}
    }


    public void quitApp() {
        finishAndRemoveTask();
        int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);
    }


    public void quitApp2(View v) {
        finishAndRemoveTask();
        int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);
    }



}