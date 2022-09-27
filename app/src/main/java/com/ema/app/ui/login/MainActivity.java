package com.ema.app.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.ema.app.R;
import com.ema.app.controller.Address;
import com.ema.app.controller.AddressEndpoint;
import com.ema.app.controller.D;
import com.ema.app.controller.I;
import com.ema.app.controller.Info;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private String secret = "1HmgYQ7g^C%y7HzX9$RiC5ArGryGjVjzNb06gyp$l8DN!hfrHeDt28Yze2vU6DE8XLVJws";
    //example
    //private byte[] secure_flag = { 95, 21, -88, -118, 28, -79, 50, 61, 36, 39, 17, 92, 96, 84, 47, 126, 3, -77, 38, -12, -82, 10, -59, 55,
    //        75, -109, 51, -67, -89, -84, -108, -36, -84, -78, -121, 114, -96, -76, 38, 32, -5, 87, 127, -63, 36, -6, 99, 84, -26, -30, -27, -62, 59, -84, -17, -67 };
    //real
    private byte[] secure_flag = {18, 35, 40, 89, 40, -107, 91, 103, -28, 51, -23, 93, 31, 41, 28,
            94, 88, -120, 10, -8, 66, -29, -95, 126, -7, -122, 65, 106, -125, -89, -37, 18, 98, 59,
            38, 25, -39, 111, 70, -20, 8, -72, -7, -128, 105, 3, -74, 117, -26, -30, -27, -62, 59, -84, -17, -67};

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove clock
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //FLAG2
        //challenge to force dev to not keep secrets in memory
        D d = new D();
        try {
            String s = d.getFlag(secret, secure_flag);
            //System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //FLAG3
        //challenge to force dev to not keep secrets in code
        //char[] api_host = new char[]{'h','t','t','p','s',':','/','/','a','p','i','.','e','m','a','.','c','o','m'};
        char[] api_host = new char[]{'h','t','t','p',':','/','/','1','2','7','.','0','.','0','.','1',':','8','0','8','1'};
        char[] api_id = new char[]{'e','m','a'};
        char[] api_secret = new char[]{'^','#','5','f','&','9','w','F','V','$','D','4','&','L','l','6','D','6','A','G','m','b','X','c','5','s','U','2','A','8','Y','!','G','%','w','P','f','0','0','0','9','5','l','@','T','4','B','u','k','S','s','H','o','b','W','j','W','a','G','V','2','%','X','X','6','m','k','E','V','K'};

        //TODO remove comments
        //FLAG3
        //System.out.println(api_secret);
        //Arrays.fill(api_host, '0');
        //Arrays.fill(api_id, '0');
        //Arrays.fill(api_secret, '0');
        //System.out.println(api_secret);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode auth = mapper.createObjectNode();

        auth.put("api_id",String.valueOf(api_id));
        auth.put("api_secret",String.valueOf(api_secret));

        //FLAG3
        try {
            I p = new I();
            OkHttpClient p_client = p.client_auth(auth, "POST");
            Retrofit http_post = new Retrofit.Builder()
                    .baseUrl(String.valueOf(api_host))
                    .addConverterFactory(JacksonConverterFactory.create())
                    .client(p_client)
                    .build();

            AddressEndpoint addrp_endpoint = http_post.create(AddressEndpoint.class);

            Info info = addrp_endpoint.getInfo().get();
            //System.out.println(info.getInfo());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (java.lang.IllegalArgumentException e) {
            //e.printStackTrace();
            System.out.println("Internal error");
        }

        //FLAG4
        try {
            I g = new I();
            OkHttpClient g_client = g.client_auth(auth, "GET");
            Retrofit http_get = new Retrofit.Builder()
                    .baseUrl(String.valueOf(api_host))
                    .addConverterFactory(JacksonConverterFactory.create())
                    .client(g_client)
                    .build();

            AddressEndpoint addrg_endpoint = http_get.create(AddressEndpoint.class);

            Address addr = addrg_endpoint.getAddress("00000000").get();
            //System.out.println(addr.categoria);
            //Toast.makeText(getApplicationContext(), addr.categoria, Toast.LENGTH_LONG).show();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (java.lang.IllegalArgumentException e) {
            //e.printStackTrace();
        }
    } //end OnCreate
};