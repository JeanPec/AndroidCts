package com.shindra;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTest#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTest extends Fragment {

    private EditText txtLogin;
    private EditText txtPassword;
    private Button btnConnect;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test, container, true);

        txtLogin = (EditText) rootView.findViewById (R.id.txtLogin);
        txtPassword = (EditText) rootView.findViewById (R.id.txtPassword);

        btnConnect = (Button) rootView.findViewById(R.id.Connect);
        btnConnect.setOnClickListener(btnConnectListener);

        return rootView;

    }

    private View.OnClickListener btnConnectListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i ("DEBUG", txtLogin.getText() + "/" + txtPassword.getText());
        }
    };

        }

//