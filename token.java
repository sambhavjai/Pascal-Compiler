public static class token{
       public static String integer="INTEGER";
	    public static String plus="PLUS";
	    public static String eof="EOF";
	    public static String minus="MINUS";
	    public static String multiply="MULTIPLY";
		public static String divide="DIVIDE";
		public static String lparen="LPAREN";
		public static String rparen="RPAREN";
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
}