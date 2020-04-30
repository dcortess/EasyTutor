package cat.udl.tidic.amb.easytutor.dao;

import cat.udl.tidic.amb.easytutor.models.User;
import cat.udl.tidic.amb.easytutor.network.RetrofitClientInstance;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

public class UserDAOImpl implements IUserDAO {
    private Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();


    @Override
    public Call<ResponseBody> createTokenUser(String auth){

        return retrofit.create(IUserDAO.class).createTokenUser(auth);

    }
    @Override
    public Call<ResponseBody> deleteTokenUser(String auth){

        return retrofit.create(IUserDAO.class).deleteTokenUser(auth);

    }

    @Override
    public Call<User> getProfileUser(String auth){

        return retrofit.create(IUserDAO.class).getProfileUser(auth);

    }

}
