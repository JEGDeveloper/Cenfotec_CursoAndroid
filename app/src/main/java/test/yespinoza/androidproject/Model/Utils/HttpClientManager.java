package test.yespinoza.androidproject.Model.Utils;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;
import test.yespinoza.androidproject.Model.Biometric.BiometricPerson;


public class HttpClientManager {

    public static String URL_Biometric_API = "http://tecnobcrmovil.cloudapp.net:1011/Biometric_API_v2/";

    private RequestQueue cola;

    public HttpClientManager(Context pContext){
        cola = Volley.newRequestQueue(pContext);
    }

    public void Get(String url, Response.Listener<String> callBack_OK, Response.ErrorListener callback_ERROR){
        StringRequest oRequest = new StringRequest(Request.Method.GET, url, callBack_OK, callback_ERROR);
        cola.add(oRequest);
    }

    public void GetJSON(String url, Response.Listener<JSONObject> callBack_OK, Response.ErrorListener callback_ERROR){
        JsonObjectRequest oRequest = new JsonObjectRequest(Request.Method.GET, url, null, callBack_OK, callback_ERROR);
        cola.add(oRequest);
    }

    public void Biometric_API_POST(String method, JSONObject params,Response.Listener<JSONObject> callBack_OK, Response.ErrorListener callback_ERROR){
        JsonObjectRequest oRequest = new JsonObjectRequest(Request.Method.POST, URL_Biometric_API.concat(method), params, callBack_OK, callback_ERROR);
        oRequest.setRetryPolicy(new DefaultRetryPolicy(10000,1,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        cola.add(oRequest);
    }

    public static boolean validateNetworkConnetion(ConnectivityManager pConnectivityManager) {
        NetworkInfo activeNetworkInfo = pConnectivityManager.getActiveNetworkInfo();
        boolean networkConnetion = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        return networkConnetion;
    }

}