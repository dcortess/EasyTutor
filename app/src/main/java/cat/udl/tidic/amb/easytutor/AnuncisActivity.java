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
import cat.udl.tidic.amb.easytutor.models.AnunciLevel;
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
    private Button filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncis);
        anunciViewModel= new AnunciViewModel(this.getApplication());
        initView();
    }

    protected void initView() {


        todo = findViewById(R.id.radioButtonT);
        doy = findViewById(R.id.radioButtonD);
        busco = findViewById(R.id.radioButtonB);
        filter = findViewById(R.id.filter);
        anunciListView = findViewById(R.id.mRecycleView);

        //anunciViewModel.getAnuncis();
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (doy.isChecked()){
                    anunciViewModel.getAnuncisFiltered("D");
                }else if(busco.isChecked()){
                    anunciViewModel.getAnuncisFiltered("B");
                }else{
                    anunciViewModel.getAnuncis();
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





        initAnunciList();
    }

    private void initAnunciList(){
        anunciListView.setLayoutManager(new LinearLayoutManager(this));
        final AnunciAdapter anunciAdapter = new AnunciAdapter(new AnunciDiffCallback());
        anunciListView.setAdapter(anunciAdapter);
        anunciAdapter.setOnItemClickListener(new AnunciAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Anunci anunci) {
                Log.d(TAG, anunci.getId());
                Intent intent = new Intent(AnuncisActivity.this, AnunciActivity.class);
                intent.putExtra("id",anunci.getId());
            }
        });

        anunciViewModel.getResponseLiveDataAnuncis().observe(this, new Observer<List<Anunci>>() {
            @Override
            public void onChanged(List<Anunci> anuncis) {
                anunciAdapter.submitList(anuncis);
            }
        });
    }

    protected void refreshList(){
        anunciViewModel.getAnuncis();
    }

    @Override
    public void onResume(){
        super.onResume();
    }
}
