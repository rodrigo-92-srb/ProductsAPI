package br.com.api.products.repositories;

import br.com.api.products.models.ProductModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRespository extends CrudRepository<ProductModel, Long> {


}
