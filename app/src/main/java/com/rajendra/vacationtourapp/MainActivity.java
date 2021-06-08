package com.rajendra.vacationtourapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.rajendra.vacationtourapp.adapter.RecentsAdapter;
import com.rajendra.vacationtourapp.adapter.TopPlacesAdapter;
import com.rajendra.vacationtourapp.model.RecentsData;
import com.rajendra.vacationtourapp.model.Rest.RestHelper;
import com.rajendra.vacationtourapp.model.TopPlacesData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class MainActivity extends AppCompatActivity {

    RecyclerView recentRecycler, topPlacesRecycler;
    RecentsAdapter recentsAdapter;
    TopPlacesAdapter topPlacesAdapter;
    ConstraintLayout profilePhoto;
    ArrayList<RecentsData> recentsData=new ArrayList<>();
    ArrayList<TopPlacesData> topPlacesData=new ArrayList<>();
//    PrefManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        session=new PrefManager(MainActivity.this);
//        session.checkLogin();
        profilePhoto=findViewById(R.id.constraintLayout);
        getData();
        getDataLocation();
//        profilePhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                session.logoutUser();
//            }
//        });

        // Now here we will add some dummy data in our model class

//        List<RecentsData> recentsDataList = new ArrayList<>();
//        recentsDataList.add(new RecentsData("AM Lake","India","From $200",R.drawable.recentimage1));
//        recentsDataList.add(new RecentsData("Nilgiri Hills","India","From $300",R.drawable.recentimage2));
//        recentsDataList.add(new RecentsData("AM Lake","India","From $200",R.drawable.recentimage1));
//        recentsDataList.add(new RecentsData("Nilgiri Hills","India","From $300",R.drawable.recentimage2));
//        recentsDataList.add(new RecentsData("AM Lake","India","From $200",R.drawable.recentimage1));
//        recentsDataList.add(new RecentsData("Nilgiri Hills","India","From $300",R.drawable.recentimage2));

        setRecentRecycler();

//        List<TopPlacesData> topPlacesDataList = new ArrayList<>();
//        topPlacesDataList.add(new TopPlacesData("Kasimir Hill","India","$200 - $500",R.drawable.topplaces));
//        topPlacesDataList.add(new TopPlacesData("Kasimir Hill","India","$200 - $500",R.drawable.topplaces));
//        topPlacesDataList.add(new TopPlacesData("Kasimir Hill","India","$200 - $500",R.drawable.topplaces));
//        topPlacesDataList.add(new TopPlacesData("Kasimir Hill","India","$200 - $500",R.drawable.topplaces));
//        topPlacesDataList.add(new TopPlacesData("Kasimir Hill","India","$200 - $500",R.drawable.topplaces));

        setTopPlacesRecycler();
    }

    private  void setRecentRecycler(){

        recentRecycler = findViewById(R.id.recent_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recentRecycler.setLayoutManager(layoutManager);
        recentsAdapter = new RecentsAdapter(this, recentsData);


    }

    private  void setTopPlacesRecycler(){

        topPlacesRecycler = findViewById(R.id.top_places_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        topPlacesRecycler.setLayoutManager(layoutManager);
        topPlacesAdapter = new TopPlacesAdapter(this, topPlacesData);
        topPlacesRecycler.setAdapter(topPlacesAdapter);

    }
    public void getData (){
        JSONObject payload = new JSONObject();

        Response.Listener successResp = new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response){
                //spinner.dismiss();
                Log.e(" ", response.toString());
                try {
                    String success= response.getString("success");
                    if (success.equals("true")){
                        Log.e("Success","True");
                        JSONArray item= response.getJSONArray("data");
                        for (int i=2;i<item.length();i++){
                            JSONObject items = item.getJSONObject(i);
                            RecentsData recentsData1=new RecentsData();
                            recentsData1.setId(items.getString("id"));
                            recentsData1.setPlaceName(items.getString("name"));
                            recentsData1.setCountryName(items.getString("location"));
                            recentsData1.setPrice(items.getString("budget"));
                            recentsData1.setImageUrl(items.getString("picture"));
                            recentsData.add(recentsData1);
                        }
                        recentRecycler.setAdapter(recentsAdapter);
                    }
                    String a="hahahaha";
                    JSONObject result= response.getJSONObject("data");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }};
        Response.ErrorListener errorResp = RestHelper.generalErrorResponse("", null);
        JsonObjectRequest myReq=new JsonObjectRequest(Request.Method.GET,"http://34.101.87.18:8000/rest/getListLocation/",payload,successResp,errorResp);
//        spinner.setMessage("Loading.....");
//        spinner.show();
        AppController.getRest().addToReqq(myReq,"");
    }
    public void getDataLocation (){
        JSONObject payload = new JSONObject();

        Response.Listener successResp = new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response){
                //spinner.dismiss();
                Log.e(" ", response.toString());
                try {
                    String success= response.getString("success");
                    if (success.equals("true")){
                        Log.e("Success","True");
                        JSONArray item= response.getJSONArray("data");
                        for (int i=0;i<item.length();i++){
                            JSONObject items = item.getJSONObject(i);
                            TopPlacesData recentsData1=new TopPlacesData();
                            recentsData1.setId(items.getString("id"));
                            recentsData1.setPlaceName(items.getString("name"));
                            recentsData1.setCountryName(items.getString("location"));
                            recentsData1.setPrice(items.getString("budget"));
                            recentsData1.setImageUrl(items.getString("picture"));
                            topPlacesData.add(recentsData1);
                        }
                        topPlacesRecycler.setAdapter(topPlacesAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }};
        Response.ErrorListener errorResp = RestHelper.generalErrorResponse("", null);
        JsonObjectRequest myReq=new JsonObjectRequest(Request.Method.GET,"http://34.101.87.18:8000/rest/getListLocation/",payload,successResp,errorResp);
//        spinner.setMessage("Loading.....");
//        spinner.show();
        AppController.getRest().addToReqq(myReq,"");
    }

    // Hi all, today we are going to make a holiday tour app.
    // so lets see i will show you what we are going to build
    // so lets get started.
    // before getting started make sure to subscribe and hit the bell i con to get notified
    // everytime i post a video.
    // lets first import image assets
    // Now we will add a recycler view for recents data.
    // lets make a model class first.
    // now we create a adapter class for holding data
    // our adapter class is ready now we will setup recyclerview

    // So we have setup recents items recyclerview
    //Now we do same setup for top places
    // lets do it fast.
    // Now i will add a bottom navigation
    // now we will set the details activity
    // when user click on any of places details activity will open
    // So this app is done.
    // Please like share and subscribe
    // if any question plz do comment
    // Thanks for watching see you in the next tutorial

}
