package com.its.samplelearning;

import java.util.ArrayList;

public interface ApiCallBackListener {

    void onRequestSuccess(ArrayList<UserPosts> response);

    void onRequestFailed(String message);

    void onInternetConnectivityFailed(String message);
}
