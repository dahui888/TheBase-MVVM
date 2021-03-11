package com.theone.demo.app.widge;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.theone.demo.R;


//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃                  神兽保佑
//    ┃　　　┃                  永无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛

/**
 * @author The one
 * @date 2020/1/6 0006
 * @describe 搜索
 * @email 625805189@qq.com
 * @remark
 */
public class TheSearchView extends RelativeLayout implements TextWatcher, View.OnClickListener {

    private static final String TAG = "SearchView";

    private EditText mSearchEditText;
    private AppCompatImageView mDeleteImageView;
    private OnTextChangedListener onTextChangedListener;
    private boolean mEditEnable = true;
    private int mDefaultPadding;

    public void setOnTextChangedListener(OnTextChangedListener onTextChangedListener) {
        this.onTextChangedListener = onTextChangedListener;
    }

    public void setEditEnable(boolean editEnable) {
        this.mEditEnable = editEnable;
        initEditTextEnable();
    }

    public TheSearchView(Context context){
        super(context);
        initView();
    }

    public TheSearchView(Context context, boolean editEnable) {
        super(context);
        this.mEditEnable = editEnable;
        initView();
    }

    public TheSearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TheSearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setBackground(getDrawable(R.drawable.search_bg_corner));
        mDefaultPadding = dp2px(10);
        addView(getDeleteImage());
        addView(getSearchEditText());
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        setLayoutParams(layoutParams);
    }

    public EditText getSearchEditText() {
        if (null == mSearchEditText) {
            mSearchEditText = new EditText(getContext());
            mSearchEditText.setBackground(null);
            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.LEFT_OF, mDeleteImageView.getId());
            mSearchEditText.setLayoutParams(layoutParams);
            mSearchEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, dp2px(14));
            mSearchEditText.setHint("搜索");
            mSearchEditText.setSingleLine(true);
            mSearchEditText.setPadding(mDefaultPadding, dp2px(7), mDefaultPadding, dp2px(7));
            mSearchEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(getDrawable(R.drawable.mz_titlebar_search_layout_ic_search_dark), null, null, null);
            mSearchEditText.setCompoundDrawablePadding(mDefaultPadding);
            initEditTextEnable();
            mSearchEditText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
            mSearchEditText.setOnEditorActionListener(editorActionListener);
            mSearchEditText.addTextChangedListener(this);
        }
        return mSearchEditText;
    }

    public AppCompatImageView getDeleteImage() {
        if (null == mDeleteImageView) {
            mDeleteImageView = new AppCompatImageView(getContext());
            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            layoutParams.rightMargin = mDefaultPadding;
            layoutParams.leftMargin = mDefaultPadding;
            mDeleteImageView.setId(R.id.search_layout_delete);
            mDeleteImageView.setLayoutParams(layoutParams);
            mDeleteImageView.setImageDrawable(getDrawable(R.drawable.search_title_cancel));
            mDeleteImageView.setOnClickListener(this);
            mDeleteImageView.setVisibility(GONE);
        }
        return mDeleteImageView;
    }

    private void initEditTextEnable(){
        if(null != mSearchEditText){
            mSearchEditText.setCursorVisible(mEditEnable);
            mSearchEditText.setFocusable(mEditEnable);
            mSearchEditText.setFocusableInTouchMode(mEditEnable);
            if (mEditEnable) mSearchEditText.requestFocus();
            else mSearchEditText.clearFocus();
        }
    }

    private Drawable getDrawable(@DrawableRes int res) {
        return ContextCompat.getDrawable(getContext(), res);
    }

    private int dp2px(int dp) {
        return QMUIDisplayHelper.dp2px(getContext(), dp);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String content = mSearchEditText.getText().toString();
        boolean isEmpty = TextUtils.isEmpty(content);
        mDeleteImageView.setVisibility(isEmpty ? GONE : VISIBLE);
        if (null != onTextChangedListener) {
            onTextChangedListener.onChanged(content, isEmpty);
        }
    }

    @Override
    public void onClick(View v) {
        mSearchEditText.setText("");
    }

    private TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (onTextChangedListener != null && actionId == EditorInfo.IME_ACTION_SEARCH) {
                String content = mSearchEditText.getText().toString();
                boolean isEmpty = TextUtils.isEmpty(content);
                mDeleteImageView.setVisibility(isEmpty ? GONE : VISIBLE);
                if (null != onTextChangedListener) {
                    onTextChangedListener.onSearch(content, isEmpty);
                }
            }
            return false;
        }
    };

    public interface OnTextChangedListener {
        void onChanged(String content, boolean isEmpty);
        void onSearch(String content, boolean isEmpty);
    }

}
