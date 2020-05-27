public class lexer{
    String text;
    int pos;
    char current_char;
    public lexer(String text)
    {
        this.text=text;
        pos=0;
        current_char=text.charAt(pos);
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