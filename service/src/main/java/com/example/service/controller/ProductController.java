package com.example.service.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.service.entity.ApiResponse;
import com.example.service.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.example.service.service.FileStorageService;
import com.example.service.service.ProductService;
import com.example.service.utils.AppConstants;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = AppConstants.PRODUCT_URI)
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    FileStorageService fileService;

    ModelMapper mapper = new ModelMapper();

//    @GetMapping(value = AppConstants.PRODUCT_URI)
//    public List<Product> listAllProducts(){
//        return productService.listProducts();
//    }

    @GetMapping
    public ApiResponse<List<Product>> listAll(){
        return new ApiResponse<List<Product>>(AppConstants.SUCCESS_CODE, AppConstants.CREATE_SUCCESS_MSG, productService.listProducts());
    }

    @GetMapping(value = "/list")
    public ApiResponse<?> getAllProductsWithPagination(@RequestParam(name = "page", required = false, defaultValue = "0")  Integer page,
                                                        @RequestParam(name = "size", required = false, defaultValue = "2") Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Product> productList = productService.getAllProductsWithPagination(pageable);
        return new ApiResponse<>(AppConstants.SUCCESS_CODE, "fg", productList);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<Product> createOrUpdateProduct(Product pro, @RequestParam(required = true, value = AppConstants.FILE_PARAM) MultipartFile file) throws JsonParseException, JsonMappingException, IOException {
        String fileName = fileService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.DOWNLOAD_PATH).path(fileName).toUriString();
        pro.setImagePath(fileDownloadUri);
        productService.createOrUpdateProduct(pro);
        return new ApiResponse<Product>(AppConstants.SUCCESS_CODE, AppConstants.CREATE_SUCCESS_MSG);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteProductById(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
        return HttpStatus.OK;
    }

    @GetMapping(value = AppConstants.DOWNLOAD_URI)
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
        Resource resource = fileService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (contentType == null) {
            contentType = AppConstants.DEFAULT_CONTENT_TYPE;
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION, String.format(AppConstants.FILE_DOWNLOAD_HTTP_HEADER, resource.getFilename())).body(resource);
    }
}
