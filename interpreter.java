public class interpreter{
    node root;
    parser obj;
  //  int result;
    public interpreter(parser obj) throws my_exception
    {
        this.obj=obj;
        this.root=obj.expr();
     //   result=0;
    }
    public int calculate() throws my_exception
    {
        int result= postorder(root);
        return result;
    }
    public void error() throws my_exception
    {
        throw new my_exception("Divide by 0 not possible");
    }
    public int postorder(node root) throws my_exception
    {
        if(root==null)
            return 0;
            int left=postorder(root.left);
            int right=postorder(root.right);
            if(root.val.type.equals("integer"))
            return Integer.parseInt(root.val.value);
            if(root.val.type.equals("plus"))
            return left+right;
            else if(root.val.type.equals("minus")&&root.left==null)
            return -1*right;
            else if(root.val.type.equals("minus"))
            return left-right;
            else if(root.val.type.equals("multiply"))
            return left*right;
            else if(root.val.type.equals("divide"))
            {
                if(right==0)
                error();
                return left/right;
            }
            return 0;
    }
}