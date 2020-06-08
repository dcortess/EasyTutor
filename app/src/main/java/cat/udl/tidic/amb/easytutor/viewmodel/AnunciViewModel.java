package cat.udl.tidic.amb.easytutor.viewmodel;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import cat.udl.tidic.amb.easytutor.models.Anunci;
import cat.udl.tidic.amb.easytutor.preferences.PreferencesProvider;
import cat.udl.tidic.amb.easytutor.services.AnunciServiceImpl;


public class AnunciViewModel extends AndroidViewModel {

    private AnunciServiceImpl repository;
    private SharedPreferences mPreferences;
    private MutableLiveData<List<Anunci>> responseLiveDataAnuncis;
    private MutableLiveData<Anunci> responseLiveDataAnunci;

    public AnunciViewModel(@NonNull Application application){
        super(application);

        this.repository = new AnunciServiceImpl();
        this.responseLiveDataAnuncis = repository.getResponseLiveDataAnuncis();
        this.responseLiveDataAnunci = repository.getResponseLiveDataAnunci();

        this.mPreferences = PreferencesProvider.providePreferences();

    }

    public void getAnuncis(){
        String header = this.mPreferences.getString("token","");
        repository.getAnuncis(header);
    }

    public void getAnuncisFiltered(String type){
        String header = this.mPreferences.getString("token", "");
        repository.getAnuncisFiltered(header,type);
    }

    public void getAnunci(String id){
        String header = this.mPreferences.getString("token","");
        repository.getAnunci(header, id);
    }

    public MutableLiveData<List<Anunci>> getResponseLiveDataAnuncis(){return this.responseLiveDataAnuncis;}
    public MutableLiveData<Anunci> getResponseLiveDataAnunci(){return this.responseLiveDataAnunci;}
}
