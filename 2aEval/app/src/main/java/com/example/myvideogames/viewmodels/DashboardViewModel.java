package com.example.myvideogames.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.myvideogames.models.Game;
import com.example.myvideogames.repositories.DashboardRepository;
import java.util.List;

public class DashboardViewModel extends ViewModel {

    // Instancia del repositorio que se encargará de la carga de los datos
    private DashboardRepository dashboardRepository;

    // LiveData para almacenar la lista de juegos que se observa desde la UI
    private MutableLiveData<List<Game>> gamesLiveData;

    // LiveData para almacenar los errores que se observan desde la UI
    private MutableLiveData<Exception> errorLiveData;

    // Constructor del ViewModel
    public DashboardViewModel() {
        // Inicialización del repositorio que maneja la carga de datos
        dashboardRepository = new DashboardRepository();

        // Inicialización de los LiveData
        gamesLiveData = new MutableLiveData<>();
        errorLiveData = new MutableLiveData<>();
    }

    // Método para cargar los juegos desde el repositorio
    public void loadGames() {
        // Llamada al repositorio para cargar los juegos
        dashboardRepository.loadGames(new DashboardRepository.OnDataLoadedListener() {
            // Callback cuando los datos son cargados con éxito
            @Override
            public void onDataLoaded(List<Game> games) {
                // Se actualiza el LiveData con la lista de juegos
                gamesLiveData.setValue(games);
            }

            // Callback cuando ocurre un error en la carga de los datos
            @Override
            public void onDataFailed(Exception e) {
                // Se actualiza el LiveData con el error ocurrido
                errorLiveData.setValue(e);
            }
        });
    }

    // Método para obtener el LiveData que contiene la lista de juegos
    public MutableLiveData<List<Game>> getGamesLiveData() {
        return gamesLiveData;
    }

    // Método para obtener el LiveData que contiene el error (si existe)
    public MutableLiveData<Exception> getErrorLiveData() {
        return errorLiveData;
    }
}
