package Pascal_compiler;
import java.util.*;
public class calc1{
    public static String integer="INTEGER";
    public static String plus="PLUS";
    public static String eof="EOF";
    public static String minus="MINUS";
    public static String multiply="MULTIPLY";
    public static String divide="DIVIDE";
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
        public void error(String error) throws my_exception
        {
            if(error.compareTo("input")==0)
            {
                throw new my_exception("Error parsing input");
            }
            if(error.compareTo("/0")==0)
            {
                throw new my_exception("Divide by 0 not possible");
            }
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
            if(text.charAt(pos)=='*'){ pos++;
            return new token(multiply,""+text.charAt(pos-1));}
            if(text.charAt(pos)=='/'){ pos++;
            return new token(divide,""+text.charAt(pos-1));}
            error("input");
            return new token("","");
        }
        public void eat(token a) throws my_exception
        {
            if(current_token.type.compareTo(a.type)==0)
            current_token=get_next_token();
            else
            error("input");
        }
        public int expr() throws my_exception
        {
            current_token=get_next_token();
            int result=Integer.MIN_VALUE;
            token left=current_token;
            eat(new token(integer,"2"));
            result=Integer.parseInt(left.value);
            while(current_token.type.compareTo(eof)!=0)
            {
            token op=current_token;
            eat(op);
            token right=current_token;
            eat(new token(integer,"2"));
            if(op.type.compareTo(plus)==0)
            result=result+Integer.parseInt(right.value);
            else if(op.type.compareTo(minus)==0)
            result=result-Integer.parseInt(right.value);
            else if(op.type.compareTo(multiply)==0)
            result=result*Integer.parseInt(right.value);
            else if(op.type.compareTo(divide)==0)
            {
                if(right.value.charAt(0)=='0')
                {
                    error("/0");
                }
                result=result/Integer.parseInt(right.value);
            }
        }
            return result;
        }
    }
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