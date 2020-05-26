public class token{
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