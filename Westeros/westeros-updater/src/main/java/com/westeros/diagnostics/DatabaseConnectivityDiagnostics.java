package com.westeros.diagnostics;

import com.westeros.diagnostics.runners.IDiagnose;
import com.westeros.diagnostics.services.contract.Diagnostics;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseConnectivityDiagnostics implements IDiagnose {

    private final JdbcTemplate jdbcTemplate;
    private static final String NAME = "Database Connection";
    private static final String DESC = "Checks if application can execute a simple query on the database.";

    @Override
    public String getName() { return NAME; }

    @Override
    public String getDescription() { return DESC; }

    @Override
    public Diagnostics run() {
        try {
            // zapytanie
            jdbcTemplate.execute("SELECT 1");
            return new Diagnostics(true, NAME, null, DESC);
        } catch (Exception e) {
            return new Diagnostics(false, NAME, "Database unreachable: " + e.getMessage(), DESC);
        }
    }
}
