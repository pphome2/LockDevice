package com.wswdteam.lockdevice;

        import android.app.admin.DeviceAdminReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.widget.Toast;
        import androidx.annotation.NonNull;


public class LDAdmin extends DeviceAdminReceiver {

    @Override
    public void onEnabled(@NonNull Context context, @NonNull Intent intent) {
        Toast.makeText(context, R.string.service_enable, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisabled(@NonNull Context context, @NonNull Intent intent) {
        Toast.makeText(context, R.string.service_disable, Toast.LENGTH_SHORT).show();
    }
}
