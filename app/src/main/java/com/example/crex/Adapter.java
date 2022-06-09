package com.example.crex;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


import static com.example.crex.model.LayoutOne;
import static com.example.crex.model.LayoutTwo;

public class Adapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<model> mData;

    public Adapter(Context mContext, List<model> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getItemViewType(int position) {

        switch (mData.get(position).getViewType()) {
            case 0:
                return model.LayoutOne;
            case 1:
                return model.LayoutTwo;
            default:
                return -1;
        }
        //return super.getItemViewType(position);
    }

    // Create classes for each layout ViewHolder.
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case LayoutOne:
                View layoutOne = LayoutInflater.from(mContext).inflate(R.layout.upcoming_list, parent, false);
                return new LayoutTwoViewHolder(layoutOne);
            case LayoutTwo:
                View layoutTwo = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_list2, parent, false);
                return new LayoutOneViewHolder(layoutTwo);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (mData.get(position).getViewType()) {
            case LayoutTwo:
                String comDate = mData.get(position).getComdate();
                Log.d("Check Date",comDate);
                LayoutOneViewHolder layoutOneViewHolder=((LayoutOneViewHolder)holder);
                SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy");
                SimpleDateFormat form1 = new SimpleDateFormat("E,dd LLLL");
                Calendar c = Calendar.getInstance();
                try {
                    c.setTime(sdf.parse(comDate));
                    layoutOneViewHolder.comdate.setText(form1.format(c.getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                break;

            case LayoutOne:
                Glide.with(mContext)
                        .load(mData.get(position).getT1im())
                        .into(((LayoutTwoViewHolder)holder).t1im);

                Glide.with(mContext)
                        .load(mData.get(position).getT2im())
                        .into(((LayoutTwoViewHolder)holder).t2im);

                //These are for the images ...to get images from the JSON and bind it ..making use of Glider
                //Please have a look why is this giving an error?????


                LayoutTwoViewHolder layoutTwoViewHolder=((LayoutTwoViewHolder)holder);


                String place = mData.get(position).getPlace();
                String team1 = mData.get(position).getTeam1();
                String team2 = mData.get(position).getTeam2();
                layoutTwoViewHolder.team1.setText(team1);
                layoutTwoViewHolder.team2.setText(team2);
                layoutTwoViewHolder.place.setText(place);




                //String date = mData.get(position).getDate();
                Long time = mData.get(position).getTime();

                SimpleDateFormat formatter = new SimpleDateFormat("KK:mm aaa");
                SimpleDateFormat form = new SimpleDateFormat("dd LLLL");
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(time);
                String dte= formatter.format(calendar.getTime());
                layoutTwoViewHolder.time.setText(dte);
                layoutTwoViewHolder.date.setText(form.format(calendar.getTime()));



//
                String rate_team = mData.get(position).getRate_team();
                String rate = mData.get(position).getRate();
                String rate2 = mData.get(position).getRate2();

                layoutTwoViewHolder.rate_team.setText("rate_team");
                layoutTwoViewHolder.rate.setText("rate");
                layoutTwoViewHolder.rate2.setText("rate2");

               /* if(rate_team.equals("")){
                    layoutTwoViewHolder.linearLayout.setVisibility(View.GONE);
                }
                else{
                    layoutTwoViewHolder.linearLayout.setVisibility(View.VISIBLE);
                    layoutTwoViewHolder.rate_team.setText(rate_team);
                    layoutTwoViewHolder.rate.setText(rate);
                    layoutTwoViewHolder.rate2.setText(rate2);

                }*/





                break;
            default:
                return;
        }









        /*holder.team1.setText(mData.get(position).getTeam1());
        holder.team2.setText(mData.get(position).getTeam2());
        holder.date.setText(mData.get(position).getDate());
        holder.time.setText(mData.get(position).getTime());

        Glide.with(mContext)
                .load(mData.get(position).getT1im())
                .into(holder.t1im);

        Glide.with(mContext)
                .load(mData.get(position).getT2im())
                .into(holder.t2im);*/


        //Using glide library to display the image
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }


public class LayoutOneViewHolder extends RecyclerView.ViewHolder {

    public TextView comdate;

    public LayoutOneViewHolder(@NonNull View itemView)
    {
        super(itemView);

        // Find the Views
        comdate = itemView.findViewById(R.id.comdate);

    }

    // method to set the views that will
    // be used further in onBindViewHolder method.
    private void setView(String comdate)
    {
        this.comdate.setText(comdate);
    }
}





//Similarly, creating class for the second layout viewholder
public class LayoutTwoViewHolder extends RecyclerView.ViewHolder {

    public ImageView t1im;
    public ImageView t2im;
    public TextView place, team1, team2, date, time, rate_team, rate, rate2;
    LinearLayout linearLayout;


    public LayoutTwoViewHolder(@NonNull View itemView)
    {
        super(itemView);
        place = itemView.findViewById(R.id.place);
        team1 = itemView.findViewById(R.id.team1);
        team2 = itemView.findViewById(R.id.team2);
        linearLayout= itemView.findViewById(R.id.linearLayout);

        date = itemView.findViewById(R.id.date);
        time = itemView.findViewById(R.id.time);
        rate_team = itemView.findViewById(R.id.rate_team);
        rate = itemView.findViewById(R.id.rate);
        rate2 = itemView.findViewById(R.id.rate2);
        t1im = itemView.findViewById(R.id.t1im);
        t2im = itemView.findViewById(R.id.t2im);

    }

    /*private void setViews(int t1im, int t2im, String place,
                          String team1, String team2, String date, String time, String rate_team, String rate, String rate2)
    {
        this.t1im.setImageResource(t1im);
        this.t2im.setImageResource(t2im);
        this.place.setText(place);
        this.team1.setText(team1);
        this.team2.setText(team2);




        this.date.setText(date);
        this.time.setText(time);

//        if(rate_team.equals("")){
//            linearLayout.setVisibility(View.GONE);
//        }
//        else{
//            linearLayout.setVisibility(View.VISIBLE);
            this.rate_team.setText("rate_team");
            this.rate.setText("rate");
            this.rate2.setText("rate2");
//        }




    }*/
}
}