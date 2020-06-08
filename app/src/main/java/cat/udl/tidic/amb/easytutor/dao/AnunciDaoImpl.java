package cat.udl.tidic.amb.easytutor.dao;

import java.util.List;

import cat.udl.tidic.amb.easytutor.models.Anunci;
import cat.udl.tidic.amb.easytutor.network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Retrofit;

public class AnunciDaoImpl implements IAnunciDAO {

    private Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();

    @Override
    public Call<List<Anunci>> getAnuncis(String auth) { return retrofit.create(IAnunciDAO.class).getAnuncis(auth);}

    @Override
    public Call<Anunci> getAnunci(String auth, String id) { return retrofit.create(IAnunciDAO.class).getAnunci(auth,id);}

    @Override
    public Call<List<Anunci>> getAnuncisFiltered(String auth, String type){return retrofit.create(IAnunciDAO.class).getAnuncisFiltered(auth, type);}

}
