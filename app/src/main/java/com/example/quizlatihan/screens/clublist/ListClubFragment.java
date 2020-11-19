package com.example.quizlatihan.screens.clublist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quizlatihan.R;
import com.example.quizlatihan.databinding.FragmentListClubBinding;
import com.example.quizlatihan.models.Club;

import java.util.ArrayList;
import java.util.List;

public class ListClubFragment extends Fragment {

    private FragmentListClubBinding binding;
    private ListClubFragmentViewModel viewModel;

    public  ListClubFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        List<Club> clubList = new ArrayList<>();
        clubList.add(new Club("Arsenal", false, R.drawable.arsenal));
        clubList.add(new Club("Manchester", false, R.drawable.manchester));
        ListClubFragmentViewModelFactory listClubFragmentViewModelFactory = new ListClubFragmentViewModelFactory(clubList);
        viewModel = new ViewModelProvider(this, listClubFragmentViewModelFactory).get(ListClubFragmentViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_club, container, false);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRvClub();
    }

    private void setupRvClub() {
        RecyclerView recyclerView = binding.rvClub;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ClubAdapter adapter = new ClubAdapter(new OnItemClubListener(){
            @Override
            public void onClubClicked(Club club) { viewModel.onClubClicked(club); }
        });
        recyclerView.setAdapter(adapter);
        viewModel.listClubLiveData().observe(getViewLifecycleOwner(), new Observer<List<Club>>() {
            @Override
            public void onChanged(List<Club> clubList) {
                adapter.setClubList(clubList);
            }
        });

        viewModel.navigateToDetail().observe(getViewLifecycleOwner(), new Observer<Club>() {
            @Override
            public void onChanged(Club club) {
                if(club!=null){
                    Toast.makeText(getContext(),"Club "+ club.getClubDescription(), Toast.LENGTH_SHORT).show();
                    NavDirections action = ListClubFragmentDirections.actionListClubFragmentToDetailFragment(club);
                    Navigation.findNavController(requireView()).navigate(action);
                    viewModel.onClubDetailNavigated();
                }
            }
        });
    }
}