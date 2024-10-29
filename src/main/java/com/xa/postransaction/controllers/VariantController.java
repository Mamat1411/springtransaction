package com.xa.postransaction.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xa.postransaction.dtos.requests.VariantRequest;
import com.xa.postransaction.dtos.responses.VariantResponse;
import com.xa.postransaction.entities.Variant;
import com.xa.postransaction.services.VariantService;

@RestController
@RequestMapping("/api/variant")
@CrossOrigin("http://localhost:9002")
public class VariantController {
    
    @Autowired
    VariantService variantService;

    @GetMapping("")
    public ResponseEntity<?> getAllVariants() {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        try {
            List<Variant> variants = variantService.getAllVariants();
            List<VariantResponse> variantResponses = variants.stream().
                        map(variant -> modelMapper.map(variant, VariantResponse.class)).collect(Collectors.toList());
            resultMap.put("status", "200");
            resultMap.put("message", "success");
            resultMap.put("data", variantResponses);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", "500");
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getVariantById(@PathVariable("id") Long id) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        try {
            Variant variant = variantService.getVariantById(id);
            VariantResponse variantResponse = modelMapper.map(variant, VariantResponse.class);
            resultMap.put("status", "200");
            resultMap.put("message", "success");
            resultMap.put("data", variantResponse);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", "500");
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("")
    public ResponseEntity<?> saveVariant(@RequestBody VariantRequest variantRequest) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        try {
            Variant variant = modelMapper.map(variantRequest, Variant.class);
            variantService.saveVariant(variant);
            resultMap.put("status", "200");
            resultMap.put("message", "success");
            resultMap.put("data", variant);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", "500");
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateVariantById(@PathVariable("id") Long id, @RequestBody VariantRequest variantRequest) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        try {
            Variant variant = variantService.getVariantById(id);
            modelMapper.map(variant, Variant.class);
            variantService.saveVariant(variant);
            resultMap.put("status", "200");
            resultMap.put("message", "success");
            resultMap.put("data", variant);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", "500");
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVariantById(@PathVariable("id") Long id) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        try {
            Variant variant = variantService.getVariantById(id);
            variantService.deleteVariant(variant.getId());
            resultMap.put("status", "200");
            resultMap.put("message", "success");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", "500");
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
