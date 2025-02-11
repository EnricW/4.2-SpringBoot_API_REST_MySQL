package cat.itacademy.s04.t02.n02.services;

import cat.itacademy.s04.t02.n02.exception.FruitAlreadyExistsException;
import cat.itacademy.s04.t02.n02.exception.FruitNotFoundException;
import cat.itacademy.s04.t02.n02.model.Fruit;
import cat.itacademy.s04.t02.n02.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitServiceImpl implements FruitService {

    @Autowired
    private FruitRepository fruitRepository;

    public Fruit addFruit(Fruit fruit) {
        validateFruit(fruit);
        if (fruitRepository.findByName(fruit.getName()).isPresent()) {
            throw new FruitAlreadyExistsException("Fruit with name " + fruit.getName() + " already exists");
        }
        return fruitRepository.save(fruit);
    }

    public void deleteFruit(int id) {
        if (!fruitRepository.existsById(id)) {
            throw new FruitNotFoundException(id);
        }
        fruitRepository.deleteById(id);
    }

    public Fruit updateFruit(Fruit fruit) {
        validateFruit(fruit);
        fruitRepository.findById(fruit.getId())
                .orElseThrow(() -> new FruitNotFoundException(fruit.getId()));
        return fruitRepository.save(fruit);
    }

    public Fruit getFruit(int id) {
        return fruitRepository.findById(id)
                .orElseThrow(() -> new FruitNotFoundException(id));
    }

    public List<Fruit> getFruits() {
        return fruitRepository.findAll();
    }

    private void validateFruit(Fruit fruit) {
        if (fruit.getName() == null || fruit.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Fruit name cannot be empty");
        }
        if (fruit.getWeight() <= 0) {
            throw new IllegalArgumentException("Fruit weight must be positive");
        }
    }
}
