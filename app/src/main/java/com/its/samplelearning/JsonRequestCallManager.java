package com.its.samplelearning;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonRequestCallManager {

    public static void getPosts(Context mContext, RequestQueue requestQueue, String url, ApiCallBackListener apiCallBackListener) {

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        ArrayList<UserPosts> userPosts = new ArrayList<>();
                        if (response != null && response.length() > 0) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject obj = response.getJSONObject(i);

                                    UserPosts posts = new UserPosts();
                                    posts.setUserId(obj.optString("userId"));
                                    posts.setId(obj.optString("id"));
                                    posts.setTitle(obj.optString("title"));
                                    posts.setBody(obj.optString("body"));

                                    userPosts.add(posts);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    apiCallBackListener.onRequestSuccess(null);
                                    return;
                                }
                            }
                        }
                        apiCallBackListener.onRequestSuccess(userPosts);
                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        apiCallBackListener.onRequestFailed(error.getMessage());
                    }
                });
        if (Utility.isNetworkConnected(mContext))
            requestQueue.add(jsonObjectRequest);
        else {
            apiCallBackListener.onInternetConnectivityFailed(mContext.getResources().getString(R.string.no_connection_msg));
        }
    }
}
