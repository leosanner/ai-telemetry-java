package com.telai.api.ai.ingest.http;

import com.telai.api.ai.ingest.IngestDocumentUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/ingest")
public class IngestDocumentController {

    private final IngestDocumentUseCase ingestDocumentUseCase;

    public IngestDocumentController(IngestDocumentUseCase ingestDocumentUseCase) {
        this.ingestDocumentUseCase = ingestDocumentUseCase;
    }

    @PostMapping("/")
    public ResponseEntity<IngestDocumentResponse> ingestDocument(
            @RequestBody @Valid IngestDocumentRequest ingestDocumentRequest
    ) {
        var resp = IngestDocumentResponse.fromUseCase(
                this.ingestDocumentUseCase.execute()
        );

        return ResponseEntity.ok(resp);
    }
}
