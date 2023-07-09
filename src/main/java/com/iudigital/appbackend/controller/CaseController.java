package com.iudigital.appbackend.controller;

import com.iudigital.appbackend.model.Case;
import com.iudigital.appbackend.service.CaseService;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/cases")
public class CaseController {
    private static final String NOT_FOUND = "NOT FOUND";
    private static final String BAD_REQUEST = "BAD REQUEST";
    final
    CaseService caseService;

    public CaseController(CaseService caseService) {
        this.caseService = caseService;
    }

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("")
    public String getAllCases() {


        return caseService.getAllCases();
    }

     @GetMapping("/{id}")
     public Case getCaseById(@PathVariable Long id){

        return caseService.getCaseById(id);
     }

    @PostMapping("")
    public String createCase(@RequestBody Case caseBody) {
        try{

            Case createCase = new Case();

            createCase.setCrime(caseService.crime(caseBody.getCrime()));
            createCase.setSuccessDate(caseBody.getSuccessDate());
            createCase.setDescription(caseBody.getDescription());
            createCase.setLocation(caseBody.getLocation());
            createCase.setCreated_at(new Date().toString());
            createCase.setUpdated_at(new Date().toString());
            createCase.setUser(caseBody.getUser());


            return caseService.addCase(createCase);

        }catch (Exception e){
            throw new IllegalArgumentException(BAD_REQUEST + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public String updateCase(@Validated @PathVariable Long id, @RequestBody Case caseBody)  {
        try{

            boolean existCase = caseService.existCaseById(id);

            if (!existCase){
                return NOT_FOUND;
            }

            Case updateCase = caseService.getCaseById(id);
            String updateDate = new Date().toString();

            updateCase.setCrime(caseService.crime(caseBody.getCrime())); // Default is ATTRACT
            updateCase.setDescription(caseBody.getDescription());
            updateCase.setUpdated_at(updateDate);

            return caseService.addCase(updateCase);

        }catch (Exception e){
            throw new IllegalArgumentException(BAD_REQUEST + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public String deleteCase(@PathVariable Long id)  {
        try{

            boolean existCase = caseService.existCaseById(id);

            if (existCase){
                return caseService.deleteCase(id);
            }else{
                return NOT_FOUND;
            }

        }catch (Exception e){
            throw new IllegalArgumentException(BAD_REQUEST + e.getMessage());
        }
    }

}
