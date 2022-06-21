package com.example.crex;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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
import com.example.crex.R;
import com.example.crex.models.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Upcoming extends Fragment {


    public static final String URL_API_UPCOMING = "https://mocki.io/v1/5ce5957e-eb98-46e0-a463-e59392856f68";
    private static final String TAG = "Upcoming";

    private RecyclerView recyclerView;
    private final List<Model> getModels;
    private ModelAdapter modelAdapter;
    private RequestQueue mRequestQueue;


    //These two are declared to implement timer functions
    private Timer mTimer1;
    private TimerTask mTt1;

    public Upcoming(ArrayList<Model> arrayList) {
        getModels = arrayList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_upcoming,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        //fetchJSON_data();

        modelAdapter = new ModelAdapter(getContext(), getModels);
        recyclerView.setAdapter(modelAdapter);

        mTimer1 = new Timer();
        mTt1 = new TimerTask() {
            public void run() {
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        modelAdapter.notifyDataSetChanged();
                    }
                });
            }
        };

        mTimer1.schedule(mTt1, 1, 5000);



    }

    private void fetchJSON_data() {

        JsonArrayRequest requestQueue = new JsonArrayRequest(Request.Method.GET, URL_API_UPCOMING, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            for (int i = 0; i < response.length(); i++) {

                                JSONObject fields = response.getJSONObject(i);
                                JSONArray matches = fields.getJSONArray("m");
                                String club_date = fields.getString("date");
                                getModels.add(new Model(0,club_date));

                                for(int j=0;j<matches.length();j++)
                                {
                                    JSONObject temp = matches.getJSONObject(j);

                                    String t1 = temp.getString("t1");
                                    String t2 = temp.getString("t2");
                                    String t1flag = temp.getString("t1flag");
                                    String t2flag = temp.getString("t2flag");
                                    String match_no = temp.getString("match_no");
                                    String date = temp.getString("date");
                                    String time_stamp = temp.getString("t");

                                    if(temp.has("odds"))
                                    {
                                        JSONObject odds = temp.getJSONObject("odds");
                                        String rate = odds.getString("rate");
                                        String rate2 = odds.getString("rate2");
                                        String rate_team = odds.getString("rate_team");
                                        Log.d(TAG, "onResponse: "+rate+" "+rate2+" "+rate_team);
                                        getModels.add(new Model(1, 1, t1, t2, t1flag, t2flag, match_no, getDate(date), time_in_AM_PM(Long.parseLong((time_stamp))), rate, rate2, rate_team));
                                    }
                                    else
                                    {
                                        getModels.add(new Model(1, 0, t1, t2, t1flag, t2flag, match_no, getDate(date), time_in_AM_PM(Long.parseLong((time_stamp)))));
                                    }
                                }


                            }

                            modelAdapter = new ModelAdapter(getContext(), getModels);
                            recyclerView.setAdapter(modelAdapter);
                            modelAdapter.notifyDataSetChanged();

                        } catch (JSONException | ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error.getMessage());
            }
        });
        mRequestQueue.add(requestQueue);


    }


    private String getDate(String date) throws ParseException {

        SimpleDateFormat df = new SimpleDateFormat("MM/DD/yyyy");

        Date readDate = df.parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(readDate.getTime());

        String []month = {"Jan","Feb","Mar","April","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

        return ("" +Calendar.DAY_OF_MONTH+ " " + month[Calendar.MONTH]);
    }

    String time_in_AM_PM(long mili)
    {
        Date dt = new Date(mili);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
        String time1 = sdf.format(dt);
        return time1;
    }

}