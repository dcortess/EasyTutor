package cat.udl.tidic.amb.easytutor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import cat.udl.tidic.amb.easytutor.adapters.AnunciAdapter;
import cat.udl.tidic.amb.easytutor.adapters.AnunciDiffCallback;
import cat.udl.tidic.amb.easytutor.models.Anunci;
import cat.udl.tidic.amb.easytutor.viewmodel.AnunciViewModel;

public class AnuncisActivity extends AppCompatActivity {
    public static final String TAG = "AnunciActivity";
    private RecyclerView anunciListView;
    private AnunciAdapter anunciAdapter;
    protected AnunciViewModel anunciViewModel;
    private Button createAnunci;
    private RadioButton todo;
    private RadioButton doy;
    private RadioButton busco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    protected void initView() {
        todo = findViewById(R.id.radioButtonT);
        doy = findViewById(R.id.radioButtonD);
        busco = findViewById(R.id.radioButtonB);
        setContentView(R.layout.activity_anuncis);
        anunciListView = findViewById(R.id.mRecycleView);
        anunciListView.setLayoutManager(new LinearLayoutManager(this));
        //anunciListView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        anunciAdapter = new AnunciAdapter(new AnunciDiffCallback());
        anunciListView.setAdapter(anunciAdapter);

        anunciAdapter.setOnItemClickListener(new AnunciAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Anunci anunci) {
                Log.d(TAG, anunci.getId());
                Intent intent = new Intent(AnuncisActivity.this, AnunciActivity.class);
                intent.putExtra("id",anunci.getId());
            }
        });
        anunciViewModel= new AnunciViewModel(this.getApplication());
        anunciViewModel.getAnuncis();
        anunciViewModel.getResponseLiveDataAnuncis().observe(this, new Observer<List<Anunci>>() {
            @Override
            public void onChanged(List<Anunci> anuncis) {
                //seria millor filtrar amb una crida a la API, queda pendent
                if(doy.isChecked()){
                    List<Anunci> doyList = new ArrayList<Anunci>();

                    for(int i = 0; i < anuncis.size(); i++){
                        if(anuncis.get(i).getType().equals("D")){
                            doyList.add(anuncis.get(i));
                        }
                    }
                    anunciAdapter.submitList(doyList);
                }
                else if (busco.isChecked()){
                    List<Anunci> buscoList = new ArrayList<Anunci>();

                    for(int i = 0; i < anuncis.size(); i++){
                        if(anuncis.get(i).getType().equals("B")){
                            buscoList.add(anuncis.get(i));
                        }
                    }
                    anunciAdapter.submitList(buscoList);
                }
                else{
                    anunciAdapter.submitList(anuncis);
                }

            }
        });

        createAnunci = findViewById(R.id.buttonAddAnunci);
        createAnunci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnuncisActivity.this, AddOfferActivity.class);
                startActivity(intent);
            }
        });



        refreshList();
    }

    protected void refreshList(){
        anunciViewModel.getAnuncis();
    }

    @Override
    public void onResume(){
        super.onResume();
    }
}
