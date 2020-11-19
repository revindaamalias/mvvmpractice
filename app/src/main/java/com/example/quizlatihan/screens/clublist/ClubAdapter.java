package com.example.quizlatihan.screens.clublist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizlatihan.databinding.ItemClubBinding;
import com.example.quizlatihan.models.Club;

import java.util.List;

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.ClubViewHolder> {
    private List<Club> clubList;
    private OnItemClubListener itemClubListener;

    public ClubAdapter() {

    }

    public ClubAdapter(List<Club> clubList) {
        this.clubList = clubList;
    }

    public ClubAdapter(OnItemClubListener itemClubListener) {
        this.itemClubListener = itemClubListener;
    }

    public ClubAdapter(List<Club> clubList, OnItemClubListener itemClubListener) {
        this.clubList = clubList;
        this.itemClubListener = itemClubListener;
    }

    public void setClubList(List<Club> clubList) {
        this.clubList = clubList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ClubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemClubBinding itemClubBinding = ItemClubBinding.inflate(layoutInflater,parent,false);
        return  new ClubViewHolder(itemClubBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ClubViewHolder holder, int position) {
        Club club = clubList.get(position);
        holder.bind(club, itemClubListener);
    }

    @Override
    public int getItemCount() {
        if(clubList!=null){
            return clubList.size();
        }else {
            return 0;
        }
    }

    public  class  ClubViewHolder extends RecyclerView.ViewHolder {
        private ItemClubBinding binding;

        public ClubViewHolder(ItemClubBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Club club, OnItemClubListener itemClubListener){
            binding.setClub(club);
            binding.setClickListener(itemClubListener);
            binding.executePendingBindings();
        }
    }
}
