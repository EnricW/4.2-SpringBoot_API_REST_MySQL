package cat.itacademy.s04.t02.n02.exception;

public class FruitAlreadyExistsException extends RuntimeException{
    public FruitAlreadyExistsException(String name) { super("Fruit with name '" + name + "' already exists."); }
}