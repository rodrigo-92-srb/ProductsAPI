package br.com.api.products.controllers;

import br.com.api.products.models.ProductModel;
import br.com.api.products.models.ProductResponseModel;
import br.com.api.products.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/products")
@Tag(name = "Product Controller", description = "Operations about products")
public class ProductController {

    @Autowired
    private ProductService ps;

    @GetMapping("/list")
    @Operation(summary = "List all products", description = "Return a list of all products.")
    public Iterable<ProductModel> list(){
        return ps.list();
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new product", description = "Create a new product with request body informations")
    public ResponseEntity<?> create(@RequestBody ProductModel pm, String action){
        return ps.createOrUpdate(pm, "create");
    }

    @PutMapping("/update")
    @Operation(summary = "Update a product", description = "Update a product with request body informations")
    public ResponseEntity<?> update(@RequestBody ProductModel pm, String action){
        return ps.createOrUpdate(pm, "update");
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a product", description = "Delete a product by Id")
    public ResponseEntity<ProductResponseModel> delete(@PathVariable Long id){
        return ps.remove(id);
    }
}
