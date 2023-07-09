package com.iudigital.appbackend.service;

import com.iudigital.appbackend.model.Case;
import com.iudigital.appbackend.model.Crime;
import com.iudigital.appbackend.repository.CaseRepository;
import org.springframework.stereotype.Service;

@Service
public class CaseService {

    final
    CaseRepository caseRepository;

    public CaseService(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    public String getAllCases() {
        return caseRepository.findAll().toString();
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

}
