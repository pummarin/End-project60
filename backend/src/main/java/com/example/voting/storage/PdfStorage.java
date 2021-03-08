package com.example.voting.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface PdfStorage {
    void init();
	void store(MultipartFile file);
	Stream<Path> loadAll();
	Path loadPdf(String pdf);
	Resource loadPdfAsResource(String pdf);
	void deleteAll();
}
