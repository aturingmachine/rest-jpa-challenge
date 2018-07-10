package solstice.bootcamp.jpaapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solstice.bootcamp.jpaapi.model.Product;
import solstice.bootcamp.jpaapi.repository.ProductRepository;

@RequestMapping("/products")
@RestController
public class ProductController {

  private ProductRepository productRepository;

  public ProductController(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @GetMapping("")
  public Iterable<Product> getAll() {
    return productRepository.findAll();
  }

  @GetMapping("/{id}")
  public Product getOne(@PathVariable("id") Long id) {
    return productRepository.findById(id).get();
  }

  @PostMapping("")
  public ResponseEntity create(@RequestBody Product product) {
    Product savedProduct = productRepository.save(product);

    return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public Product update(@PathVariable("id") Long id, @RequestBody Product product) {
    Product productToUpdate = productRepository.findById(id).get();
    productToUpdate.setDescription(product.getDescription());
    productToUpdate.setImage(product.getImage());
    productToUpdate.setName(product.getName());
    productToUpdate.setPrice(product.getPrice());

    return productRepository.save(productToUpdate);
  }

  public ResponseEntity delete(@PathVariable("id") Long id) {
    productRepository.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
