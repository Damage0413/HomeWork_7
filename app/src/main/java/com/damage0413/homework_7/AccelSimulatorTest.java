package com.damage0413.homework_7;

import org.openintents.sensorsimulator.hardware.Sensor;
import org.openintents.sensorsimulator.hardware.SensorEvent;
import org.openintents.sensorsimulator.hardware.SensorEventListener;
import org.openintents.sensorsimulator.hardware.SensorManagerSimulator;
import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.EditText;
public class AccelSimulatorTest extends Activity implements SensorEventListener {
    // 定义模拟器的Sensor管理器
    private SensorManagerSimulator mSensorManager;
    // 定义界面上的文本框组件
    EditText etTxt1;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        // 获取程序界面的文本框组件
        etTxt1 = (EditText) findViewById(R.id.txt1);
        // 获取传感器模拟器的传感器管理服务
        mSensorManager =SensorManagerSimulator.getSystemService(this,
                SENSOR_SERVICE);
        // 连接传感器模拟器
        mSensorManager.connectSimulator();
    }
    protected void onResume()	{
        super.onResume();
        // 为系统的加速度传感器注册监听器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);
    }
    protected void onStop()	{
        // 取消注册
        mSensorManager.unregisterListener(this);
        super.onStop();
    }
    // 以下是实现SensorEventListener接口必须实现的方法
    // 当传感器的值发生改变时回调该方法
    public void onSensorChanged(SensorEvent event)	{
        float[] values = event.values;
        StringBuilder sb = new StringBuilder();
        sb.append("X方向上的加速度：");
        sb.append(values[0]);
        sb.append("\nY方向上的加速度：");
        sb.append(values[1]);
        sb.append("\nZ方向上的加速度：");
        sb.append(values[2]);
        etTxt1.setText(sb.toString());
    }
    // 当传感器精度改变时回调该方法。
    public void onAccuracyChanged(Sensor sensor, int accuracy)	{
    }
}

