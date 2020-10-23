package com.example.voting.entity;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class TestHash {
    public static void main(String args[]){
        String text = "pontep";

        Vote newVote = new Vote();
        newVote.setId(new Long(1));
        System.out.println(newVote.toString());
        try{
            String data = newVote.toString();
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
            String encoded = DatatypeConverter.printHexBinary(hash);
            System.out.println(encoded.toLowerCase());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
