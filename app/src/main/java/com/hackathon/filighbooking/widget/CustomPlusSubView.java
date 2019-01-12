package com.hackathon.filighbooking.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hackathon.filighbooking.R;

public class CustomPlusSubView extends LinearLayout implements View.OnClickListener {

    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 4;

    // View members
    private ImageButton mSubtractBtn;
    private ImageButton mPlusBtn;
    private TextView mEditText;

    // Data member
    private int mValue;
    private boolean isMax;
    private boolean isMin;

    public CustomPlusSubView(Context context) {
        super(context);
        mValue = MIN_VALUE;
        initLayout();
    }

    public CustomPlusSubView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mValue = MIN_VALUE;
        initLayout();
    }

    private void initLayout() {
        LayoutInflater.from(getContext()).inflate(R.layout.custom_plus_subtract_view, this);
        initViews();
    }

    private void initViews() {
        mSubtractBtn = findViewById(R.id.mCustomPlusSubBtnSubtract);
        mPlusBtn = findViewById(R.id.mCustomPlusSubBtnPlus);
        mEditText = findViewById(R.id.mCustomPlusSubEditText);

        mSubtractBtn.setOnClickListener(this);
        mPlusBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mCustomPlusSubBtnPlus:
                if (mValue < MAX_VALUE) {
                    mValue = mValue + 1;
                    mEditText.setText(String.valueOf(mValue));
                    if (mValue == MAX_VALUE)
                        setUnableStateForPlusButton();
                    if (isMin)
                        setEnableStateForSubButton();
                }
                break;
            case R.id.mCustomPlusSubBtnSubtract:
                if (mValue > 1) {
                    mValue = mValue - 1;
                    mEditText.setText(String.valueOf(mValue));
                    if (mValue == 1)
                        setUnableStateForSubButton();
                    if (isMax)
                        setEnableStateForPlusButton();
                }
                break;
        }
    }

    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        this.mValue = value;
    }

    private void setUnableStateForPlusButton() {
        isMax = true;
        mPlusBtn.setEnabled(false);
    }

    private void setUnableStateForSubButton() {
        isMin = true;
        mSubtractBtn.setEnabled(false);
    }

    private void setEnableStateForPlusButton() {
        isMax = false;
        mPlusBtn.setEnabled(true);
    }

    private void setEnableStateForSubButton() {
        isMin = false;
        mSubtractBtn.setEnabled(true);
    }

    public void setEditTextValue(int value) {
        if (value >= MIN_VALUE && value <= MAX_VALUE) {
            mValue = value;
            mEditText.setText(String.valueOf(value));
            if (mValue == MAX_VALUE)
                setUnableStateForPlusButton();
            if (mValue == MIN_VALUE)
                setUnableStateForSubButton();
        } else {
            mEditText.setText(String.valueOf(mValue));
        }
    }
}
