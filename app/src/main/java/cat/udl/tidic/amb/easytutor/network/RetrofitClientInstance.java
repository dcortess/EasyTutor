package cat.udl.tidic.amb.easytutor.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;

    /*
     * @Jordi: Millor no tenir hardcodejat, es pot llegir de la configuració, gradle.build
     * */
    private static final String BASE_URL = "http://10.0.2.2:8000";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
