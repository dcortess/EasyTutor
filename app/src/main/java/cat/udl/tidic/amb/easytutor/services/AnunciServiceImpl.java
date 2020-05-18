package cat.udl.tidic.amb.easytutor.services;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.List;

import cat.udl.tidic.amb.easytutor.dao.AnunciDaoImpl;
import cat.udl.tidic.amb.easytutor.dao.IAnunciDAO;
import cat.udl.tidic.amb.easytutor.models.Anunci;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnunciServiceImpl implements AnunciServiceI {

    private IAnunciDAO anunciDAO;
    private String TAG = "AnunciServiceImpl";
    public final MutableLiveData<List<Anunci>> mResponseAnuncis;
    public final MutableLiveData<Anunci> mResponseAnunci;

    public AnunciServiceImpl() {
        anunciDAO = new AnunciDaoImpl();
        mResponseAnuncis = new MutableLiveData<>();
        mResponseAnunci = new MutableLiveData<>();
    }

    public MutableLiveData<List<Anunci>> getResponseLiveDataAnuncis() {
        return mResponseAnuncis;
    }
    public MutableLiveData<Anunci> getResponseLiveDataAnunci(){return mResponseAnunci;}

    @Override
    public void getAnuncis(String auth) {

        anunciDAO.getAnuncis(auth).enqueue(new Callback<List<Anunci>>() {


            @Override
            public void onResponse(Call<List<Anunci>> call, Response<List<Anunci>> response) {

                int code = response.code();
                Log.d(TAG,  "getEvents() -> ha rebut del backend un codi:  " + code);

                if (code == 200 ){
                    List<Anunci> anuncis = response.body();
                    Log.d(TAG,  "getAnuncis() -> ha rebut una llista de mida: "+ anuncis.size());
                    mResponseAnuncis.setValue(anuncis);
                }
                else{
                    try {
                        String error_msg = "Error: " + response.errorBody().string();
                        Log.d(TAG,  "getAnuncis() -> ha rebut l'error:  " + error_msg);
                        mResponseAnuncis.setValue(null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Anunci>> call, Throwable t) {
                String error_msg = "Error: " + t.getMessage().toString();
                Log.d(TAG,  "getAnuncis() onFailure() -> ha rebut el missatge:  " + error_msg);
                mResponseAnuncis.setValue(null);
            }
        });

    }

    @Override
    public void getAnunci(String auth, String id) {

        anunciDAO.getAnunci(auth, id).enqueue(new Callback<Anunci>() {


            @Override
            public void onResponse(Call<Anunci> call, Response<Anunci> response) {

                int code = response.code();
                Log.d(TAG,  "getEvents() -> ha rebut del backend un codi:  " + code);

                if (code == 200 ){
                    Anunci anunci = response.body();
                    mResponseAnunci.setValue(anunci);
                }
                else{
                    try {
                        String error_msg = "Error: " + response.errorBody().string();
                        Log.d(TAG,  "getAnunci() -> ha rebut l'error:  " + error_msg);
                        mResponseAnunci.setValue(null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<Anunci> call, Throwable t) {
                String error_msg = "Error: " + t.getMessage().toString();
                Log.d(TAG,  "getAnunci() onFailure() -> ha rebut el missatge:  " + error_msg);
                mResponseAnunci.setValue(null);
            }
        });

    }


}
