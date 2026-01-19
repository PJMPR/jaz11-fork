package com.westeros.diagnostics.controllers;

import com.westeros.diagnostics.runners.IRunDiagnoses;
import com.westeros.diagnostics.services.contract.Diagnostics;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Używamy RestController, aby automatycznie serializować wyniki do JSON
@RequestMapping("diagnostics")
@RequiredArgsConstructor
public class DiagnosticsController {

    private final IRunDiagnoses diagnosticsRunner;

    /**
     * Endpoint: http://localhost:PORT/diagnostics
     */
    @GetMapping
    public ResponseEntity<String> checkStatus(){
        return ResponseEntity.ok("ALIVE");
    }

    /**
     * Endpoint: http://localhost:PORT/diagnostics/check
     * Odpala wszystkie zarejestrowane testy diagnostyczne.
     */
    @GetMapping("/check")
    public ResponseEntity<List<Diagnostics>> checkDiagnostics() {
        List<Diagnostics> results = diagnosticsRunner.runAll();
        return ResponseEntity.ok(results);
    }
}