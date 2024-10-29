package com.xa.postransaction.services;

import java.util.List;

import com.xa.postransaction.entities.Variant;

public interface VariantService {
    List<Variant> getAllVariants();
    Variant getVariantById(Long id);
    void saveVariant(Variant variant);
    void deleteVariant(Long id);
}
