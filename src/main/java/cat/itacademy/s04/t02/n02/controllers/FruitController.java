package cat.itacademy.s04.t02.n02.controllers;

import cat.itacademy.s04.t02.n02.model.Fruit;
import cat.itacademy.s04.t02.n02.services.FruitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/fruit")
@Validated
public class FruitController {

    private static final Logger logger = LoggerFactory.getLogger(FruitController.class);

    @Autowired
    private FruitService fruitService;

    @PostMapping("/add")
    public ResponseEntity<Fruit> addFruit(@Valid @RequestBody Fruit fruit) {
        logger.info("Adding fruit: {}", fruit.getName());
        Fruit savedFruit = fruitService.addFruit(fruit);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFruit);
    }

    @PutMapping("/update")
    public ResponseEntity<Fruit> updateFruit(@Valid @RequestBody Fruit fruit) {
        if (fruit.getName() == null || fruit.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Fruit name cannot be empty or null");
        }
        logger.info("Updating fruit: {}", fruit.getId());
        return ResponseEntity.ok(fruitService.updateFruit(fruit));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFruit(@PathVariable int id) {
        logger.warn("Deleting fruit with ID: {}", id);
        fruitService.deleteFruit(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Fruit> getFruitById(@PathVariable int id) {
        logger.info("Fetching fruit with ID: {}", id);
        Fruit fruit = fruitService.getFruit(id);
        return ResponseEntity.ok(fruit);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Fruit>> getAllFruits() {
        logger.info("Fetching all fruits");
        return ResponseEntity.ok(fruitService.getFruits());
    }
}