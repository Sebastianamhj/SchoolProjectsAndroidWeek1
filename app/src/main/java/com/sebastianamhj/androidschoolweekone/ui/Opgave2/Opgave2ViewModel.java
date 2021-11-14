package com.sebastianamhj.androidschoolweekone.ui.Opgave2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Opgave2ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Opgave2ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}