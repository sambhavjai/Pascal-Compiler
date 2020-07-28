public class index{
    public static void main(String args[]) throws Exception
    {
		// String filename="C:\\Users\\SAMBHAV JAIN\\SAM\\Compiler\\Pascal_compiler\\input.txt";
		// String data=new String(Files.readAllBytes(Paths.get(filename)));
		String data="BEGIN BEGIN number := 2; a := number; b := 10 * a + 10 * number / 4; c := a - - b; END; x := 11; END.";
	        try{
                lexer lex=new lexer(data);
                parser parse=new parser(lex);
	            interpreter obj=new interpreter(parse);
	            obj.interpret();
	        }
	        catch(my_exception e)
	        {
	            System.out.println(e.getMessage());
			}
    }
}