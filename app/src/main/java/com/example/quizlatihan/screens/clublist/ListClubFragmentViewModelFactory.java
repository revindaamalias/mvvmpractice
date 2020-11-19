package com.example.quizlatihan.screens.clublist;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.quizlatihan.models.Club;

import java.util.List;

public class ListClubFragmentViewModelFactory implements ViewModelProvider.Factory {
    private List<Club> listClub;

    public ListClubFragmentViewModelFactory(List<Club> listClub) { this.listClub = listClub; }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(ListClubFragmentViewModel.class)){
            return (T) new ListClubFragmentViewModel(listClub);
        }
        throw new IllegalArgumentException("Viewmodel Yang Diminta MainActivityViewModel");
    }
}
