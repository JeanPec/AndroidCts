package com.shindra;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;

import org.jetbrains.annotations.NotNull;

public class stationFragment extends Fragment {

    public RecyclerView recycler;
    public Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_station, container,false);

        recycler=v.findViewById(R.id.recycler_view_station);
        button=v.findViewById(R.id.buttonToMapActivity);

        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setHasFixedSize(true);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        loadingWindow window = new loadingWindow(getContext());
        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM,getArguments().getString("lineName"),0), new ObservableListener<Line>() {

            @Override
            public void onError(@NotNull Throwable throwable) {

            }

            @Override
            public void onSuccess(Line data) {
                window.dismissLoadingWindow();

                RecyclerView.Adapter _adapter = new stationAdapter(data);
                recycler.setAdapter(_adapter);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), mapActivity.class);
                        intent.putExtra("LINE_NAME",data.getName());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onLoading() {
                window.displayLoadingWindow();
            }
        });
    }

}
