package com.example.myvideogames.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.example.myvideogames.models.Game;
import com.example.myvideogames.repositories.DashboardRepository;
import com.example.myvideogames.utils.FavoritesManager;
import java.util.ArrayList;
import java.util.List;

public class DashboardViewModel extends AndroidViewModel {

    // Instancia del repositorio que se encargará de la carga de los datos
    private DashboardRepository dashboardRepository;

    // LiveData para almacenar la lista de juegos que se observa desde la UI
    private MutableLiveData<List<Game>> gamesLiveData;

    // LiveData para almacenar los errores que se observan desde la UI
    private MutableLiveData<Exception> errorLiveData;

    // Gestor de favoritos para filtrar los juegos
    private FavoritesManager favoritesManager;

    // Constructor del ViewModel
    public DashboardViewModel(Application application) {
        super(application);
        // Inicialización del repositorio que maneja la carga de datos
        dashboardRepository = new DashboardRepository();

        // Inicialización de los LiveData
        gamesLiveData = new MutableLiveData<>();
        errorLiveData = new MutableLiveData<>();

        // Inicialización del FavoritesManager
        favoritesManager = new FavoritesManager(application);
    }

    // Método para cargar los juegos desde el repositorio, filtrando los que están en favoritos
    public void loadGames() {
        dashboardRepository.loadGames(new DashboardRepository.OnDataLoadedListener() {
            @Override
            public void onDataLoaded(List<Game> allGames) {
                // Obtener la lista de juegos favoritos
                List<Game> favoriteGames = favoritesManager.getFavorites();
                // Filtrar juegos que NO están en favoritos
                List<Game> filteredGames = new ArrayList<>();
                for (Game game : allGames) {
                    if (!favoriteGames.contains(game)) {
                        filteredGames.add(game);
                    }
                }
                gamesLiveData.setValue(filteredGames);
            }

            @Override
            public void onDataFailed(Exception e) {
                errorLiveData.setValue(e);
            }
        });
    }

    // Método para obtener el LiveData de juegos
    public MutableLiveData<List<Game>> getGamesLiveData() {
        return gamesLiveData;
    }

    // Método para obtener el LiveData de errores
    public MutableLiveData<Exception> getErrorLiveData() {
        return errorLiveData;
    }
}
