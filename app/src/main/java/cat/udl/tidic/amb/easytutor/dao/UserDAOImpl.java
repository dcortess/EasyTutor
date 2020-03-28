package cat.udl.tidic.amb.easytutor.dao;

import android.util.Log;

import cat.udl.tidic.amb.easytutor.network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserDAOImpl implements IUserDAO {
    Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();


    @Override
    public Call<Void> createTokenUser(String auth) {
        Call<Void> call = retrofit.create(IUserDAO.class).createTokenUser(auth);
        call.enqueue(new Callback<Void>() {
            //trec les respostes pel log ja que vaig tenir problemes a l'hora d'agafar el context actual per fer servir un Toast
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("UserDAO", "RESPONSE OK");
                Log.d("UserDAO", "" + response.code());
            }


            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("UserDAO", "BAD RESPONSE");
                Log.d("UserDAO", t.getMessage());
            }
        });
        return call;
    }

}
