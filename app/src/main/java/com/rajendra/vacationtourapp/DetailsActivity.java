package com.rajendra.vacationtourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.rajendra.vacationtourapp.model.Json.JsonHelper;
import com.rajendra.vacationtourapp.model.Rest.RestHelper;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailsActivity extends AppCompatActivity {

    String id;
    TextView namePlace,price,description;
    ImageView locations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        namePlace=findViewById(R.id.textView6);
        price=findViewById(R.id.textView10);
        description=findViewById(R.id.textView14);
        locations=findViewById(R.id.imageView3);
        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        getData(id);
    }
    public void getData (String ids){
        JSONObject payload = new JSONObject();

        Response.Listener successResp = new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response){
                //spinner.dismiss();
                Log.e(" ", response.toString());
                try {
                    String success= response.getString("success");
                    if (success.equals("true")){
                        JSONObject result= response.getJSONObject("data");
                        Log.e("Success","True");
                        namePlace.setText(result.getString("name"));
                        price.setText(result.getString("budget"));
                        description.setText(result.getString("description"));
                        Picasso.get().load(result.getString("picture")).into(locations);
                    }
                    String a="hahahaha";
                    JSONObject result= response.getJSONObject("data");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                        for(int i = 0; i < result.length(); i++){
//                            JSONObject item = result.getJSONObject(i);
//                            String imgName=item.getString("image");
//                            String url=RestUrl.getImgBase(RestUrl.IMAGE_URL_INFO)+imgName+"?time="+times;
//                            Log.e(" ",url);
//                            Picasso.get()
//                                    .load(url)
//                                    .placeholder(R.drawable.placeholder)
//                                    .into(imgDtlInf);
//                            Picasso.get()
//                                    .load(url)
//                                    .placeholder(R.drawable.placeholder)
//                                    .into(background);
//                            jdlDtl.setText(item.getString("judul"));
//                            descDtl.setText(item.getString("description"));
//                        }

            }};
        Response.ErrorListener errorResp = RestHelper.generalErrorResponse("", null);
        JsonObjectRequest myReq=new JsonObjectRequest(Request.Method.GET,"http://34.101.87.18:8000/rest/update/"+ids,payload,successResp,errorResp);
//        spinner.setMessage("Loading.....");
//        spinner.show();
        AppController.getRest().addToReqq(myReq,"");
    }
}
