package br.com.api.products.controllers;

import br.com.api.products.models.ProductModel;
import br.com.api.products.models.ProductResponseModel;
import br.com.api.products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService ps;

    @GetMapping("/")
    public String route(){
        return "Products API working...";
    }

    @GetMapping("/list")
    public Iterable<ProductModel> list(){
        return ps.list();
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductModel pm, String action){
        return ps.createOrUpdate(pm, "create");
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ProductModel pm, String action){
        return ps.createOrUpdate(pm, "update");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductResponseModel> delete(@PathVariable Long id){
        return ps.remove(id);
    }
}
