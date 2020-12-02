package com.example.voting.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Lob;

import lombok.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


    public class FileStorage {
    @Id
    @SequenceGenerator(name = "file_storage_seq", sequenceName = "file_storage_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_storage_seq")
    private Long fileId;
    private String fileName;
    private String fileType;

    public FileStorage(String fileName, String fileType) {
            this.fileName = fileName;
            this.fileType = fileType;
        }
    @Lob
    private byte[] data;
   
}