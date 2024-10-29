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
import org.springframework.web.bind.annotation.RestController;

import com.xa.postransaction.dtos.requests.CategoryRequest;
import com.xa.postransaction.dtos.responses.CategoryResponse;
import com.xa.postransaction.entities.Category;
import com.xa.postransaction.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/category")
@CrossOrigin("http://localhost:9002")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> getAllCategories() {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        try {
            List<Category> categories = categoryService.getAllCategories();

            // Using Model Mapper
            List<CategoryResponse> categoryResponses = categories.stream()
                    .map(category -> modelMapper.map(category, CategoryResponse.class)).collect(Collectors.toList());

            // // Manual Way
            // List<CategoryResponse> categoryResponses = new ArrayList<>();
            // for (Category category : categories) {
            //     CategoryResponse categoryResponse = new CategoryResponse();
            //     categoryResponse.setId(category.getId());
            //     categoryResponse.setName(category.getName());
            //     categoryResponse.setDescription(category.getDescription());
            //     categoryResponse.setCreatedAt(category.getCreatedAt());
            //     categoryResponse.setCreatedBy(category.getCreatedBy());
            //     categoryResponse.setUpdatedAt(category.getUpdatedAt());
            //     categoryResponse.setUpdatedBy(category.getUpdatedBy());
            //     categoryResponse.setIsDeleted(category.getIsDeleted());
            //     categoryResponses.add(categoryResponse);
            // }

            resultMap.put("status", "200");
            resultMap.put("message", "success");
            resultMap.put("data", categoryResponses);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", "500");
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryRequest categoryRequest) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        try {
            // // Manual Way
            // Category category = new Category();
            // category.setName(categoryRequest.getName());
            // category.setDescription(categoryRequest.getDescription());

            // Using Model Mapper
            Category category = modelMapper.map(categoryRequest, Category.class);
            categoryService.saveCategory(category);
            resultMap.put("status", "200");
            resultMap.put("message", "success");
            resultMap.put("data", category);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", "500");
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        try {
            Category category = categoryService.getCategoryById(id);
            CategoryResponse categoryResponse = modelMapper.map(category, CategoryResponse.class);
            resultMap.put("status", "200");
            resultMap.put("message", "success");
            resultMap.put("data", categoryResponse);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", "500");
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @PutMapping("/{id}")
    public ResponseEntity<?> editCategoryById(@PathVariable("id") Long id, @RequestBody CategoryRequest categoryRequest) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        try {
            Category category = categoryService.getCategoryById(id);
            modelMapper.map(categoryRequest, category);
            categoryService.saveCategory(category);
            resultMap.put("status", "200");
            resultMap.put("message", "success");
            resultMap.put("data", category);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", "500");
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> editCategory(@PathVariable("id") Long id, @RequestBody CategoryRequest categoryRequest) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        // useful for strict binding between entity and dto
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        // useful for updating fields based on what exists in request
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        try {
            Category category = categoryService.getCategoryById(id);
            modelMapper.map(categoryRequest, category);
            categoryService.saveCategory(category);
            resultMap.put("status", "200");
            resultMap.put("message", "success");
            resultMap.put("data", category);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", "500");
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable("id") Long id) {
       LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
       try {
        Category category = categoryService.getCategoryById(id);
        categoryService.deleteCategory(category.getId());
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
