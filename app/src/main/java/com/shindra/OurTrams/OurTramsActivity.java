package com.shindra.OurTrams;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.LoadingScreen;
import com.shindra.MyViewModel;
import com.shindra.R;
import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.bo.Line;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class OurTramsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private OurTramsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private LoadingScreen loadingScreen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // Schedule Item list
        /*ArrayList<ScheduleItem> scheduleList = new ArrayList<>();
        scheduleList.add(new ScheduleItem("Parc malraux","18H55","Tram A"));
        scheduleList.add(new ScheduleItem("Parc Bellevue","18H45","Tram B"));
        scheduleList.add(new ScheduleItem("Haguenau","20H55","Tram C"));
        scheduleList.add(new ScheduleItem("Strasbourg","21H55","Tram D"));
        scheduleList.add(new ScheduleItem("Oui","22H55","Tram E"));
        scheduleList.add(new ScheduleItem("Non","23H55","Tram F"));
        scheduleList.add(new ScheduleItem("Rome","19H55","Tram G"));
        scheduleList.add(new ScheduleItem("Planete","18H55","Tram H"));
        scheduleList.add(new ScheduleItem("Mars","17H55","Tram I"));
        scheduleList.add(new ScheduleItem("Humour","16H55","Tram J"));
        scheduleList.add(new ScheduleItem("Drole","15H55","Tram K"));
        mAdapter = new ScheduleAdapter(scheduleList);*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingScreen = new LoadingScreen(this);

        ArrayList<OurTramsItem> nosTramsList = new ArrayList<>();
        nosTramsList.add(new OurTramsItem(R.drawable.tram_a, R.drawable.nouveau_tram_strasbourg));
        nosTramsList.add(new OurTramsItem(R.drawable.tram_b, R.drawable.nouveau_tram_strasbourg));
        nosTramsList.add(new OurTramsItem(R.drawable.tram_c, R.drawable.nouveau_tram_strasbourg));
        nosTramsList.add(new OurTramsItem(R.drawable.tram_d, R.drawable.nouveau_tram_strasbourg));
        nosTramsList.add(new OurTramsItem(R.drawable.tram_e, R.drawable.nouveau_tram_strasbourg));
        nosTramsList.add(new OurTramsItem(R.drawable.tram_f, R.drawable.nouveau_tram_strasbourg));
        mAdapter = new OurTramsAdapter(nosTramsList);

        mAdapter.setOnItemClickListener(new OurTramsAdapter.OnItemClickListener() {
            @Override
            public void onButtonScheduleClick(int position) {
                nosTramsList.get(position).changeImgTramLetter(R.drawable.tram_d);
                mAdapter.notifyItemChanged(position);
            }
        });

        // Recycler configuration
        mRecyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
                loadingScreen.display();
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                loadingScreen.dismiss();
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
            }
        });
    }
}

