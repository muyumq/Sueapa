package org.example.app;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class MainController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewProduct(@RequestParam String name,
                                              @RequestParam String description,
                                              @RequestParam Integer price,
                                              @RequestParam String category,
                                              @RequestParam String brand,
                                              @RequestParam String imageName){
        Product p = new Product();
        p.setName(name);
        p.setDescription(description);
        p.setPrice(price);
        p.setCategory(category);
        p.setBrand(brand);
        p.setImageName(imageName);
        productRepository.save(p);
        return "Saved!";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Product> getAllProduct(){
        return productRepository.findAll();
    }
}
