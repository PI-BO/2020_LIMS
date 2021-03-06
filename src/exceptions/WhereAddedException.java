package exceptions;

/**
 * @author Narwutsch Dominic
 * created on 05.03.2021
 */
public class WhereAddedException extends Exception{
    public WhereAddedException(){
        super();
    }

    public WhereAddedException(String message){
        super(message);
    }
}
