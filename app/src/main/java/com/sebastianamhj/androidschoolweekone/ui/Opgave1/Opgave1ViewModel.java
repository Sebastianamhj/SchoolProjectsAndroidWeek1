package com.sebastianamhj.androidschoolweekone.ui.Opgave1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Opgave1ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Opgave1ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}