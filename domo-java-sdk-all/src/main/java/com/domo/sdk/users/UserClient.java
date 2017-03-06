package com.domo.sdk.users;

import com.domo.sdk.request.Transport;
import com.domo.sdk.request.UrlBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.HttpUrl;

import java.util.List;

public class UserClient {
    private final UrlBuilder urlBuilder;
    private final Transport transport;

    public UserClient(UrlBuilder urlBuilder, Transport transport) {
        this.urlBuilder = urlBuilder;
        this.transport = transport;
    }

//    POST /v1/users?sendInvite=true HTTP/1.1
//    Content-Type: application/json
//    Accept: application/json
//    Host: api.domo.com
//    Content-Length: 90
//    Authorization: bearer <your-valid-oauth-access-token>
//
//    {
//        "email" : "leonhard.euler@domo.com",
//            "role" : "Admin",
//            "name" : "Leonhard Euler"
//    }
    public User create(boolean sendInvite, User user) {
        HttpUrl url = urlBuilder.fromPathSegments("/v1/users")
                .addQueryParameter("sendInvite", Boolean.toString(sendInvite))
                .build();

        return transport.postJson(url, user, User.class);
    }

    public List<User> get(long userId) {
        HttpUrl url = urlBuilder.fromPathSegments("/v1/users")
                .addPathSegment(Long.toString(userId))
                .build();

        return transport.getJson(url, User.class);
    }

    public List<User> list(int limit, int offset) {
        HttpUrl url = urlBuilder.fromPathSegments("/v1/users")
                .addQueryParameter("limit", Integer.toString(limit))
                .addQueryParameter("offset", Integer.toString(offset))
                .build();

        return transport.getJson(url, new TypeToken<List<User>>(){}.getType());
    }


    public User update(long userId, User user) {
        HttpUrl url = urlBuilder.fromPathSegments("/v1/users")
                .addPathSegment(Long.toString(userId))
                .build();

        return transport.putJson(url, user, User.class);
    }


    public void delete(long userId) {
        HttpUrl url = urlBuilder.fromPathSegments("/v1/users")
                .addPathSegment(Long.toString(userId))
                .build();

        transport.deleteJson(url);
    }





}
