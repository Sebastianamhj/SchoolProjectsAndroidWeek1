package com.sebastianamhj.androidschoolweekone.ui.Opgave1;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.sebastianamhj.androidschoolweekone.databinding.FragmentOpgave1Binding;

public class Opgave1Fragment extends Fragment {

    private Opgave1ViewModel opgave1ViewModel;
    private FragmentOpgave1Binding binding;

    boolean operatorActive = false;
    String currentOperator = "";
    int currentNumber;
    TextView calcTv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        opgave1ViewModel =
                new ViewModelProvider(this).get(Opgave1ViewModel.class);

        binding = FragmentOpgave1Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        calcTv = binding.calcTextview;

        binding.calc0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcTv.append("0");

            }
        });

        binding.calc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcTv.append("1");

            }
        });

        binding.calc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcTv.append("2");

            }
        });

        binding.calc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcTv.append("3");

            }
        });

        binding.calc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcTv.append("4");

            }
        });

        binding.calc5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcTv.append("5");

            }
        });

        binding.calc6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcTv.append("6");

            }
        });

        binding.calc7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcTv.append("7");

            }
        });

        binding.calc8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcTv.append("8");

            }
        });

        binding.calc9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcTv.append("9");

            }
        });

        binding.calcPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (operatorActive) {
                    int tempInt = getOperatedNumber();
                    calcTv.setText(Integer.toString(currentNumber + tempInt));
                } else {
                    operatorActive = true;
                    currentOperator = "plus";

                    currentNumber = getOperatedNumber();
                    calcTv.setText("");
                }
            }
        });

        binding.calcDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (operatorActive) {
                    int tempInt = getOperatedNumber();
                    calcTv.setText(Integer.toString(currentNumber / tempInt));
                } else {
                    operatorActive = true;
                    currentOperator = "divide";

                    currentNumber = getOperatedNumber();
                    calcTv.setText("");
                }
            }
        });

        binding.calcTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (operatorActive) {
                    int tempInt = getOperatedNumber();
                    calcTv.setText(Integer.toString(currentNumber * tempInt));
                } else {
                    operatorActive = true;
                    currentOperator = "times";

                    currentNumber = getOperatedNumber();
                    calcTv.setText("");
                }
            }
        });

        binding.calcMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (operatorActive) {
                    int tempInt = getOperatedNumber();
                    calcTv.setText(Integer.toString(currentNumber - tempInt));
                } else {
                    operatorActive = true;
                    currentOperator = "minus";

                    currentNumber = getOperatedNumber();
                    calcTv.setText("");
                }
            }
        });

        binding.calcClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operatorActive = false;
                calcTv.setText("");
            }
        });

        binding.calcEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tempNumber = 0;
                if (operatorActive) {
                    switch (currentOperator) {
                        case "plus":
                            tempNumber = currentNumber + getOperatedNumber();
                            calcTv.setText(Integer.toString(tempNumber));
                            break;
                        case "minus":
                            tempNumber = currentNumber - getOperatedNumber();
                            calcTv.setText(Integer.toString(tempNumber));
                            break;
                        case "times":
                            tempNumber = currentNumber * getOperatedNumber();
                            calcTv.setText(Integer.toString(tempNumber));
                            break;
                        case "divide":
                            tempNumber = currentNumber / getOperatedNumber();
                            calcTv.setText(Integer.toString(tempNumber));
                            break;
                    }
                }
                operatorActive = false;

            }
        });

        final GridLayout gridLayout = binding.gridLayout;
        opgave1ViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

    public int getOperatedNumber() {
        try {
            return Integer.parseInt(String.valueOf(calcTv.getText()));

        } catch (Exception e) {
            Log.d(TAG, "onClick: " + e);
        }
        return 0;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}