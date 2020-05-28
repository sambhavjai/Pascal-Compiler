public class parser{
        lexer lex;
	    token current_token;
	    public parser(lexer lex) throws my_exception
	    {
	        this.lex=lex;
	        current_token=lex.get_next_token();
	    }
	    public void error() throws my_exception
	    {
	        throw new my_exception("Invalid Syntax");
	    }
	    public void eat(token a) throws my_exception
	    {
	        if(current_token.type.equals(a.type))
	        current_token=lex.get_next_token();
	        else
	        error();
	    }
	    public node factor() throws my_exception
	    {
			token curr=current_token;
			if(current_token.type.equals("plus"))
			{
				eat(new token("plus","+"));
				return factor();
			}
			if(current_token.type.equals("minus"))
			{
				eat(new token("minus","-"));
				return new node(null,curr,factor());
			}
	        if(current_token.type.equals("integer"))
	        {
	            eat(new token("integer","2"));
	            return new node(null,curr,null);
	        }
	        else if(current_token.type.equals("lparen"))
	        {
	            eat(new token("lparen","("));
	            node ans=expr();
	            eat(new token("rparen",")"));
	            return ans;
	        }
	        return null;
	    }
	    public node term() throws my_exception
	    {
	        node ans=factor();
	        while(current_token.type.equals("multiply")||current_token.type.equals("divide"))
	        {
	        token curr=current_token;
	        if(current_token.type.equals("multiply"))
	        {
	            eat(new token("multiply","*"));
	        }
	        if(current_token.type.equals("divide"))
	        {
	            eat(new token("divide","/"));
	        }
	        ans=new node(ans,curr,factor());
	        }
	        return ans;
	    }
	    public node expr() throws my_exception
	    {
	        node ans=term();
	        while(current_token.type.equals("plus")||current_token.type.equals("minus"))
	        {
	        token curr=current_token;
	        if(current_token.type.equals("plus"))
	        {
	            eat(new token("plus","+"));
	        }
	        if(current_token.type.equals("minus"))
	        {
	            eat(new token("minus","-"));
	        }
	        ans=new node(ans,curr,term());
	        }
	        return ans;
	    }
}