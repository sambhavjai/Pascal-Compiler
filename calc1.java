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
	    public static class Lexer{
	        String text;
	        int pos;
	        char current_char;
	        public Lexer(String text)
	        {
	            this.text=text;
	            this.pos=0;
	            this.current_char=text.charAt(pos);
	        }
	        public void error() throws my_exception
	        {
	            throw new my_exception("Invalid character");
	        }
	        public void advance()
	        {
	            pos++;
	            if(pos>text.length()-1)
	            {
	                current_char='\0';
	            }
	            else
	            {
	                current_char=text.charAt(pos);
	            }
	        }
	        public void skip_whitespace()
	        {
	            while(current_char!='\0'&&current_char==' ')
	            {
	                advance();
	            }
	        }
	        public String integer()
	        {
	            String result="";
	            while(current_char!='\0'&&current_char>='0'&&current_char<='9')
	            {
	                result=result+current_char;
	                advance();
	            }
	            return result;
	        }
	        public token get_next_token() throws my_exception
	        {
	            while(current_char!='\0')
	            {
	                if(current_char==' ')
	                {
	                    skip_whitespace();
	                    continue;
	                }
	                if(current_char>='0'&&current_char<='9')
	                {
	                    return new token(integer,integer());
	                }
	                if(current_char=='*')
	                {
	                	advance();
	                    return new token(multiply,"*");
	                }
	                if(current_char=='/')
	                {
	                	advance();
	                    return new token(divide,"/");
	                }
	                if(current_char=='+')
	                {
	                	advance();
	                    return new token(plus,"+");
	                }
	                if(current_char=='-')
	                {
	                	advance();
	                    return new token(minus,"-");
	                }
	            }
	            return new token(eof,"None");
	        }
	    }
	    public static class Interpreter{
	        Lexer lex;
	        token current_token;
	        public Interpreter(Lexer lex) throws my_exception
	        {
	            this.lex=lex;
	            this.current_token=lex.get_next_token();
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
	        public void eat(token a) throws my_exception
	        {
	            if(current_token.type.compareTo(a.type)==0)
	            {
	            current_token=lex.get_next_token();}
	            else
	            error("input");
	        }
	        public int factor() throws my_exception
	        {
	            token temp=current_token;
	            eat(new token(integer,"2"));
	            return Integer.parseInt(temp.value);
			}
			public int term() throws my_exception
			{
				int result=factor();
				while(current_token.type.compareTo(multiply)==0||current_token.type.compareTo(divide)==0)
				{
					token temp=current_token;
					if(temp.type.compareTo(multiply)==0)
					{
						eat(new token(multiply,"*"));
						result=result*factor();
					}
					else if(temp.type.compareTo(divide)==0)
					{
						eat(new token(divide,"/"));
						int right=factor();
						if(right==0)
						{
							error("/0");
						}
						result=result/right;
					}
				}
				return result;
			}
	        public int expr() throws my_exception
	        {
	            int result=term();
				while(current_token.type.compareTo(plus)==0||current_token.type.compareTo(minus)==0)
	            {
	            token op=current_token;
	            if(op.type.compareTo(plus)==0)
	            {
	            eat(new token(plus,"+"));
	            result=result+term();
	            }
	            else if(op.type.compareTo(minus)==0)
	            {
	            eat(new token(minus,"-"));
	            result=result-term();
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
	            Lexer lex=new Lexer(text);
	            Interpreter obj=new Interpreter(lex);
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