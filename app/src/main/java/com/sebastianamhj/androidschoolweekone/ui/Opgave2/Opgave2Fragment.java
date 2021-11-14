package com.sebastianamhj.androidschoolweekone.ui.Opgave2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.sebastianamhj.androidschoolweekone.databinding.FragmentOpgave2Binding;

public class Opgave2Fragment extends Fragment {

    private Opgave2ViewModel opgave2ViewModel;
    private FragmentOpgave2Binding binding;

    boolean change = false;
    int clicked = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        opgave2ViewModel =
                new ViewModelProvider(this).get(Opgave2ViewModel.class);

        binding = FragmentOpgave2Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button clickBtn = binding.clickBtn;
        clickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickBtn.setText(Integer.toString(clicked++));
                if (change) {
                    clickBtn.setBackgroundColor(Color.parseColor("#F43274"));
                } else {
                    clickBtn.setBackgroundColor(Color.parseColor("#1D84D6"));
                }
                change = !change;
            }
        });

        opgave2ViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {


            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}