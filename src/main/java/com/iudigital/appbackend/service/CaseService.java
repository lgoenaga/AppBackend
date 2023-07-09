package com.iudigital.appbackend.service;

import com.iudigital.appbackend.model.Case;
import com.iudigital.appbackend.model.Delito;
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

    public boolean existCaseByDelito(String delito){
        return caseRepository.existsByDelito(delito);
    }

    public Delito delito(Delito delito) {
        if (delito == null) {
            delito = Delito.ATRACO;
        }
        return delito;
    }

    public Object ubicacion(double latitud, double longitud, double altitud) {
        if (latitud == 0 && longitud == 0) {
            return "No se ha podido obtener la ubicación";
        }
        return "Latitud: " + latitud + " Longitud: " + longitud + " Altitud: " + altitud;
    }


}
