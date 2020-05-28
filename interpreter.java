public class interpreter{
    parser obj;
    int result;
    public interpreter(parser obj) throws my_exception
    {
        this.obj=obj;
        result=0;
    }
    public int interpret() throws my_exception
    {
        node tree=obj.parse();
        int ans=0;
        if(tree.val.type.equals("integer"))
        ans=visit_integer(tree);
        else if(tree.val.type.equals("plus")||(tree.val.type.equals("minus")&&tree.left!=null)||tree.val.type.equals("multiply")||tree.val.type.equals("divide"))
        ans=visit_binop(tree);
        else if(tree.val.type.equals("minus")&&tree.left==null)
        ans=visit_unminus(tree);
        return ans;
    }
    public int visit_unminus(node tree) throws my_exception
    {
        int ans=0;
        if(tree.right.val.type.equals("integer"))
        ans=visit_integer(tree.right);
        else if(tree.right.val.type.equals("plus")||(tree.right.val.type.equals("minus")&&tree.right.left!=null)||tree.right.val.type.equals("multiply")||tree.right.val.type.equals("divide"))
        ans=visit_binop(tree.right);
        else if(tree.right.val.type.equals("minus")&&tree.right.left==null)
        ans=visit_unminus(tree.right);
        return -1*ans;
    }
    public int visit_binop(node tree) throws my_exception
    {
        if(tree.val.type.equals("plus"))
        {
            int left=0;
            if(tree.left.val.type.equals("integer"))
            left=visit_integer(tree.left);
            else if(tree.left.val.type.equals("plus")||(tree.left.val.type.equals("minus")&&tree.left.left!=null)||tree.left.val.type.equals("multiply")||tree.left.val.type.equals("divide"))
            left=visit_binop(tree.left);
            else if(tree.left.val.type.equals("minus")&&tree.left.left==null)
            left=visit_unminus(tree.left);
            int right=0;
            if(tree.right.val.type.equals("integer"))
            right=visit_integer(tree.right);
            else if(tree.right.val.type.equals("plus")||(tree.right.val.type.equals("minus")&&tree.right.left!=null)||tree.right.val.type.equals("multiply")||tree.right.val.type.equals("divide"))
            right=visit_binop(tree.right);
            else if(tree.right.val.type.equals("minus")&&tree.right.left==null)
            right=visit_unminus(tree.right);
            return left+right;
        }
        else if(tree.val.type.equals("minus"))
        {
            int left=0;
            if(tree.left.val.type.equals("integer"))
            left=visit_integer(tree.left);
            else if(tree.left.val.type.equals("plus")||(tree.left.val.type.equals("minus")&&tree.left.left!=null)||tree.left.val.type.equals("multiply")||tree.left.val.type.equals("divide"))
            left=visit_binop(tree.left);
            else if(tree.left.val.type.equals("minus")&&tree.left.left==null)
            left=visit_unminus(tree.left);
            int right=0;
            if(tree.right.val.type.equals("integer"))
            right=visit_integer(tree.right);
            else if(tree.right.val.type.equals("plus")||(tree.right.val.type.equals("minus")&&tree.right.left!=null)||tree.right.val.type.equals("multiply")||tree.right.val.type.equals("divide"))
            right=visit_binop(tree.right);
            else if(tree.right.val.type.equals("minus")&&tree.right.left==null)
            right=visit_unminus(tree.right);
            return left-right;
        }
        else if(tree.val.type.equals("multiply"))
        {
            int left=0;
            if(tree.left.val.type.equals("integer"))
            left=visit_integer(tree.left);
            else if(tree.left.val.type.equals("plus")||(tree.left.val.type.equals("minus")&&tree.left.left!=null)||tree.left.val.type.equals("multiply")||tree.left.val.type.equals("divide"))
            left=visit_binop(tree.left);
            else if(tree.left.val.type.equals("minus")&&tree.left.left==null)
            left=visit_unminus(tree.left);
            int right=0;
            if(tree.right.val.type.equals("integer"))
            right=visit_integer(tree.right);
            else if(tree.right.val.type.equals("plus")||(tree.right.val.type.equals("minus")&&tree.right.left!=null)||tree.right.val.type.equals("multiply")||tree.right.val.type.equals("divide"))
            right=visit_binop(tree.right);
            else if(tree.right.val.type.equals("minus")&&tree.right.left==null)
            right=visit_unminus(tree.right);
            return left*right;
        }
        else if(tree.val.type.equals("divide"))
        {
            int left=0;
            if(tree.left.val.type.equals("integer"))
            left=visit_integer(tree.left);
            else if(tree.left.val.type.equals("plus")||(tree.left.val.type.equals("minus")&&tree.left.left!=null)||tree.left.val.type.equals("multiply")||tree.left.val.type.equals("divide"))
            left=visit_binop(tree.left);
            else if(tree.left.val.type.equals("minus")&&tree.left.left==null)
            left=visit_unminus(tree.left);
            int right=0;
            if(tree.right.val.type.equals("integer"))
            right=visit_integer(tree.right);
            else if(tree.right.val.type.equals("plus")||(tree.right.val.type.equals("minus")&&tree.right.left!=null)||tree.right.val.type.equals("multiply")||tree.right.val.type.equals("divide"))
            right=visit_binop(tree.right);
            else if(tree.right.val.type.equals("minus")&&tree.right.left==null)
            right=visit_unminus(tree.right);
            if(right==0)
            error();
            return left/right;
        }
        return 0;
    } 
    public int visit_integer(node tree) throws my_exception
    { 
        return Integer.parseInt(tree.val.value);
    }
    public void error() throws my_exception
    {
        throw new my_exception("Divide by 0 not possible");
    }
    // public void postorder(node root) throws my_exception
    // {
    //     if(root==null)
    //         return;
    //         postorder(root.left);
    //         postorder(root.right);
    //         if(root.val.type.equals("integer"))
    //         return Integer.parseInt(root.val.value);
    //         else if(root.val.type.equals("plus"))
    //         return left+right;
    //         else if(root.val.type.equals("minus")&&root.left==null)
    //         return -1*right;
    //         else if(root.val.type.equals("minus"))
    //         return left-right;
    //         else if(root.val.type.equals("multiply"))
    //         return left*right;
    //         else if(root.val.type.equals("divide"))
    //         {
    //             if(right==0)
    //             error();
    //             return left/right;
    //         }
    //         else if(root.children!=null)
    //         {

    //         }
    //         return 0;
    // }
}