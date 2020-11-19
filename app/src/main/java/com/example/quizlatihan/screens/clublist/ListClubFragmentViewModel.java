package com.example.quizlatihan.screens.clublist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizlatihan.models.Club;

import java.util.List;

public class ListClubFragmentViewModel extends ViewModel {
    private MutableLiveData<List<Club>> listClubMutableLiveData = new MutableLiveData<>();
    private List<Club> listClub = null;

    public ListClubFragmentViewModel(List<Club> listClub) {
        this.listClub=listClub;
        this.listClubMutableLiveData.setValue(listClub);
    }

    public LiveData<List<Club>> listClubLiveData () {
        return listClubMutableLiveData;
    }

    private MutableLiveData<Club> _navigateToDetail = new MutableLiveData<>();
    public LiveData<Club> navigateToDetail(){ return  _navigateToDetail; }
    public void onClubClicked(Club club) { _navigateToDetail.setValue(club);}
    public void onClubDetailNavigated() { _navigateToDetail.setValue(null);}
}
