package com.example.clickup.controller;

import com.example.clickup.entitiy.workspace.IshchilarSoni;
import com.example.clickup.entitiy.workspace.Reklama;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.service.workspaceServicePackage.IshchilarSoniVaReklamaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ishchilarSoniVaReklama")
public class IshchilarSoniVaReklamaController {
    @Autowired
    IshchilarSoniVaReklamaService ishchilarSoniVaReklamaService;
    @PostMapping("/addIshchilarSoni")
    public HttpEntity<?> AddIshchilarSoni(@RequestBody IshchilarSoni ishchilarSoni){
        ApiResponse apiResponse=ishchilarSoniVaReklamaService.addIshchilarSoni(ishchilarSoni);
        return ResponseEntity.status(apiResponse.isHolat()?200:209).body(apiResponse.getXabar());
    }
    @PostMapping("/addReklama")
    public HttpEntity<?> AddReklama(@RequestBody Reklama reklama){
        ApiResponse apiResponse=ishchilarSoniVaReklamaService.addReklama(reklama);
        return ResponseEntity.status(apiResponse.isHolat()?200:209).body(apiResponse.getXabar());
    }
}
