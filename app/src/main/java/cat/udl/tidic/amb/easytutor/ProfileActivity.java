package cat.udl.tidic.amb.easytutor;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import cat.udl.tidic.amb.easytutor.models.User;
import cat.udl.tidic.amb.easytutor.preferences.PreferencesProvider;
import cat.udl.tidic.amb.easytutor.viewmodel.UserViewModel;

public class ProfileActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private SharedPreferences mPreferences;
    private TextView name;
    private TextView username;
    private TextView email;
    private TextView phone;
    private TextView gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mPreferences = PreferencesProvider.providePreferences();
        userViewModel = new UserViewModel(getApplication());

        name = findViewById(R.id.editText_nombre);
        username = findViewById(R.id.editText_username);
        email = findViewById(R.id.editText_email);
        phone = findViewById(R.id.editText_telefono);
        gender = findViewById(R.id.editText_genero);

        userViewModel.getResponseLiveDataUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User u) {
                String completeName = u.getUsername() + " " + u.getSurname();
                name.setText(completeName);
                username.setText(u.getSurname());
                email.setText(u.getEmail());
                phone.setText(u.getPhone());
                gender.setText(u.getGender());
            }
        });
    }
}
