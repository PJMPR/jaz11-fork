package com.westeros.diagnostics;

import com.westeros.diagnostics.runners.IDiagnose;
import com.westeros.diagnostics.services.contract.Diagnostics;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TheMovieDbApiConnectivityDiagnostics implements IDiagnose {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String NAME = "TheMovieDb API Connectivity";
    private static final String DESC = "Checks if api.themoviedb.org is reachable.";
    private static final String API_URL = "https://api.themoviedb.org/3/configuration";

    @Override
    public String getName() { return NAME; }

    @Override
    public String getDescription() { return DESC; }

    @Override
    public Diagnostics run() {
        try {
            // Próbujemy pobrać nagłówki lub wykonać lekkie zapytanie
            // Nawet jeśli zwróci 401 (Unauthorized), oznacza to, że serwis żyje i odpowiedział
            restTemplate.getForEntity(API_URL, String.class);
            return new Diagnostics(true, NAME, null, DESC);
        } catch (org.springframework.web.client.HttpStatusCodeException e) {
            // Jeśli otrzymaliśmy kod HTTP (np. 401), to znaczy, że połączenie działa
            return new Diagnostics(true, NAME, null, DESC);
        } catch (Exception e) {
            return new Diagnostics(false, NAME, "Cannot connect to TMDB: " + e.getMessage(), DESC);
        }
    }
}