package com.westeros.diagnostics.runners;

import com.westeros.diagnostics.services.contract.Diagnostics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiagnosticsRunner implements IRunDiagnoses {

//    miejsce
    private final List<IDiagnose> diagnosticTests;

    @Override
    public List<Diagnostics> runAll() {
        return diagnosticTests.stream()
                .map(IDiagnose::run)
                .collect(Collectors.toList());
    }
}