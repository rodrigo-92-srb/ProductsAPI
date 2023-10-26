package br.com.api.products.services;

import br.com.api.products.models.ProductModel;
import br.com.api.products.models.ProductResponseModel;
import br.com.api.products.repositories.ProductRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRespository pr;

    @Autowired
    private ProductResponseModel prm;

    public Iterable<ProductModel> list(){
        return pr.findAll();
    }

    public ResponseEntity<?> createOrUpdate(ProductModel pm, String action){

        if(pm.getName().equals("")){
            prm.setMessage("Name is required!");
            return new ResponseEntity<ProductResponseModel>(prm, HttpStatus.BAD_REQUEST);
        } else if (pm.getBrand().equals("")) {
            prm.setMessage("Brand is required!");
            return new ResponseEntity<ProductResponseModel>(prm, HttpStatus.BAD_REQUEST);
        }
        else{
            if(action.equals("create")){
                return new ResponseEntity<ProductModel>(pr.save(pm), HttpStatus.CREATED);
            }else{
                return new ResponseEntity<ProductModel>(pr.save(pm), HttpStatus.OK);
            }

        }
    }

    public ResponseEntity<ProductResponseModel> remove(Long id){

        pr.deleteById(id);

        prm.setMessage("The product was removed with success!");
        return new ResponseEntity<ProductResponseModel>(prm, HttpStatus.OK);
    }
}
