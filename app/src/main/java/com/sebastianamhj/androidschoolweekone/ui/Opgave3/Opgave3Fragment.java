package com.sebastianamhj.androidschoolweekone.ui.Opgave3;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sebastianamhj.androidschoolweekone.R;
import com.sebastianamhj.androidschoolweekone.databinding.FragmentOpgave3Binding;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Opgave3Fragment extends Fragment {

    private Opgave3ViewModel opgave3ViewModel;
    private FragmentOpgave3Binding binding;

    TodoAdapter adapter;
    SharedPreferences sharedPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        opgave3ViewModel =
                new ViewModelProvider(requireActivity()).get(Opgave3ViewModel.class);

        binding = FragmentOpgave3Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);

        Log.d(TAG, "onCreateView: yoyoooyoyoyoy");

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(root.getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = sharedPreferences.getString("todo-list", "");
        Type type = new TypeToken<List<String>>() {}.getType();
        List<String> todoList = gson.fromJson(json, type);

        if (todoList == null) {
            todoList = new ArrayList<>();
            todoList.add("Groceries");
            todoList.add("Cleaning");
            todoList.add("Homework");
            String tempJson = gson.toJson(todoList);
            editor.putString("todo-list", tempJson);
            editor.apply();
        }

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        adapter = new TodoAdapter(root.getContext(), todoList);
        adapter.setClickListener(new TodoAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String action) {
                if (action.equals("delete")) {
                    List<String> tempList = adapter.getItems();
                    String tempJson = gson.toJson(tempList);
                    editor.putString("todo-list", tempJson);
                    editor.apply();
                } else if (action.equals("edit")) {
                    opgave3ViewModel.setEditTodo(adapter.getItem(position));
                    navController.navigate(R.id.fragment_add_todo);
                }
            }
        });
        recyclerView.setAdapter(adapter);

        binding.addTodoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.fragment_add_todo);
            }
        });

        opgave3ViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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