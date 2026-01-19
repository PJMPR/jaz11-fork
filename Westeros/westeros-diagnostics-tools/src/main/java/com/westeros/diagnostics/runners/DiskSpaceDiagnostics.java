package com.westeros.diagnostics.runners;

import com.westeros.diagnostics.services.contract.Diagnostics;

import com.westeros.diagnostics.runners.IDiagnose;
import com.westeros.diagnostics.services.contract.Diagnostics;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Paths;

@Component
public class DiskSpaceDiagnostics implements IDiagnose {

    private static final String NAME = "Disk Space Diagnostic";
    private static final String DESCRIPTION = "Checks if the available disk space is above the safety threshold (1 GB).";
    private static final long MIN_FREE_SPACE_BYTES = 1024L * 1024L * 1024L; // 1 GB

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public Diagnostics run() {
        File root = new File("."); //ten katalog
        long usableSpace = root.getUsableSpace();
        boolean success = usableSpace >= MIN_FREE_SPACE_BYTES;

        String errorMessage = null;
        if (!success) {
            errorMessage = String.format(
                    "Low disk space: available %d MB, required at least %d MB",
                    usableSpace / (1024 * 1024),
                    MIN_FREE_SPACE_BYTES / (1024 * 1024)
            );
        }

        return new Diagnostics(success, NAME, errorMessage, DESCRIPTION);
    }
}
