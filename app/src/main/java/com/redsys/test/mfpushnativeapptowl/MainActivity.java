package com.redsys.test.mfpushnativeapptowl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.worklight.common.Logger;
import com.worklight.wlclient.api.WLClient;
import com.worklight.wlclient.api.WLFailResponse;
import com.worklight.wlclient.api.WLResponse;
import com.worklight.wlclient.api.WLResponseListener;

import java.net.URL;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String loggerTag = "testLoggerWL";

    private WLClient client;

    private Logger logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Logger.setContext(this);

        client = WLClient.createInstance(this);

        client.setHeartBeatInterval(10);

        client.connect(new WLResponseListener() {
            @Override
            public void onSuccess(WLResponse wlResponse) {

                Log.d("mfp", "Successssssssssssssssssssssssss.....................");

                Log.d("mfp", wlResponse.getResponseText());

                Log.d("mfp", wlResponse.getResponseJSON().toString());
            }

            @Override
            public void onFailure(WLFailResponse wlFailResponse) {

                Log.d("mfp", "Faillllllllllllllllllllllllllllllll........................");
            }
        });

        client.setHeartBeatInterval(10);


        logger = Logger.getInstance(loggerTag);


        logger.info("onCreate...............................");


    }

    public void getInfo(View view) {

        URL serverUrl = client.getServerUrl();

        Log.w("mfp", "server url: " + serverUrl.toString());


        logger = Logger.getInstance("testLoggerWL");

        logger.error("test error time=" + new Date());
        logger.info("test info date = " + new Date() );

        logger.error("strange log behavior time = " + new Date());

        Toast.makeText(this, "logs has been sent", Toast.LENGTH_LONG).show();
    }
}
