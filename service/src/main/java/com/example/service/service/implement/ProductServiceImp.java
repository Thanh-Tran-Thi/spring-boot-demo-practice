package com.example.service.service.implement;

import com.example.service.entity.Product;
import com.example.service.repository.PaginationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.service.repository.ProductRepository;
import com.example.service.service.ProductService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PaginationRepository repository;

    @Override
    public Product createOrUpdateProduct(Product product) {
        if (product.getId() != null) {
            Optional<Product> optionalProduct = productRepository.findById(product.getId());
            if (optionalProduct.isPresent()) {
                Product newProduct = optionalProduct.get();
                newProduct.setName(product.getName());
                newProduct.setDescription(product.getDescription());
                newProduct.setImagePath(product.getImagePath());
                newProduct.setPrice(product.getPrice());
                newProduct = productRepository.save(newProduct);
                return newProduct;
            }
            return null;
        } else {
            return productRepository.save(product);
        }
    }

   @Override
    public void deleteProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent())
            productRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteProductByIds(Long[] ids) {
        Arrays.asList(ids).forEach(i->{
            productRepository.deleteById(i);
        });
    }

    @Override
    public Page<Product> getAllProductsWithPagination(Pageable pageable) {
        Page<Product> productPage = repository.findAll(pageable);
        return productPage.map(product -> this.convertPagination(product));
    }

    @Override
    public Optional<Product> viewProduct(Long id) {
        return productRepository.findById(id);
    }

    private Product convertPagination(Product product) {
        Product currentProduct = new Product();
        currentProduct.setId(product.getId());
        currentProduct.setName(product.getName());
        currentProduct.setDescription(product.getDescription());
        currentProduct.setImagePath(product.getImagePath());
        currentProduct.setPrice(product.getPrice());
        return currentProduct;
    }
}
