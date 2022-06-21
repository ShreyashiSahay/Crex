package com.example.crex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.example.crex.Adapter.ModelAdapter;
import com.example.crex.models.Model;
import com.example.crex.models.Model2;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    //two things inside our main activity; tab layout and vioew pager
    //So we need to declare both of them

    private static final String TAG = "MainActivity";

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private final ArrayList<Model> arrayList = new ArrayList<>();
    private final ArrayList<Model2> arraylist2 = new ArrayList<>();
    VPAdapter vpAdapter;
    DatabaseReference usersRef;
    DatabaseReference rootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);


        rootRef = FirebaseDatabase.getInstance().getReference();
        usersRef = rootRef.child("matches");

        tabLayout.addTab(tabLayout.newTab().setText("Upcoming  "));
        tabLayout.addTab(tabLayout.newTab().setText("  Finished  "));


        getData();


    }


    private void getData() {


        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrayList.clear();
                arraylist2.clear();

                TreeMap<String, List<Model2>> hashMapFinished = new TreeMap<>();
                TreeMap<String, List<Model>> hashMapUpcoming = new TreeMap<>();


                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    String uid = ds.getKey();
                    if (ds.hasChild(Groupin.WINNER)) {
                        String club_date = ds.child(Groupin.CLUBDATE).getValue().toString();
                        ;

                        String t1 = ds.child(Groupin.TEAM1).getValue().toString();
                        String t2 = ds.child(Groupin.TEAM2).getValue().toString();
                        String date = ds.child(Groupin.CLUBDATE).getValue().toString();
                        String match_no = ds.child(Groupin.MATCHNO).getValue().toString();
                        String time_stamp = ds.child(Groupin.TIME).getValue().toString();
                        String t1_flag = ds.child(Groupin.IMAGE1).getValue().toString();

                        String t2_flag = ds.child(Groupin.IMAGE2).getValue().toString();

                        String overs1 = ds.child(Groupin.OVERS1).getValue().toString();
                        String overs2 = ds.child(Groupin.OVERS2).getValue().toString();
                        String winner = ds.child(Groupin.WINNER).getValue().toString();
                        String result = ds.child(Groupin.RESULT).getValue().toString();
                        String score1 = ds.child(Groupin.SCORE1).getValue().toString();
                        String score2 = ds.child(Groupin.SCORE2).getValue().toString();
                        //Log.d(TAG, "onDataChange: "+score1+" "+score2);

                        Model2 tempFinishedModels = new Model2(1, t1, t2, t1_flag, t2_flag, match_no, score1, score2, overs1, overs2, winner, result);

                        //Log.d(TAG, "onDataChange: "+get_day_month_day(club_date));

                        if (hashMapFinished.get(club_date) == null) {
                            hashMapFinished.put(club_date, new ArrayList<>());
                        }
                        hashMapFinished.get(club_date).add(tempFinishedModels);
                    } else {

                        String club_date = ds.child(Groupin.CLUBDATE).getValue().toString();
                        String t1 = ds.child(Groupin.TEAM1).getValue().toString();
                        String t2 = ds.child(Groupin.TEAM2).getValue().toString();
                        String date = ds.child(Groupin.CLUBDATE).getValue().toString();
                        String match_no = ds.child(Groupin.MATCHNO).getValue().toString();
                        String time_stamp = ds.child(Groupin.TIME).getValue().toString();
                        String t1_flag = ds.child(Groupin.IMAGE1).getValue().toString();
                        String t2_flag = ds.child(Groupin.IMAGE2).getValue().toString();
                        if (ds.hasChild("odds")) {
//                            Log.d(TAG, "onDataChange: odds");
                            String rate1 = ds.child(Groupin.ODDS).child(Groupin.RATE).getValue().toString();
                            String rate2 = ds.child(Groupin.ODDS).child(Groupin.RATE2).getValue().toString();
                            String rate_team = ds.child(Groupin.ODDS).child(Groupin.RATE_TEAM).getValue().toString();

                            Model tempUpcoming = (new Model(1, 1, t1, t2, t1_flag, t2_flag, match_no, (club_date), (time_stamp), rate1, rate2, rate_team));
                            if (hashMapUpcoming.get(club_date) == null) {
                                hashMapUpcoming.put(club_date, new ArrayList<>());
                            }
                            hashMapUpcoming.get(club_date).add(tempUpcoming);
                        } else {
//                            Log.d(TAG, "onDataChange: kxmsx");
                            Model tempUpcoming = (new Model(1, 0, t1, t2, t1_flag, t2_flag, match_no, (club_date), (time_stamp)));
                            if (hashMapUpcoming.get(club_date) == null) {
                                hashMapUpcoming.put(club_date, new ArrayList<>());
                            }
                            hashMapUpcoming.get(club_date).add(tempUpcoming);
                        }


                    }
                }

                Log.d(TAG, "onDataChange: "+hashMapUpcoming.size());

                for (String key : hashMapUpcoming.keySet()) {
                    List<Model> newModels = hashMapUpcoming.get(key);
                    Collections.sort(newModels, new Comparator<Model>() {
                        @Override
                        public int compare(Model o1, Model o2) {
                            return o1.getTime_stamp().compareTo(o2.getTime_stamp());
                        }
                    });
                    Log.d(TAG, "onDataChange: "+key);
                    arrayList.add(new Model(0, key));
                    //                        Log.d(TAG, "onDataChange: "+newModels.get(i).getT1Flag());
                    arrayList.addAll(newModels);
                }

                for (String key : hashMapFinished.keySet()) {
                    List<Model2> newModels = hashMapFinished.get(key);
                    arraylist2.add(new Model2(0, key));
                    arraylist2.addAll(newModels);
                }

                Log.d(TAG, "onDataChange: "+arrayList.size()+" "+arraylist2.size());

                init();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Log.d(TAG, databaseError.getMessage());
            }
        };
        usersRef.addValueEventListener(valueEventListener);
    }

    public void init() {
        vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        vpAdapter.addFragment(new Upcoming(arrayList), "Upcoming  ");
        vpAdapter.addFragment(new Finished(arraylist2), "Finished");

        viewPager.setAdapter(vpAdapter);

    }

}

