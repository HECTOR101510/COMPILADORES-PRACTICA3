import java.util.List;
import java.util.Stack;

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
        Stack<String> stack=new Stack<>();
        stack.push("1");//se llena con el primer estado
        String x=stack.peek();//apuntamos el tope de la pila
        Boolean re=true;
        while(re){//bulce infinito
            switch (x) {
                case "1":
                    if(preanalisis.tipo.name()=="SELECT"){
                        stack.push("SELECT");
                        stack.push("3");
                        x=stack.peek();
                        i++;
                        preanalisis=this.tokens.get(i);
                    }
                    else{
                         System.out.println("Error: se esperaba un SELECT");
                         hayErrores=true;
                         re=false;
                    }
                    break;

                case "2":
                    if(preanalisis.tipo.name()=="EOF"){
                        hayErrores=false;
                        re=false;
                    }
                    else{
                        System.out.println("Error");
                        re=false;
                    }
                    break;

                case "3":
                    if(preanalisis.tipo.name()=="DISTINCT"){
                        stack.push("DISTINCT");
                        stack.push("14");
                        x=stack.peek();
                    }
                    else if(preanalisis.tipo.name()=="ASTERISCO"){
                        stack.push("ASTERISCO");
                        stack.push("25");
                        x=stack.peek();
                    }
                    else if(preanalisis.tipo.name()=="IDENTIFICADOR"){
                        stack.push("IDENTIFICADOR");
                        stack.push("19");
                        x=stack.peek();
                    }
                    else{
                        System.out.println("Error: se esperaba un DISTINCT o ASTERISCO o IDENTIFICADOR");
                        hayErrores=true;
                        re=false;
                    }
                    i++;
                    preanalisis=this.tokens.get(i);
                    break;
                
                case "4":
                    if(preanalisis.tipo.name()=="FROM"){
                        stack.push("FROM");
                        stack.push("5");
                        x=stack.peek();
                    }
                    else{
                        System.out.println("Error: se esperaba un FROM");
                        hayErrores=true;
                        re=false;
                    }
                    i++;
                    preanalisis=this.tokens.get(i);
                    break;

                case "5":
                    if(preanalisis.tipo.name()=="IDENTIFICADOR"){
                        stack.push("IDENTIFICADOR");
                        stack.push("7");
                        x=stack.peek();
                    }
                    else{
                        System.out.println("Error: se esperaba un IDENTIFICADOR");
                        hayErrores=true;
                        re=false;
                    }
                    i++;
                    preanalisis=this.tokens.get(i);
                    break;

                case "6":
                    if(preanalisis.tipo.name()=="COMA"){
                        stack.push("COMA");
                        stack.push("8");
                        x=stack.peek();
                        i++;
                        preanalisis=this.tokens.get(i);
                    }
                    else if(preanalisis.tipo.name()=="EOF"){
                        stack.pop();
                        stack.pop();
                        stack.push("EOF");
                        stack.push("2");
                        x=stack.peek();
                    }
                    else{
                        System.out.println("Error: se esperaba una COMA");
                        hayErrores=true;
                        re=false;
                    }
                    break;
                
                case "7":
                    if(preanalisis.tipo.name()=="IDENTIFICADOR"){
                        stack.push("IDENTIFICADOR");
                        stack.push("10");
                        x=stack.peek();
                        i++;
                        preanalisis=this.tokens.get(i);
                    }
                    else if(preanalisis.tipo.name()=="COMA"){
                        stack.pop();
                        stack.pop();
                        stack.push("COMA");
                        stack.push("11");
                        x=stack.peek();
                    }
                    else if(preanalisis.tipo.name()=="EOF"){
                        stack.pop();
                        stack.pop();
                        stack.push("EOF");
                        stack.push("11");
                        x=stack.peek();
                    }
                    else{
                        System.out.println("Error: se esperaba un IDENTIFICADOR");
                        hayErrores=true;
                        re=false;
                    }
                    break;
                
                case "8":
                    if(preanalisis.tipo.name()=="IDENTIFICADOR"){
                        stack.push("IDENTIFICADOR");
                        stack.push("7");
                        x=stack.peek();
                    }
                    else{
                        System.out.println("Error: se esperaba un IDENTIFICADOR");
                        hayErrores=true;
                        re=false;
                    }
                    i++;
                    preanalisis=this.tokens.get(i);
                    break;

                case "10":
                    stack.pop();
                    stack.pop();
                    if(preanalisis.tipo.name()=="COMA"){
                        stack.push("COMA");
                        stack.push("11");
                        x=stack.peek();
                    }
                    else if(preanalisis.tipo.name()=="EOF"){
                        stack.push("EOF");
                        stack.push("11");
                        x=stack.peek();
                    }
                    else{
                        System.out.println("Error: se esperaba una COMA");
                        hayErrores=true;
                        re=false;
                    }
                    break;

                case "11":
                    stack.pop();
                    stack.pop();
                    if(preanalisis.tipo.name()=="COMA"){
                        stack.push("COMA");
                        stack.push("12");
                        x=stack.peek();
                    }
                    else if(preanalisis.tipo.name()=="EOF"){
                        stack.push("EOF");
                        stack.push("12");
                        x=stack.peek();
                    }
                    else{
                        System.out.println("Error: se esperaba una COMA");
                        hayErrores=true;
                        re=false;
                    }
                    break;

                case "12":
                    stack.pop();
                    stack.pop();
                    if(preanalisis.tipo.name()=="COMA"){
                        stack.push("COMA");
                        stack.push("6");
                        x=stack.peek();
                    }
                    else if(preanalisis.tipo.name()=="EOF"){
                        stack.push("EOF");
                        stack.push("6");
                        x=stack.peek();
                    }
                    else{
                        System.out.println("Error: se esperaba una COMA");
                        hayErrores=true;
                        re=false;
                    }
                    break;

                case "13":
                    stack.pop();
                    stack.pop();
                    if(preanalisis.tipo.name()=="COMA"){
                        stack.push("COMA");
                        stack.push("6");
                        x=stack.peek();
                    }
                    else if(preanalisis.tipo.name()=="EOF"){
                        stack.push("EOF");
                        stack.push("6");
                        x=stack.peek();
                    }
                    else{
                        System.out.println("Error: se esperaba una COMA");
                        hayErrores=true;
                        re=false;
                    }
                    break;

                case "14":
                    if(preanalisis.tipo.name()=="ASTERISCO"){
                        stack.push("ASTERISCO");
                        stack.push("25");
                        x=stack.peek();
                    }
                    else if(preanalisis.tipo.name()=="IDENTIFICADOR"){
                        stack.push("IDENTIFICADOR");
                        stack.push("19");
                        x=stack.peek();
                    }
                    else{
                        System.out.println("Error: se esperaba un ASTERISCO o IDENTIFICADOR");
                        hayErrores=true;
                        re=false;
                    }
                    i++;
                    preanalisis=this.tokens.get(i);
                    break;

                case "15":
                    stack.pop();
                    stack.pop();
                    if(preanalisis.tipo.name()=="FROM"){
                        stack.push("FROM");
                        stack.push("4");
                        x=stack.peek();
                    }
                    else{
                        System.out.println("Error: se esperaba un FROM");
                        hayErrores=true;
                        re=false;
                    }
                    break;

                case "16":
                    if(preanalisis.tipo.name()=="COMA"){
                        stack.push("COMA");
                        stack.push("17");
                        x=stack.peek();
                        i++;
                        preanalisis=this.tokens.get(i);
                    }
                    else if(preanalisis.tipo.name()=="FROM"){
                        stack.push("FROM");
                        stack.push("15");
                        x=stack.peek();
                    }
                    else{
                        System.out.println("Error: se esperaba una COMA ");
                        hayErrores=true;
                        re=false;
                    }
                    
                    break;

                case "17":
                    if(preanalisis.tipo.name()=="IDENTIFICADOR"){
                        stack.push("IDENTIFICADOR");
                        stack.push("19");
                        x=stack.peek();
                    }
                    else{
                        System.out.println("Error: se esperaba un IDENTIFICADOR");
                        hayErrores=true;
                        re=false;
                    }
                    i++;
                    preanalisis=this.tokens.get(i);
                    break;

                case "18":
                    if(preanalisis.tipo.name()=="FROM"){
                        stack.push("FROM");
                        stack.push("16");
                        x=stack.peek();
                    }
                    else if(preanalisis.tipo.name()=="COMA"){
                        stack.push("COMA");
                        stack.push("16");
                        x=stack.peek();
                    }
                    else{
                        System.out.println("Error: se esperaba una COMA o FROM");
                        hayErrores=true;
                        re=false;
                    }
                    break;

                case "19":
                    if(preanalisis.tipo.name()=="PUNTO"){
                            stack.push("PUNTO");
                            stack.push("21");
                            x=stack.peek();
                            i++;
                            preanalisis=this.tokens.get(i);
                    }
                    else if(preanalisis.tipo.name()=="FROM"){
                        stack.pop();
                        stack.pop();
                        stack.push("FROM");
                        stack.push("20");
                        x=stack.peek();
                    }
                    else if(preanalisis.tipo.name()=="COMA"){
                        stack.pop();
                        stack.pop();
                        stack.push("COMA");
                        stack.push("20");
                        x=stack.peek();
                    }
                    else{
                        System.out.println("Error: se esperaba un PUNTO");
                        hayErrores=true;
                        re=false;
                    }
                    break;

                case "20":
                    if(preanalisis.tipo.name()=="FROM"){
                            stack.push("FROM");
                            stack.push("24");
                            x=stack.peek();
                    }
                    else if(preanalisis.tipo.name()=="COMA"){
                            stack.push("COMA");
                            stack.push("24");
                            x=stack.peek();
                    }
                    else{
                        System.out.println("Error: se esperaba un FROM o COMA");
                        hayErrores=true;
                        re=false;
                    }
                    break;

                case "21":
                    if(preanalisis.tipo.name()=="IDENTIFICADOR"){
                        stack.push("IDENTIFICADOR");
                        stack.push("23");
                        x=stack.peek();
                    }
                    else{
                        System.out.println("Error: se esperaba un IDENTIFICADOR");
                        hayErrores=true;
                        re=false;
                    }
                    i++;
                    preanalisis=this.tokens.get(i);
                    break;

                case "23":
                    if(preanalisis.tipo.name()=="FROM"){
                            stack.push("FROM");
                            stack.push("20");
                            x=stack.peek();
                    }
                    else if(preanalisis.tipo.name()=="COMA"){
                            stack.push("COMA");
                            stack.push("20");
                            x=stack.peek();
                    }
                    else{
                        System.out.println("Error: se esperaba un FROM o COMA");
                        hayErrores=true;
                        re=false;
                    }
                    break;

                case "24":
                    if(preanalisis.tipo.name()=="FROM"){
                            stack.push("FROM");
                            stack.push("16");
                            x=stack.peek();
                    }
                    else if(preanalisis.tipo.name()=="COMA"){
                            stack.push("COMA");
                            stack.push("16");
                            x=stack.peek();
                    }
                    else{
                        System.out.println("Error: se esperaba un FROM o COMA");
                        hayErrores=true;
                        re=false;
                    }
                    break;

                case "25":
                    stack.pop();
                    stack.pop();
                    if(preanalisis.tipo.name()=="FROM"){
                            stack.push("FROM");
                            stack.push("15");
                            x=stack.peek();
                    }
                    else{
                        System.out.println("Error: se esperaba un FROM");
                        hayErrores=true;
                        re=false;
                    }
                    break;

                case "26":
                    if(preanalisis.tipo.name()=="FROM"){
                            stack.push("FROM");
                            stack.push("4");
                            x=stack.peek();
                    }
                    else{
                        System.out.println("Error: se esperaba un FROM");
                        hayErrores=true;
                        re=false;
                    }
                    break;
                default:
                    System.out.println("Error: se ha encontrado errores");
                    re=false;
                    hayErrores=true;
                    break;
            }
        }
    }

    @Override
    public boolean parse(){
        analisis();
        return !hayErrores;
    }
}
