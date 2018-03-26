package com.demo.gst.networkspeedtest;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by steffi on 23/3/18.
 */

public class NetworkAdapter extends RecyclerView.Adapter<NetworkAdapter.NetworkViewHolder> {
    Activity activity;
    ArrayList<NetworkData> dataList;

    public NetworkAdapter(Activity activity, ArrayList<NetworkData> dataList){
        this.activity = activity;
        this.dataList = dataList;
    }


    @Override
    public NetworkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_network_data,parent,false);
        NetworkViewHolder vh = new NetworkViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(NetworkViewHolder holder, int position) {

        holder.time.setText(dataList.get(position).getDateTime());
        holder.network.setText(dataList.get(position).getNetworkType());
        holder.download.setText(dataList.get(position).getDownloadSpeed());
        holder.upload.setText(dataList.get(position).getUploadSpeed());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class NetworkViewHolder extends RecyclerView.ViewHolder{

        private TextView time, network,download,upload;

        public NetworkViewHolder(View v) {
            super(v);

            time = (TextView) v.findViewById(R.id.date_time_tv);
            network = (TextView) v.findViewById(R.id.network_type_tv);
            download = (TextView) v.findViewById(R.id.download_speed);
            upload = (TextView) v.findViewById(R.id.upload_speed);
        }
    }
}
