package com.mysportpesa.surebetsclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.mysportpesa.surebetsclient.R;
import com.mysportpesa.surebetsclient.model.GlobalModel;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List<GlobalModel> list;

    public MyAdapter(Context context, List<GlobalModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item2, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GlobalModel globalModel = list.get(position);

        holder.leagueName.setText(globalModel.getCountry_name()+"  "+ globalModel.getLeague_name());
        holder.homeTeam.setText(globalModel.getMatch_hometeam_name());
        holder.awayTeam.setText(globalModel.getMatch_awayteam_name());
        holder.homeWin.setText("Home Win(%):" + globalModel.getProb_HW() +"%");
        holder.awayWin.setText("Away Win(%):" + globalModel.getProb_AW() +"%");
        holder.draw.setText("Draw(%):" + globalModel.getProb_D() +"%");
        holder.homeDraw.setText("Home/Draw(%):" + globalModel.getProb_HW_D() +"%");
        holder.homeAway.setText("Home/Away(%):" + globalModel.getProb_HW_AW() +"%");
        holder.awayDraw.setText("Away/Draw(%):" + globalModel.getProb_AW_D() +"%");
        holder.un25.setText("UN25%) :" + globalModel.getProb_u25() +"%");
        holder.ov25.setText("OV25%) :" + globalModel.getProb_ov25() +"%");
        holder.un15.setText("UN15%) :" + globalModel.getProb_u_1()+"%");
        holder.ov15.setText("OV15%) :" + globalModel.getProb_o_1() +"%");
        holder.un35.setText("UN35%) :" + globalModel.getProb_u_3()+"%");
        holder.ov35.setText("OV35%) :" + globalModel.getProb_o_3() +"%");
        holder.btts.setText("BTTS%) :" + globalModel.getProb_btts()+"%");
        holder.otts.setText("OTTS%) :" + globalModel.getProb_otts() +"%");
        holder.dateTime.setText(globalModel.getMatch_date()+" "+globalModel.getMatch_time());

        holder.match_status.setText("Match Status):" + globalModel.getMatch_status());
        holder.fulltime.setText("Full-time) :" + globalModel.getMatch_hometeam_score() +"   "+globalModel.getMatch_awayteam_score());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView leagueName, homeTeam, awayTeam, homeWin, homeDraw, un25, ov25, match_status, dateTime, draw, homeAway, btts, ov15, un15, awayWin, awayDraw, otts,
                ov35, un35, fulltime;

        public ViewHolder(View itemView) {
            super(itemView);

            leagueName = itemView.findViewById(R.id.leagueName);
            homeTeam = itemView.findViewById(R.id.homeTeam);
            awayTeam = itemView.findViewById(R.id.awayTeam);
            homeWin = itemView.findViewById(R.id.homeWin);
            homeDraw = itemView.findViewById(R.id.homeDraw);
            un25 = itemView.findViewById(R.id.un25);
            ov25 = itemView.findViewById(R.id.ov25);
            ov15 = itemView.findViewById(R.id.ov15);
            un15 = itemView.findViewById(R.id.un15);
            ov35 = itemView.findViewById(R.id.ov35);
            un35 = itemView.findViewById(R.id.un35);

            match_status = itemView.findViewById(R.id.match_status);
            dateTime = itemView.findViewById(R.id.dateTime);
            draw = itemView.findViewById(R.id.draw);

            homeAway = itemView.findViewById(R.id.homeAway);
            btts = itemView.findViewById(R.id.btts);
            awayWin = itemView.findViewById(R.id.awayWin);

            awayDraw = itemView.findViewById(R.id.awayDraw);
            otts = itemView.findViewById(R.id.otts);
            fulltime = itemView.findViewById(R.id.fulltime);
        }
    }
}