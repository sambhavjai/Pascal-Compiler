import java.util.*;
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
		public node program() throws my_exception
		{
			node result=compound_statement();
			eat(new token("dot","."));
			return result;
		}
		public node compound_statement() throws my_exception
		{
			eat(new token("BEGIN","BEGIN"));
			ArrayList<node> result=statement_list();
			eat(new token("END","END"));
			node root=new node(null,null,null);
			root.children=new ArrayList<>(result);
			return root;
		}
		public ArrayList<node> statement_list() throws my_exception
		{
			node temp=statement();
			ArrayList<node> result=new ArrayList<>();
			result.add(temp);
			while(current_token.type.equals("semi"))
			{
				eat(new token("semi",";"));
				node z=statement();
				if(z!=null)
				result.add(z);
			}
			if(current_token.type.equals("id"))
			error();
			return result;
		}
		public node statement() throws my_exception
		{
			if(current_token.type.equals("BEGIN"))
			return compound_statement();
			else if(current_token.type.equals("id"))
			return assignment_statement();
			else
			return null;
		}
		public node assignment_statement() throws my_exception
		{
			node left=variable();
			token curr=current_token;
			eat(new token("assign",":="));
			node right=expr();
			return new node(left,curr,right);
		}
		public node variable() throws my_exception
		{
			node ans=new node(null,current_token,null);
			eat(new token("id","var"));
			return ans;
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
			else
			return variable();
		}
		public node parse() throws my_exception
		{
			node ans=program();
			if(!current_token.type.equals("eof"))
			error();
			return ans;
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