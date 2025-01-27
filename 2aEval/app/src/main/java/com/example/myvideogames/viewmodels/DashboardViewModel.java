package com.example.myvideogames.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.myvideogames.models.Game;
import com.example.myvideogames.repositories.DashboardRepository;
import java.util.List;

public class DashboardViewModel extends ViewModel {

    private DashboardRepository dashboardRepository;
    private MutableLiveData<List<Game>> gamesLiveData;
    private MutableLiveData<Exception> errorLiveData;

    public DashboardViewModel() {
        dashboardRepository = new DashboardRepository();
        gamesLiveData = new MutableLiveData<>();
        errorLiveData = new MutableLiveData<>();
    }

    public void loadGames() {
        dashboardRepository.loadGames(new DashboardRepository.OnDataLoadedListener() {
            @Override
            public void onDataLoaded(List<Game> games) {
                gamesLiveData.setValue(games);
            }

            @Override
            public void onDataFailed(Exception e) {
                errorLiveData.setValue(e);
            }
        });
    }

    public MutableLiveData<List<Game>> getGamesLiveData() {
        return gamesLiveData;
    }

    public MutableLiveData<Exception> getErrorLiveData() {
        return errorLiveData;
    }
}
