package cat.itacademy.s04.t02.n02.services;

import cat.itacademy.s04.t02.n02.exception.FruitAlreadyExistsException;
import cat.itacademy.s04.t02.n02.exception.FruitNotFoundException;
import cat.itacademy.s04.t02.n02.model.Fruit;
import cat.itacademy.s04.t02.n02.repository.FruitRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FruitServiceImpl implements FruitService {

    private static final Logger logger = LoggerFactory.getLogger(FruitServiceImpl.class);

    @Autowired
    private FruitRepository fruitRepository;

    @Override
    public Fruit addFruit(Fruit fruit) {
        logger.info("Attempting to add fruit: {}", fruit.getName());
        if (fruitRepository.findByName(fruit.getName()).isPresent()) {
            logger.warn("Fruit already exists: {}", fruit.getName());
            throw new FruitAlreadyExistsException(fruit.getName());
        }
        Fruit savedFruit = fruitRepository.save(fruit);
        logger.info("Fruit added successfully: {}", savedFruit.getName());
        return savedFruit;
    }

    @Override
    public void deleteFruit(int id) {
        logger.warn("Attempting to delete fruit with ID: {}", id);
        if (!fruitRepository.existsById(id)) {
            logger.error("Fruit not found with ID: {}", id);
            throw new FruitNotFoundException(id);
        }
        fruitRepository.deleteById(id);
        logger.info("Fruit deleted successfully with ID: {}", id);
    }

    @Override
    public Fruit updateFruit(Fruit fruit) {
        logger.info("Attempting to update fruit with ID: {}", fruit.getId());
        fruitRepository.findById(fruit.getId())
                .orElseThrow(() -> {
                    logger.error("Fruit not found with ID: {}", fruit.getId());
                    return new FruitNotFoundException(fruit.getId());
                });

        if (fruitRepository.existsByNameAndIdNot(fruit.getName(), fruit.getId())) {
            logger.warn("Fruit name conflict: {}", fruit.getName());
            throw new FruitAlreadyExistsException(fruit.getName());
        }

        Fruit updatedFruit = fruitRepository.save(fruit);
        logger.info("Fruit updated successfully: {}", updatedFruit.getName());
        return updatedFruit;
    }

    @Override
    public Fruit getFruit(int id) {
        logger.info("Fetching fruit with ID: {}", id);
        return fruitRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Fruit not found with ID: {}", id);
                    return new FruitNotFoundException(id);
                });
    }

    @Override
    public List<Fruit> getFruits() {
        logger.info("Fetching all fruits");
        return fruitRepository.findAll();
    }
}