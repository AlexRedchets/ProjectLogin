package com.alexredchets.projectlogin.ui.team;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexredchets.projectlogin.R;
import com.alexredchets.projectlogin.model.Player;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    private List<Player> response;
    private Context context;
    private String TAG = TeamAdapter.class.getSimpleName();
    private ClickListener clickListener;

    public TeamAdapter(Context context, ClickListener clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    public void updateAdapter(List<Player> response){
        this.response = response;
        notifyDataSetChanged();
        Log.i(TAG, "Adapter is updated");
    }

    @Override
    public int getItemCount() {
        return response != null ? response.size() : 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.player_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Player currentData =  response.get(position);

        holder.playerName.setText(currentData.getName());
        Picasso.with(context).load(currentData.getImageUrl()).into(holder.playerImage);

    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.player_name)
        TextView playerName;
        @BindView(R.id.player_image)
        ImageView playerImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(response.get(getAdapterPosition()));
        }
    }

    public interface ClickListener {

        void onClick(Player player);
    }


}
