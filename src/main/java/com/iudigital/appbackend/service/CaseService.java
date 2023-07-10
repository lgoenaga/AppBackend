package com.iudigital.appbackend.service;

import com.iudigital.appbackend.model.Case;
import com.iudigital.appbackend.model.Crime;

import com.iudigital.appbackend.repository.CaseRepository;
import com.iudigital.appbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseService {

    final
    CaseRepository caseRepository;

    final UserRepository userRepository;

    public CaseService(CaseRepository caseRepository, UserRepository userRepository) {
        this.caseRepository = caseRepository;
        this.userRepository = userRepository;
    }

    public List<Case> getAllCases() {
        return caseRepository.findAll();
    }

    public Case getCaseById(Long id){
        return  caseRepository.findById(id).orElse(null);
    }

    public String addCase(Case report){

        caseRepository.save(report);
        return "SUCCESSFULLY";
    }

    public String deleteCase(Long id) {
        caseRepository.deleteById(id);
        return "SUCCESSFULLY";
    }

    public boolean existCaseById(Long id){
        return caseRepository.findById(id).isPresent();
    }

    public Crime crime(Crime crime) {
        if (crime == null) {
            crime = Crime.ATTRACT;
        }
        return crime;
    }

    public boolean existCaseByUserId(Long user_id) {

      return caseRepository.existsByUserId(user_id);
    }

    public List<Case> getCasesByUserId(Long userId) {

            return caseRepository.findAllByUserId(userId);
    }
}
