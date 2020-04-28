package cat.udl.tidic.amb.easytutor;
import android.app.Application;

import cat.udl.tidic.amb.easytutor.preferences.PreferencesProvider;

public class App extends Application {

    /*
     * @Jordi: No funcionen les Shared Preferences si no inicialitzeu
     * l'objecte Singleton PreferenceProvider
     * */
    @Override
    public void onCreate() {
        super.onCreate();
        PreferencesProvider.init(this);
    }
}