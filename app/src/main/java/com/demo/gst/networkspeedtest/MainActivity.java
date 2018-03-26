package com.demo.gst.networkspeedtest;

import android.graphics.Typeface;
import android.net.TrafficStats;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private TextView networkTypeTv, download_tv, upload_tv,downloadIcon,uploadIcon;
    private long downloadSpeed = 0;
    private long uploadSpeed = 0;
    private RecyclerView recyclerView;
    private Handler mHandler = new Handler();
    private static ArrayList<NetworkData> dataList;
    private Typeface fontawesome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fontawesome = Typeface.createFromAsset(getAssets(),"fa-solid-900.ttf");

        networkTypeTv = (TextView) findViewById(R.id.network_type);
        download_tv = (TextView) findViewById(R.id.download_speed);
        upload_tv = (TextView) findViewById(R.id.upload_speed);

        //region GitHub changes Test
        //Hello GitHub Changes
        //endregion

        downloadIcon = (TextView) findViewById(R.id.downloadIcon);
        downloadIcon.setTypeface(fontawesome);

        uploadIcon = (TextView) findViewById(R.id.uploadIcon);
        uploadIcon.setTypeface(fontawesome);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        downloadSpeed = TrafficStats.getTotalRxBytes();
        uploadSpeed = TrafficStats.getTotalTxBytes();

        dataList = new ArrayList<>();

        if (NetworkType.isConnected(MainActivity.this)) {

            //region Check If Traffic Stats Work
            if (downloadSpeed == TrafficStats.UNSUPPORTED || uploadSpeed == TrafficStats.UNSUPPORTED) {

                AlertDialog.Builder alert = new AlertDialog.Builder(this);

                alert.setTitle("Uh Oh!");

                alert.setMessage("Your device does not support traffic stat monitoring.");

                alert.show();

            } else {

                mHandler.postDelayed(mRunnable, 1000);
            }
            //endregion

        } else {
            Toast.makeText(this, "Internet Not Connected", Toast.LENGTH_SHORT).show();
        }
    }

    private final Runnable mRunnable = new Runnable() {

        public void run() {

            NetworkData data = new NetworkData();
            //region Check Network Type
            if (NetworkType.isConnectedWifi(MainActivity.this)) {
                data.setNetworkType("WiFi");

            } else if (NetworkType.isConnectedMobile(MainActivity.this)) {

                data.setNetworkType("Mobile Data");
            }
            //endregion

            Calendar cal = Calendar.getInstance();
            Date currentLocalTime = cal.getTime();
            DateFormat date = new SimpleDateFormat("HH:mm:ss");

            data.setDateTime(date.format(currentLocalTime));
            downloadSpeed = TrafficStats.getTotalRxBytes();
            uploadSpeed = TrafficStats.getTotalTxBytes();
            data.setDownloadSpeed(getFileSize(downloadSpeed));
            data.setUploadSpeed(getFileSize(uploadSpeed));

            networkTypeTv.setText(data.getNetworkType());
            download_tv.setText(data.getDownloadSpeed());
            upload_tv.setText(data.getUploadSpeed());

            dataList.add(data);
            recyclerView.setAdapter(new NetworkAdapter(MainActivity.this,dataList));


            mHandler.postDelayed(mRunnable, 30000);

        }

    };

    //Convert from bytes to kb,mb or gb
    public static String getFileSize(long size) {
        if (size <= 0)
            return "0";
        final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }
}
