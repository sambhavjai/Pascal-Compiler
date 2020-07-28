import java.util.*;
public class lexer{
    String text;
    int pos;
    char current_char;
    HashMap<String,token> reserved_keywords;
    public lexer(String text)
    {
        this.text=text;
        pos=0;
        current_char=text.charAt(pos);
        reserved_keywords=new HashMap<>();
        reserved_keywords.put("BEGIN",new token("BEGIN","BEGIN"));
        reserved_keywords.put("END",new token("END","END"));
    }
    public void advance()
    {
        pos++;
        if(pos==text.length())
        current_char='\0';
        else
        current_char=text.charAt(pos);
    }
    public void skip_whitespace()
    {
        while(current_char==' ')
        {
            advance();
        }
    }
    public String integer()
    {
        String ans="";
        while(current_char>='0'&&current_char<='9')
        {
            ans=ans+current_char;
            advance();
        }
        return ans;
    }
    public char peek()
    {
        if(pos+1==text.length())
        return '\0';
        else
        return text.charAt(pos+1);
    }
    public token id()
    {
        String result="";
        while(current_char!='\0'&&Character.isLetterOrDigit(current_char))
        {
            result=result+current_char;
            advance();
        }
        if(reserved_keywords.containsKey(result))
        return reserved_keywords.get(result);
        return new token("id",result);
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
            else if(current_char>='0'&&current_char<='9')
            {
                return new token("integer",integer());
            }
            else if(current_char=='*')
	        {
	            advance();
	            return new token("multiply","*");
	        }
	        else if(current_char=='/')
	        {
	            advance();
	            return new token("divide","/");
	        }
	        else if(current_char=='+')
	        {
	            advance();
	            return new token("plus","+");
	        }
	        else if(current_char=='-')
	        {
	            advance();
	            return new token("minus","-");
			}
			else if(current_char=='(')
			{
				advance();
				return new token("lparen","(");
			}
			else if(current_char==')')
			{
				advance();
				return new token("rparen",")");
            }
            else if(Character.isLetterOrDigit(current_char))
            {
                return id();
            }
            else if(current_char==':'&&peek()=='=')
            {
                advance();
                advance();
                return new token("assign",":=");
            }
            else if(current_char==';')
            {
                advance();
                return new token("semi",";");
            }
            else if(current_char=='.')
            {
                advance();
                return new token("dot",".");
            }
			else
			error();
	    }
	    return new token("eof","None");
    }
    public void error() throws my_exception
    {
        throw new my_exception("Invalid input");
    }
}