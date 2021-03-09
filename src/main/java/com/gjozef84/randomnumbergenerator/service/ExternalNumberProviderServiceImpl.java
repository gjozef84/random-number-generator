package com.gjozef84.randomnumbergenerator.service;

import com.gjozef84.randomnumbergenerator.exceptions.GenerateNumberByExternalProviderException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import static com.gjozef84.randomnumbergenerator.common.Constants.TIMEOUT_DURATION;

@Service
@Slf4j
@RequiredArgsConstructor
class ExternalNumberProviderServiceImpl implements RandomNumberProviderService {

    private static final String BASE_URL = "https://www.random.org/integers/?num=1&col=1&base=10&format=plain&rnd=new";

    @Override
    public Integer generateRandomValue(int min, int max) {
        URI requestUri = createRequestUri(min, max);
        log.info("requestURI {}", requestUri);

        String generatedRandomValue = getValueFromApi(requestUri);
        log.info("ExternalNumberProviderService generated value = {}", generatedRandomValue);

        return Optional.ofNullable(generatedRandomValue)
            .map(Integer::valueOf)
            .orElseThrow(() -> new GenerateNumberByExternalProviderException("Failed to generate a random value by external provider"));
    }

    private URI createRequestUri(int min, int max) {
        return UriComponentsBuilder.fromUriString(BASE_URL)
            .queryParam("min", String.valueOf(min))
            .queryParam("max", String.valueOf(max))
            .build()
            .toUri();
    }

    private String getValueFromApi(URI uri) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .timeout(TIMEOUT_DURATION)
            .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
            Thread.currentThread().interrupt();
        }

        if (response != null && (HttpURLConnection.HTTP_OK == response.statusCode())) {
            return response.body().replace("\n", "");  //Random.org API returns JSON with '\n'
        } else {
            log.error("API return error. Response {}", response);
            return null;
        }
    }
}
