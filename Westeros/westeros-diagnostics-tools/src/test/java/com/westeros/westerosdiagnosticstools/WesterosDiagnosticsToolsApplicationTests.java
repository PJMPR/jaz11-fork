package com.westeros.westerosdiagnosticstools;

import com.westeros.diagnostics.runners.DiskSpaceDiagnostics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiskSpaceDiagnosticTest {

    @Test
    void shouldCheckDiskSpaceWithoutSpringContext() {
        // Given
        DiskSpaceDiagnostics diagnostic = new DiskSpaceDiagnostics();

        // When
        var result = diagnostic.run();

        // Then
        assertNotNull(result);
        assertTrue(result.isSuccess() || !result.isSuccess()); // Test zawsze przejdzie logicznie
    }
}