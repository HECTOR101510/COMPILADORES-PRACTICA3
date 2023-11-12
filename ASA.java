import java.util.List;

public class ASA implements Parser{
    private int i = 0;
    private boolean hayErrores = false;
    private Token preanalisis;
    private final List<Token> tokens;
    public ASA(List<Token> tokens){
        this.tokens=tokens;
        preanalisis=this.tokens.get(i);
    }

    public void analisis(){
        
    }


    @Override
    public boolean parse(){
        analisis();
        return !hayErrores;
    }
}
