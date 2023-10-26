package com.example.clickup.service.workspaceServicePackage;

import com.example.clickup.entitiy.workspace.IshchilarSoni;
import com.example.clickup.entitiy.workspace.Reklama;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.repository.workspaceRepo.IshchilarSoniRepository;
import com.example.clickup.repository.workspaceRepo.ReklamaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IshchilarSoniVaReklamaService {
    @Autowired
    IshchilarSoniRepository ishchilarSoniRepository;
    @Autowired
    ReklamaRepository reklamaRepository;

    public ApiResponse addIshchilarSoni(IshchilarSoni ishchilarSoni) {
        Optional<IshchilarSoni> byIshchilarSoni = ishchilarSoniRepository.findByIshchilarSoni(ishchilarSoni.getIshchilarSoni());
        if (byIshchilarSoni.isPresent()){
            return new ApiResponse("Bunday ishchilar soni oldin ham yaratilgan", false);
        }
        ishchilarSoniRepository.save(ishchilarSoni);
        return new ApiResponse("Ishchilar soni saqlandi", true);
    }

    public ApiResponse addReklama(Reklama reklama) {
        Optional<Reklama> byNomi = reklamaRepository.findByNomi(reklama.getNomi());
        if (byNomi.isPresent()){
            return new ApiResponse("Bunday reklama oldin ham ro'yxatdan o'tkazilgan", false);
        }
        reklamaRepository.save(reklama);
        return new ApiResponse("Reklama muvoffaqiyatli saqlandi", true);
    }
}
