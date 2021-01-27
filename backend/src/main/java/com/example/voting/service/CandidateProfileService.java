package com.example.voting.service;

import com.example.voting.repository.CandidateProfileRepository;
import com.example.voting.entity.CandidateProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
// import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CandidateProfileService {

  @Autowired
  private CandidateProfileRepository candidateProfileRepository;

  // @Autowired
  // private CandidateProfile candidateProfile;

  public String deleteById(Long id) {
    Optional<CandidateProfile> candidateProfile = candidateProfileRepository.findById(id);
    if (candidateProfile.isPresent()) {
      candidateProfileRepository.deleteById(id);
      return "delete success";
    } else {
      return "candidateprofile not found.";
    }
  }

  public String editCandidate(CandidateProfile request) {
    Optional<CandidateProfile> candidateProfile = candidateProfileRepository.findById(request.getCan_id());
    if (candidateProfile.isPresent()) {
      CandidateProfile cp = candidateProfile.get();
      cp.setC_number(request.getC_number());
      cp.setTitle_name(request.getTitle_name());
      cp.setC_name(request.getC_name());
      cp.setBirthday(request.getBirthday());
      cp.setGender(request.getGender());
      cp.setTelephone(request.getTelephone());
      cp.setGrade(request.getGrade());
      cp.setMajor(request.getMajor());
      cp.setPurpose(request.getPurpose());
      cp.setArchivement(request.getArchivement());
      cp.setAvatar(request.getAvatar());
      candidateProfileRepository.save(cp);
      return "edit candidate profile success";
    } else {
      return "edit candidate profile not success";
    }
  }

  // public CandidateProfile deleteById(long id) {
  // CandidateProfile candidateProfile = findById(id);

  // if (candidateProfile == null)
  // return null;

  // if (canprofile.remove(candidateProfile)) {
  // return candidateProfile;
  // }

  // return null;
  // }
}