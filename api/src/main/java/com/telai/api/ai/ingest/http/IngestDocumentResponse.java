package com.telai.api.ai.ingest.http;

import com.telai.api.ai.ingest.DocumentIngest;

public record IngestDocumentResponse(String id) {
    public static IngestDocumentResponse fromUseCase(DocumentIngest doc) {
        return new IngestDocumentResponse(doc.id());
    }
}
