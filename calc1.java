package Pascal_compiler;
import java.util.*;
public class calc1{
    public static String integer="INTEGER";
    public static String plus="PLUS";
    public static String eof="EOF";
    public static String minus="MINUS";
    public static class token{
        String type;
        String value;
        public token(String type,String value)
        {
            this.type=type;
            this.value=value;
        }
        public String str()
        {
            return "Token("+this.type+" , "+this.value+")";
        }
    }
    public static class my_exception extends Exception{
        public my_exception(String s)
        {
            super(s);
        }
    }
    public static class Interpreter{
        String text;
        int pos;
        token current_token;
        public Interpreter(String text,int pos,token curr)
        {
            this.text=text;
            this.pos=pos;
            this.current_token=curr;
        }
        public void error() throws my_exception
        {
            throw new my_exception("Error parsing input");
        }
        public token get_next_token() throws my_exception
        {
            if(pos>=text.length())
            return new token(eof,"None");
            while(pos<text.length()&&text.charAt(pos)==' ')
            {
                pos++;
            }
            if(pos>=text.length())
            return new token(eof,"None");
            if(text.charAt(pos)>='0'&&text.charAt(pos)<='9')
            {
             String c="";
             while(pos<text.length()&&text.charAt(pos)>='0'&&text.charAt(pos)<='9')
             {
                c=c+text.charAt(pos);
                pos++;
             }
            return new token(integer,c);
            }
            if(text.charAt(pos)=='+')
            { pos++;
            return new token(plus,""+text.charAt(pos-1));}
            if(text.charAt(pos)=='-'){ pos++;
            return new token(minus,""+text.charAt(pos-1));}
            error();
            return new token("","");
        }
        public void eat(token a) throws my_exception
        {
            if(current_token.type.compareTo(a.type)==0)
            current_token=get_next_token();
            else
            error();
        }
        public int expr() throws my_exception
        {
            current_token=get_next_token();

            token left=current_token;
            eat(new token(integer,"2"));

            token op=current_token;
            eat(op);

            token right=current_token;
            eat(new token(integer,"2"));
            
            int result=Integer.MIN_VALUE;
            if(op.type.compareTo(plus)==0)
            result=Integer.parseInt(left.value)+Integer.parseInt(right.value);
            else if(op.type.compareTo(minus)==0)
            result=Integer.parseInt(left.value)-Integer.parseInt(right.value);
            return result;
        }
    }
    public static void main(String args[])
    {
        Scanner scn=new Scanner(System.in);
        while(true)
        {
        try{
            String text=scn.nextLine();
            if(text.compareTo("eof")==0)
            break;
            Interpreter obj=new Interpreter(text,0,null);
            int result=obj.expr();
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