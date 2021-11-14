package com.sebastianamhj.androidschoolweekone.ui.Opgave3;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sebastianamhj.androidschoolweekone.R;
import com.sebastianamhj.androidschoolweekone.databinding.FragmentAddTodoBinding;
import com.sebastianamhj.androidschoolweekone.databinding.FragmentOpgave3Binding;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class AddTodoFragment extends Fragment {

    private Opgave3ViewModel opgave3ViewModel;
    private FragmentAddTodoBinding binding;
    SharedPreferences sharedPreferences;
    boolean edit = false;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        opgave3ViewModel =
                new ViewModelProvider(requireActivity()).get(Opgave3ViewModel.class);

        binding = FragmentAddTodoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);

        if (opgave3ViewModel.getEditTodo().getValue() != null) {
            Log.d(TAG, "onCreateView: yo " + opgave3ViewModel.getEditTodo().getValue());
            edit = true;
            binding.addTodoEdittext.setText(opgave3ViewModel.getEditTodo().getValue());
            binding.addTodoAddBtn.setText("Edit");

        }


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(root.getContext());
        Gson gson = new Gson();
        String json = sharedPreferences.getString("todo-list", "");
        Type type = new TypeToken<List<String>>() {}.getType();
        List<String> todoList = gson.fromJson(json, type);
        Log.d(TAG, "onCreateView: " + todoList);

        binding.addTodoAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todoList.add(binding.addTodoEdittext.getText().toString());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String json = gson.toJson(todoList);

                editor.putString("todo-list", json);
                editor.apply();
                hideKeyboard();
                navController.popBackStack();
            }
        });

        binding.addTodoEdittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;
                if (i == 6) {
                    hideKeyboard();
                    handled = true;
                }
                return handled;
            }
        });
        return root;
    }

    public void hideKeyboard() {
        final InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }
}
