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
import com.example.crex.Adapter.ModelAdapter;
import com.example.crex.Adapter.ModelAdapter2;
import com.example.crex.models.Model;
import com.example.crex.models.Model2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class Finished extends Fragment {

    public static final String URL_API_FINISHED= "https://mocki.io/v1/2389d44c-81aa-4e04-bd2e-b8c7e17572c0";

    private static final String TAG = "Finished";

    private RecyclerView recyclerView;
    private final List<Model2> getModels;
    private ModelAdapter2 modelAdapter;
    private RequestQueue mRequestQueue;

    public Finished(ArrayList<Model2> arraylist2) {
        getModels = arraylist2;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_finished, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

//        fetchJSON_data();


        modelAdapter = new ModelAdapter2(getContext(), getModels);
        recyclerView.setAdapter(modelAdapter);
        modelAdapter.notifyDataSetChanged();

    }


//    private void fetchJSON_data() {
//
//        JsonArrayRequest requestQueue = new JsonArrayRequest(Request.Method.GET, URL_API_FINISHED, null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        try {
//
//                            for (int i = 0; i < response.length(); i++) {
//
//                                JSONObject fields = response.getJSONObject(i);
//                                JSONArray matches = fields.getJSONArray("m");
//                                String club_date = fields.getString("date");
//                                getModels.add(new Model2(0,club_date));
//
//                                for(int j=0;j<matches.length();j++)
//                                {
//                                    JSONObject temp = matches.getJSONObject(j);
//
//                                    String t1 = temp.getString("t1");
//                                    String t2 = temp.getString("t2");
//                                    String t1flag = temp.getString("t1flag");
//                                    String t2flag = temp.getString("t2flag");
//                                    String match_no = temp.getString("match_no");
//                                    String score1= temp.getString("score1");
//                                    String score2= temp.getString("score2");
//                                    String overs1= temp.getString("overs1");
//                                    String overs2 = temp.getString("overs2");
//                                    String winner= temp.getString("winner");
//                                    String winnerInfo= temp.getString("result");
//
//                                        getModels.add(new Model2(1, t1, t2, t1flag, t2flag, match_no, score1, score2, overs1, overs2, winner, winnerInfo,));
//
//
//
//                                }
//
//
//                            }
//
//                            modelAdapter = new ModelAdapter2(getContext(), getModels);
//                            recyclerView.setAdapter(modelAdapter);
//                            modelAdapter.notifyDataSetChanged();
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d(TAG, "onErrorResponse: " + error.getMessage());
//            }
//        });
//        mRequestQueue.add(requestQueue);
//
//
//    }
}