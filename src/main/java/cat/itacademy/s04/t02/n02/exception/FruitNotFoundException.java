package cat.itacademy.s04.t02.n02.exception;

public class FruitNotFoundException extends RuntimeException {
    public FruitNotFoundException(int id) {
        super("Fruit not found with id: " + id);
    }
}
