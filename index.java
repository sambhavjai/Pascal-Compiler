import java.util.Scanner;
public class index{
    public static void main(String args[])
    {
        Scanner scn=new Scanner(System.in);
        while(true)
	        {
	        try{
	            System.out.print("calc -> ");
	            String text=scn.nextLine();
	            if(text.compareTo("eof")==0)
	            break;
                lexer lex=new lexer(text);
                parser parse=new parser(lex);
	            interpreter obj=new interpreter(parse);
	            int result=obj.calculate();
	            System.out.println(result);
	        }
	        catch(my_exception e)
	        {
	            System.out.println(e.getMessage());
	        }
	    }
	        scn.close();
    }
}