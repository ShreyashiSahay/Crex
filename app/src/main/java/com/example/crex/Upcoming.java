package com.example.crex;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Upcoming extends Fragment {

    private static String JSON_URL= "https://mocki.io/v1/30786c0a-390e-41d5-9ad8-549ed26cba64";
    public List<model> upcomingList;
    public LinearLayoutManager linearLayoutManager;
    public Adapter adapter1;
    public RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //Confusion--> Here do we have to write method setContentView(R.layout.fragment_upcoming)???--> No, we don't bcoz already inflated
        //Code for parsing simple JSON
        super.onViewCreated(view, savedInstanceState);
        upcomingList = new ArrayList<>();
        recyclerView=view.findViewById(R.id.recyclerView);
        androidx.recyclerview.widget.LinearLayoutManager linearLayoutManager = new androidx.recyclerview.widget.LinearLayoutManager(getActivity());
        adapter1= new Adapter(getActivity(),upcomingList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter1);
        extractData();
    }

    private void extractData() {

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,JSON_URL,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONArray res= response;
                for (int i = 0; i < res.length(); i++) {
                    try {
                        JSONObject jsonObject = res.getJSONObject(i);
                        Log.d("TAG", "onResponse: "+jsonObject);

                        model md = new model();
                        md.setViewType(1);
                        md.setComdate(jsonObject.getString("date"));
                        upcomingList.add(md);
                        Log.d("Check",jsonObject.getString("date"));
                        JSONArray ja= jsonObject.getJSONArray("m");

                        for(int j=0;j<ja.length();j++) {
                            JSONObject jo = ja.getJSONObject(j);
                            model md1 = new model();
                            md1.setViewType(0);
                            md1.setTeam1(jo.getString("t1"));
                            md1.setTeam2(jo.getString("t2"));
                            md1.setT1im(jo.getString("t1flag"));
                            md1.setT2im(jo.getString("t2flag"));
                            md1.setPlace(jo.getString("match_no"));
                            md1.setDate(jo.getString("date"));
                            md1.setTime(jo.getLong("t"));
                            if(jsonObject.has("odds")){
                                JSONObject jsonObject1=jsonObject.getJSONObject("odds");

                                md1.setRate(jsonObject1.getString("rate"));
                                md1.setRate2(jsonObject1.getString("rate2"));
                                md1.setRate_team(jsonObject1.getString("rate_team"));
                            }
                            //Here how to add the three nested fields, rate, rate2, rate_team

                            upcomingList.add(md1);

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                adapter1.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());

            }
        });


        requestQueue.add(jsonArrayRequest);

    }



}