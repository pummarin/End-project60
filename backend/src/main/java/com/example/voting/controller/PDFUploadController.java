package com.example.voting.controller;

  
import java.io.IOException;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.voting.storage.*;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class PDFUploadController {
    
	private final PdfStorage pdfStorage;

    @Autowired
    public PDFUploadController(PdfStorage pdfStorage) {
        this.pdfStorage = pdfStorage;
    }

    @GetMapping("/uploadedPdf")
    public ResponseEntity<?> listUploadPdf2(Model model) throws IOException {

        return ResponseEntity.ok().body(pdfStorage.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(PDFUploadController.class,
                        "servePdfFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

    }

    @GetMapping("/testpdf")
    public String  listUploadPdf(Model model) throws IOException {
      
        model.addAttribute("files", pdfStorage.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(PDFUploadController.class,
                        "servePdfFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/pdf/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> servePdfFile(@PathVariable String filename) {

        Resource file = pdfStorage.loadPdfAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/testpdf")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        pdfStorage.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }
    @PostMapping("/uploadPdfFile")
    public ResponseEntity<?> handleFileUpload2(@RequestParam("file") MultipartFile file) {

        try{
            pdfStorage.store(file);
            String result = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.ok().body(result);
        }
        catch(Exception ex){

            return ResponseEntity.badRequest().body(new MessageResponse("error jaaaa"));
        }
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
	
}
