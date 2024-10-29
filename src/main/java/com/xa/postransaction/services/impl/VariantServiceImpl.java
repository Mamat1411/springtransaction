package com.xa.postransaction.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xa.postransaction.entities.Variant;
import com.xa.postransaction.repositories.VariantRepository;
import com.xa.postransaction.services.VariantService;

@Service
public class VariantServiceImpl implements VariantService{

    @Autowired
    VariantRepository variantRepository;

    @Override
    public List<Variant> getAllVariants() {
        return variantRepository.findAll();
    }

    @Override
    public Variant getVariantById(Long id) {
        return variantRepository.findById(id).orElse(null);
    }

    @Override
    public void saveVariant(Variant variant) {
        variantRepository.save(variant);
    }

    @Override
    public void deleteVariant(Long id) {
        Variant variant = variantRepository.findById(id).orElse(null);
        variantRepository.deleteById(variant.getId());
    }
}
