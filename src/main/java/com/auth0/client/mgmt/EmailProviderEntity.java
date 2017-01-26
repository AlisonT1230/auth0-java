package com.auth0.client.mgmt;

import com.auth0.utils.Asserts;
import com.auth0.client.mgmt.filter.FieldsFilter;
import com.auth0.json.mgmt.emailproviders.EmailProvider;
import com.auth0.net.CustomRequest;
import com.auth0.net.Request;
import com.auth0.net.VoidRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.util.Map;

public class EmailProviderEntity extends BaseManagementEntity {
    EmailProviderEntity(OkHttpClient client, String baseUrl, String apiToken) {
        super(client, baseUrl, apiToken);
    }

    /**
     * Request the Email Provider. A token with scope read:email_provider is needed.
     *
     * @param filter the filter to use. Can be null.
     * @return a Request to execute.
     */
    public Request<EmailProvider> get(FieldsFilter filter) {
        HttpUrl.Builder builder = HttpUrl.parse(baseUrl)
                .newBuilder()
                .addPathSegment("api")
                .addPathSegment("v2")
                .addPathSegment("emails")
                .addPathSegment("provider");
        if (filter != null) {
            for (Map.Entry<String, Object> e : filter.getAsMap().entrySet()) {
                builder.addQueryParameter(e.getKey(), String.valueOf(e.getValue()));
            }
        }
        String url = builder.build().toString();
        CustomRequest<EmailProvider> request = new CustomRequest<>(client, url, "GET", new TypeReference<EmailProvider>() {
        });
        request.addHeader("Authorization", "Bearer " + apiToken);
        return request;
    }

    /**
     * Setup the Email Provider. A token with scope create:email_provider is needed.
     *
     * @param emailProvider the email provider data to set
     * @return a Request to execute.
     */
    public Request<EmailProvider> setup(EmailProvider emailProvider) {
        Asserts.assertNotNull(emailProvider, "email provider");

        String url = HttpUrl.parse(baseUrl)
                .newBuilder()
                .addPathSegment("api")
                .addPathSegment("v2")
                .addPathSegment("emails")
                .addPathSegment("provider")
                .build()
                .toString();
        CustomRequest<EmailProvider> request = new CustomRequest<>(this.client, url, "POST", new TypeReference<EmailProvider>() {
        });
        request.addHeader("Authorization", "Bearer " + apiToken);
        request.setBody(emailProvider);
        return request;
    }

    /**
     * Delete the existing Email Provider. A token with scope delete:email_provider is needed.
     *
     * @return a Request to execute.
     */
    public Request delete() {
        String url = HttpUrl.parse(baseUrl)
                .newBuilder()
                .addPathSegment("api")
                .addPathSegment("v2")
                .addPathSegment("emails")
                .addPathSegment("provider")
                .build()
                .toString();
        VoidRequest request = new VoidRequest(client, url, "DELETE");
        request.addHeader("Authorization", "Bearer " + apiToken);
        return request;
    }

    /**
     * Update the existing Email Provider. A token with scope update:email_provider is needed.
     *
     * @param emailProvider the email provider data to set.
     * @return a Request to execute.
     */
    public Request<EmailProvider> update(EmailProvider emailProvider) {
        Asserts.assertNotNull(emailProvider, "email provider");

        String url = HttpUrl.parse(baseUrl)
                .newBuilder()
                .addPathSegment("api")
                .addPathSegment("v2")
                .addPathSegment("emails")
                .addPathSegment("provider")
                .build()
                .toString();
        CustomRequest<EmailProvider> request = new CustomRequest<>(this.client, url, "PATCH", new TypeReference<EmailProvider>() {
        });
        request.addHeader("Authorization", "Bearer " + apiToken);
        request.setBody(emailProvider);
        return request;
    }

}
