import java.util.*;
public class node{
    token val;
    node left;
    node right;
    ArrayList<node> children;
    public node(node left,token val,node right)
    {
        this.val=val;
        this.left=left;
        this.right=right;
        children=null;
    }
}