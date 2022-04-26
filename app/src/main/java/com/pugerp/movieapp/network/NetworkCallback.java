package com.pugerp.movieapp.network;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.HttpException;
import retrofit2.Response;
import rx.Subscriber;

public abstract class NetworkCallback<M> extends Subscriber<Response<M>> {

    private static final String TAG = NetworkCallback.class.getName();

    public abstract void onSuccess(M model);

    public abstract void onFailure(String message);

    public abstract void onFinish();

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        try {
            if (e instanceof HttpException) {
                HttpException httpException = (HttpException) e;
                int code = httpException.code();
                String message = httpException.getMessage();
                Log.i(TAG, "code : " + code);

                String url = null;
                String request = null;

                if (httpException.response() != null) {
                    url = httpException.response().raw().request().url().url().toString();
                    request = bodyToString(httpException.response().raw().request().body());
                }

                logHttpException(code, message, url, request);
                onFailure(message);
            } else {
                onFailure(e.getMessage());
            }
        } catch (Exception err) {
            err.printStackTrace();
        } finally {
            onFinish();
        }
    }

    @Override
    public void onNext(Response<M> model) {
        if (model.isSuccessful()) {
            onSuccess(model.body());
        } else {
            try {
                String result = model.errorBody() != null ? model.errorBody().string() : null;
                String url = null;
                String request = null;
                if (model.raw().request() != null && model.raw().request().body() != null) {
                    url = model.raw().request().url().url().toString();
                    request = bodyToString(model.raw().request().body());
                }
                logHttpException(model.code(), result, url, request);
                onFailure(extractErrorMessage(result));
            } catch (Exception e) {
                e.printStackTrace();
                onFailure("Terjadi kesalahan pada server, silahkan coba kembali");
            } finally {
                onFinish();
            }
        }
    }

    @Override
    public void onCompleted() {
        onFinish();
    }

    private String extractErrorMessage(String message) {
        try {
            StringBuilder extracted = new StringBuilder();
            JSONObject jObject = new JSONObject(message);
            Iterator<?> keys = jObject.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                if (extracted.length() > 0)
                    extracted.append("\n");

                if (jObject.get(key) instanceof JSONObject) {
                    extracted.append(jObject.getString(key));
                } else if (jObject.get(key) instanceof JSONArray) {
                    for (int i = 0; i < jObject.getJSONArray(key).length(); i++) {
                        extracted.append(jObject.getJSONArray(key).getString(i));
                    }
                }
            }
            return extracted.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return message;
        }
    }

    private void logHttpException(int code, String respBody, String url, String reqBody) {
        Log.d(TAG, "logHttpException: " + code);
        Log.d(TAG, "logHttpException: " + respBody);
        Log.d(TAG, "logHttpException: " + url);
        Log.d(TAG, "logHttpException: " + reqBody);

        try {
            throw new Exception("Failed Request Detected");
        } catch (Exception e) {
            String sendToLog = code + " \n " + respBody;

            if (url != null) {
                sendToLog += "\nURL : \n" + url;
            }

            if (reqBody != null) {
                sendToLog += "\nRequest : \n" + reqBody;
            }

            Log.d(TAG, "logHttpException: " + sendToLog);
            e.printStackTrace();
        }
    }

    private String bodyToString(final RequestBody request) {
        try {
            final Buffer buffer = new Buffer();
            if (request != null)
                request.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work " + e.getMessage();
        }
    }
}