package com.shindra.OurTrams;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shindra.CT;
import com.shindra.LoadingScreen;
import com.shindra.MyViewModel;
import com.shindra.R;
import com.shindra.Schedule.ScheduleActivity;
import com.shindra.Schedule.ScheduleFragment;
import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class OurTramsFragment extends Fragment {
    private static final String TAG = "OurTramsFragment";
    private LoadingScreen mLoadingScreen;
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i(TAG, "onCreateView");
        AppCompatActivity upper = (AppCompatActivity)getContext();
        upper.getSupportActionBar().setTitle("Nos trams");
        mLoadingScreen = new LoadingScreen((Activity)getContext());

        // Get bundle
        Bundle bundle = new Bundle();
        String tramLineName = bundle.getString("tramLineName");

        // View configurations
        View view = inflater.inflate(R.layout.fv_our_trams, container, false);
        mRecyclerView = view.findViewById(R.id.fv_our_trams_RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Adapter: list will automatically be update with Update method
        OurTramsAdapter adapter = new OurTramsAdapter(new ArrayList<OurTramsItem>(), new OurTramsAdapter.OnItemClickListener() {
            @Override
            public void onButtonScheduleClick(String tramLineName) {
                Intent intent = new Intent(getActivity(), ScheduleActivity.class);
                intent.putExtra("tramLineName", tramLineName);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(adapter);

        // Network calls
        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress

                mLoadingScreen.display();
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                //call once the network call has responded with a success

                // Change bar title
                mLoadingScreen.dismiss();

                // Get only tram data and convert it to OurTramsItem
                ArrayList<OurTramsItem> ourTramsItemList = new ArrayList<OurTramsItem>();

                // Possible de l'ajouter comme ça ? Sinon faut faire un new dans la boucle
                //OurTramsItem tmpItem = new OurTramsItem();

                for (Line element : data){
                    // Add when network part is working
                    if (element.getRouteType() == RouteType.TRAM){
                        ourTramsItemList.add(new OurTramsItem(CT.GetLineRef(element.getName()), element.getStops(), CT.GetTramLetterImg(CT.GetLineRef(element.getName())), R.drawable.nouveau_tram_strasbourg));
                    }
                }
                // Update fragment if any changes in tram list
                Update(ourTramsItemList);
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
                mLoadingScreen.dismiss();
            }
        });
        return view;
    }

    public void Update(ArrayList<OurTramsItem> ourTramsItemsList) {
        Log.i("OurTramsFragment", "UpdateMethod");
        OurTramsAdapter tmp = (OurTramsAdapter) mRecyclerView.getAdapter();
        tmp.SetOurTramsList(ourTramsItemsList);

        // Recreate all ViewHolders
        tmp.notifyDataSetChanged();
        }
}