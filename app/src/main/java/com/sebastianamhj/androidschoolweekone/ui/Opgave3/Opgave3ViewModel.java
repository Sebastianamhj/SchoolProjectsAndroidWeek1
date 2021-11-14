package com.sebastianamhj.androidschoolweekone.ui.Opgave3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Opgave3ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    private MutableLiveData<String> editTodo = new MutableLiveData<>();

    public Opgave3ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<String> getEditTodo() { return editTodo; }

    public void setEditTodo(String value) { editTodo.setValue(value); }
}