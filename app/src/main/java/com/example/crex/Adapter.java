package com.example.crexproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.crexproject.R;
import com.example.crexproject.models.Model;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ViewHolder> {

    private final List<Model> getModels;

    public static final int LayoutOne = 0;
    public static final int LayoutTwo = 1;
    public static final int LayoutThree = 2;


    public ModelAdapter(Context context, List<Model> getModels) {
        this.getModels = getModels;
    }

    @Override
    public int getItemViewType(int position)
    {
        if(position==getModels.size())
            return LayoutThree;
        else if (getModels.get(position).getViewType() == 0) {
            return LayoutOne;
        } else if (getModels.get(position).getViewType() == 1) {
            return LayoutTwo;
        }
        return -1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==LayoutOne)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_club_date,parent,false);
            return new ModelAdapter.ViewHolder(view);
        }
        else if(viewType==LayoutTwo)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_details_temp,parent,false);
            return new ModelAdapter.ViewHolder(view);
        }
        else
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_finished_matches,parent,false);
            return new ModelAdapter.ViewHolder(view);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(position==getModels.size())
        {
            holder.tv_upcoming_finished.setText("All Upcoming Matches");
            holder.cl_layout.setOnClickListener(v -> {
//                Log.d("PressedC", "onClick pressed ");
                Toast.makeText(v.getContext(), "Open All Upcoming Matches",Toast.LENGTH_SHORT).show();
            });
        }
        else if(getModels.get(position).getViewType()==LayoutOne)
        {
            String date_temp = null;
            try {
                date_temp = get_day_month_day(getModels.get(position).getClub_date());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            holder.tv_matchDate.setText(date_temp);
        }
        else
        {
            holder.tv_content.setText(getModels.get(position).getMatch_no()+" at Sharjah International Ground");
            holder.tv_team1_name.setText(getModels.get(position).getTeam1());
            holder.tv_team2_name.setText(getModels.get(position).getTeam2());
            String time_tag = (getModels.get(position).getTime_stamp());

            try {
                if(check_Within_three_Hours(time_tag))
                {
                    holder.tv_time_tagWon.setText("Strating in:");
                    long temp_time = Long.parseLong(getModels.get(position).getMatch_date());

                    CountDownTimer countDownTimer = new CountDownTimer(temp_time, 1000) {
                        public void onTick(long millisUntilFinished) {
                            //Convert milliseconds into hour,minute and seconds
                            @SuppressLint("DefaultLocale") String hms = String.format("%02d:%02d:%02d",
                                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                            holder.tv_date_run.setText(hms);//set text
                        }

                        public void onFinish() {
                            holder.tv_date_run.setText("TIME'S UP!!"); //On finish change timer text
                        }
                    }.start();

                }
                else
                {
                    holder.tv_time_tagWon.setText(((getModels.get(position).getTime_stamp())));
                    holder.tv_date_run.setText((getModels.get(position).getMatch_date()));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
//            holder.iv_team1.setImageResource(android.R.color.transparent);
//            holder.iv_team2.setImageResource(android.R.color.transparent);

            Glide.with(holder.iv_team1.getContext()).load(getModels.get(position).getT1Flag()).into(holder.iv_team1);
            Glide.with(holder.iv_team2.getContext()).load(getModels.get(position).getT2Flag()).into(holder.iv_team2);

//            holder.iv_team1.setImageResource(0);
//
//            Glide.with(holder.iv_team1.getContext())
//                    .load(getModels.get(position).getT1Flag())
//                    .centerCrop()
//                .placeholder(holder.iv_team1.getDrawable() != null ? holder.iv_team1.getDrawable() : null)
//                .into(holder.iv_team1);
//
//            holder.iv_team2.setImageResource(0);
//            Glide.with(holder.iv_team2.getContext())
//                    .load(getModels.get(position).getT2Flag())
//                    .centerCrop()
//                    .placeholder(holder.iv_team2.getDrawable() != null ? holder.iv_team2.getDrawable() : null)
//                    .into(holder.iv_team2);

            if(getModels.get(position).getOdds()==0)
            {
                holder.ll_horizontal_bar.setVisibility(View.GONE);
                holder.rate.setVisibility(View.GONE);
                holder.rate2.setVisibility(View.GONE);
                holder.rate_team.setVisibility(View.GONE);
            }
            else
            {
                holder.ll_horizontal_bar.setVisibility(View.VISIBLE);
                holder.rate.setVisibility(View.VISIBLE);
                holder.rate2.setVisibility(View.VISIBLE);
                holder.rate_team.setVisibility(View.VISIBLE);

                holder.rate.setText(getModels.get(position).getOdds_rate());
                holder.rate2.setText(getModels.get(position).getOdds_rate2());
                holder.rate_team.setText(getModels.get(position).getRate_team());
            }
        }



    }


    private boolean check_Within_three_Hours(String time_tag) throws ParseException {

        DateFormat formatter = new SimpleDateFormat("hh:mm:ss a");
        Date date = (Date)formatter.parse(time_tag);
        final long DAY = 3 * 60 * 60 * 1000;
        return date.getTime() > System.currentTimeMillis() - DAY;
    }

    public static String get_day_month_day(String dateStr) throws ParseException {
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd MMMM");
        SimpleDateFormat TIME_FORMAT = new SimpleDateFormat(" 'at' h:mm aa");


        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj = curFormater.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateObj);

        DateTime startDate = new DateTime(calendar.getTimeInMillis());
        DateTime today = new DateTime();
        int days = Days.daysBetween(today.withTimeAtStartOfDay(), startDate.withTimeAtStartOfDay()).getDays();

        String date;
        switch (days) {
            case -1: date = "Yesterday"; break;
            case 0: date = "Today"; break;
            case 1: date = "Tomorrow"; break;
            default: date = DATE_FORMAT.format(calendar.getTime()); break;
        }

        String day;
        switch (calendar.get(Calendar.DAY_OF_MONTH))
        {
            case 1 :
                day = "Sunday";
                break;
            case 2 :
                day = "Monday";
                break;
            case 3 :
                day = "Tuesday";
                break;
            case 4 :
                day = "Wednesday";
                break;
            case 5 :
                day = "Thursday";
                break;
            case 6 :
                day = "Friday";
                break;
            case 7 :
                day = "Saturday";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + calendar.get(Calendar.DAY_OF_MONTH));
        }
        return day+", "+date;
    }

    @Override
    public int getItemCount() {
        return getModels.size()+1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_matchDate,tv_content,tv_team1_name,tv_team2_name,tv_time_tagWon,tv_date_run,rate,rate2,rate_team;
        ImageView iv_team1,iv_team2;
        TextView ll_horizontal_bar,odds;
        TextView tv_upcoming_finished;
        ConstraintLayout cl_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_team1 = itemView.findViewById(R.id.iv_team1);
            iv_team2 = itemView.findViewById(R.id.iv_team2);

            tv_matchDate = itemView.findViewById(R.id.tv_matchDate);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_team1_name = itemView.findViewById(R.id.tv_team1_name);
            tv_team2_name = itemView.findViewById(R.id.tv_team2_name);
            tv_time_tagWon = itemView.findViewById(R.id.tv_time_tagWon);
            tv_date_run = itemView.findViewById(R.id.tv_date_run);

            rate = itemView.findViewById(R.id.rate1);
            rate2 = itemView.findViewById(R.id.rate2);
            rate_team = itemView.findViewById(R.id.rate_team);

            ll_horizontal_bar = itemView.findViewById(R.id.tv_horizontal_bar);
//            odds = itemView.findViewById(R.id.odds);

            tv_upcoming_finished = itemView.findViewById(R.id.tv_upcoming_finished);

            cl_layout = itemView.findViewById(R.id.cl_layout);



        }

    }




}