package com.its.samplelearning;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.its.samplelearning.databinding.ActivitySampleRecyBinding;

import java.util.ArrayList;

public class SampleRecylerViewActivity extends AppCompatActivity {

    private ActivitySampleRecyBinding objUIBinding;

    private RcySampleAdapter rcySampleAdapter;
    private ArrayList<UserPosts> userPosts;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        objUIBinding = DataBindingUtil.setContentView(this, R.layout.activity_sample_recy);
        Utility.setToolbar(this, "Sample RCY VIEW");
        requestQueue = Volley.newRequestQueue(this);
        initUI();
    }

    private void initUI() {
        getPostsCall();
    }

    private void setAdapter() {
        objUIBinding.rcyView.setVisibility(View.VISIBLE);
        rcySampleAdapter = new RcySampleAdapter(userPosts);
        objUIBinding.rcyView.setLayoutManager(new LinearLayoutManager(this));
        objUIBinding.rcyView.setHasFixedSize(true);
        objUIBinding.rcyView.setAdapter(rcySampleAdapter);
    }

    private void getPostsCall() {
        objUIBinding.progressViewLyt.setVisibility(View.VISIBLE);
        JsonRequestCallManager.getPosts(this, requestQueue, Utility.URL_GET_POSTS, new ApiCallBackListener() {
            @Override
            public void onRequestSuccess(ArrayList<UserPosts> tempUserPosts) {
                if (tempUserPosts != null) {
                    userPosts = tempUserPosts;
                    setAdapter();
                    objUIBinding.progressViewLyt.setVisibility(View.GONE);
                    objUIBinding.apiErrorLyt.setVisibility(View.GONE);
                } else {
                    objUIBinding.progressViewLyt.setVisibility(View.GONE);
                    objUIBinding.apiErrorLyt.setVisibility(View.VISIBLE);
                    ((TextView) objUIBinding.apiErrorLyt.findViewById(R.id.txt_error_view)).setText(R.string.lbl_server_error);
                }
            }

            @Override
            public void onRequestFailed(String message) {
                objUIBinding.progressViewLyt.setVisibility(View.GONE);
                objUIBinding.apiErrorLyt.setVisibility(View.VISIBLE);
                ((TextView) objUIBinding.apiErrorLyt.findViewById(R.id.txt_error_view)).setText(message);
            }

            @Override
            public void onInternetConnectivityFailed(String message) {
                objUIBinding.progressViewLyt.setVisibility(View.GONE);
                objUIBinding.apiErrorLyt.setVisibility(View.VISIBLE);
                Toast.makeText(SampleRecylerViewActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onRetryClickListener(View view) {
        getPostsCall();
    }
}
