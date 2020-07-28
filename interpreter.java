import java.util.*;
public class interpreter{
    static HashMap<String,Integer> gat=new HashMap<>(); // global assignment table
    parser obj;
    int result;
    public interpreter(parser obj) throws my_exception
    {
        this.obj=obj;
        result=0;
    }
    public void interpret() throws my_exception
    {
        node tree=obj.parse();
        visit_compound(tree);
    }
    public void visit_compound(node tree) throws my_exception
    {
        for(int i=0;i<tree.children.size();i++)
        {
            if(tree.children.get(i).val==null)
            {
                visit_compound(tree.children.get(i));
            }
            else if(tree.children.get(i).val.type.equals("assign"))
            {
                visit_assign(tree.children.get(i));
            }
        }
    }
    public void visit_assign(node tree) throws my_exception
    {
        String var_name=tree.left.val.value;
        int var_value=Integer.MIN_VALUE;
        if(tree.right.val.type.equals("minus")&&tree.right.left==null)
        var_value=visit_unminus(tree.right);
        else if(tree.right.val.type.equals("plus")||(tree.right.val.type.equals("minus")&&tree.right.left!=null)||tree.right.val.type.equals("multiply")||tree.right.val.type.equals("divide"))
        var_value=visit_binop(tree.right);
        else if(tree.right.val.type.equals("integer"))
        var_value=visit_integer(tree.right);
        else if(tree.right.val.type.equals("id"))
        var_value=visit_var(tree.right);
        gat.put(var_name,var_value);
    }
    public int visit_var(node tree) throws my_exception
    {
        if(!gat.containsKey(tree.val.value))
        {
        error("v");
        return 0;
        }
        else
        {
            int ans=gat.get(tree.val.value);
            return ans;
        }
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
        else if(tree.right.val.type.equals("id"))
        ans=visit_var(tree.right);
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
            else if(tree.left.val.type.equals("id"))
            left=visit_var(tree.left);
            int right=0;
            if(tree.right.val.type.equals("integer"))
            right=visit_integer(tree.right);
            else if(tree.right.val.type.equals("plus")||(tree.right.val.type.equals("minus")&&tree.right.left!=null)||tree.right.val.type.equals("multiply")||tree.right.val.type.equals("divide"))
            right=visit_binop(tree.right);
            else if(tree.right.val.type.equals("minus")&&tree.right.left==null)
            right=visit_unminus(tree.right);
            else if(tree.right.val.type.equals("id"))
            right=visit_var(tree.right);
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
            else if(tree.left.val.type.equals("id"))
            left=visit_var(tree.left);
            int right=0;
            if(tree.right.val.type.equals("integer"))
            right=visit_integer(tree.right);
            else if(tree.right.val.type.equals("plus")||(tree.right.val.type.equals("minus")&&tree.right.left!=null)||tree.right.val.type.equals("multiply")||tree.right.val.type.equals("divide"))
            right=visit_binop(tree.right);
            else if(tree.right.val.type.equals("minus")&&tree.right.left==null)
            right=visit_unminus(tree.right);
            else if(tree.right.val.type.equals("id"))
            right=visit_var(tree.right);
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
            else if(tree.left.val.type.equals("id"))
            left=visit_var(tree.left);
            int right=0;
            if(tree.right.val.type.equals("integer"))
            right=visit_integer(tree.right);
            else if(tree.right.val.type.equals("plus")||(tree.right.val.type.equals("minus")&&tree.right.left!=null)||tree.right.val.type.equals("multiply")||tree.right.val.type.equals("divide"))
            right=visit_binop(tree.right);
            else if(tree.right.val.type.equals("minus")&&tree.right.left==null)
            right=visit_unminus(tree.right);
            else if(tree.right.val.type.equals("id"))
            right=visit_var(tree.right);
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
            else if(tree.left.val.type.equals("id"))
            left=visit_var(tree.left);
            int right=0;
            if(tree.right.val.type.equals("integer"))
            right=visit_integer(tree.right);
            else if(tree.right.val.type.equals("plus")||(tree.right.val.type.equals("minus")&&tree.right.left!=null)||tree.right.val.type.equals("multiply")||tree.right.val.type.equals("divide"))
            right=visit_binop(tree.right);
            else if(tree.right.val.type.equals("minus")&&tree.right.left==null)
            right=visit_unminus(tree.right);
            else if(tree.right.val.type.equals("id"))
            right=visit_var(tree.right);
            if(right==0)
            error("0");
            return left/right;
        }
        return 0;
    } 
    public int visit_integer(node tree) throws my_exception
    { 
        return Integer.parseInt(tree.val.value);
    }
    public void error(String e) throws my_exception
    {
        if(e.equals("0"))
        throw new my_exception("Divide by 0 not possible");
        else if(e.equals("v"))
        throw new my_exception("Variable not found");
    }
}