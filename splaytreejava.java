import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Kartik
 *
 */
class SplayNode
	 {    
		SplayNode left, right, parent;
	     int element;
	 
	     
	     public SplayNode()
	     {
	         this(0, null, null, null);
	     }          
	     
	     public SplayNode(int ele, Object object, Object object2, Object object3)
	     {
	         this(ele, null, null, null);
	     } 
	     public SplayNode(int ele, SplayNode left, SplayNode right, SplayNode parent)
	     {
	         this.left = left;
	         this.right = right;
	         this.parent = parent;
	         this.element = ele;         
	     }    
	 }
	 
	 class SplayTree
	 {
	     public SplayNode root;
	     public int count = 0;
	 
	     public SplayTree()
	     {
	         root = null;
	     }
	 
	     public boolean isEmpty()
	     {
	         return root == null;
	     }
	 
	     public void clear()
	     {
	         root = null;
	     }
	 
	     public void insert(int ele)
	     {
	    	 SplayNode z = root;
	    	 SplayNode p = null;
	         while (z != null)
	         {
	             p = z;
	             if (ele < p.element)
	                 z = z.right;
	             else
	                 z = z.left;
	         }
	         z = new SplayNode();
	         z.element = ele;
	         z.parent = p;
	         if (p == null)
	             root = z;
	         else if (ele < p.element)
	             p.right = z;
	         else
	             p.left = z;
	         Splay(z);
	         count++;
	     }
	     public void leftChildAsParent(SplayNode c, SplayNode p)
	     {
	         if ((c == null) || (p == null) || (p.left != c) || (c.parent != p))
	             throw new RuntimeException("Incorrect");
	 
	         if (p.parent != null)
	         {
	             if (p == p.parent.left)
	                 p.parent.left = c;
	             else 
	                 p.parent.right = c;
	         }
	         if (c.right != null)
	             c.right.parent = p;
	 
	         c.parent = p.parent;
	         p.parent = c;
	         p.left = c.right;
	         c.right = p;
	     }
	 
	     public void rightChildAsParent(SplayNode c, SplayNode p)
	     {
	         if ((c == null) || (p == null) || (p.right != c) || (c.parent != p))
	             throw new RuntimeException("Incorrect");
	         if (p.parent != null)
	         {
	             if (p == p.parent.left)
	                 p.parent.left = c;
	             else
	                 p.parent.right = c;
	         }
	         if (c.left != null)
	             c.left.parent = p;
	         c.parent = p.parent;
	         p.parent = c;
	         p.right = c.left;
	         c.left = p;
	     }
	 
	     private void Splay(SplayNode z)
	     {
	         while (z.parent != null)
	         {
	        	 SplayNode Parent = z.parent;
	        	 SplayNode GrandParent = Parent.parent;
	             if (GrandParent == null)
	             {
	                 if (z == Parent.left)
	                     leftChildAsParent(z, Parent);
	                 else
	                     rightChildAsParent(z, Parent);                 
	             } 
	             else
	             {
	                 if (z == Parent.left)
	                 {
	                     if (Parent == GrandParent.left)
	                     {
	                    	 leftChildAsParent(Parent, GrandParent);
	                    	 leftChildAsParent(z, Parent);
	                     }
	                     else 
	                     {
	                    	 leftChildAsParent(z, z.parent);
	                    	 rightChildAsParent(z, z.parent);
	                     }
	                 }
	                 else 
	                 {
	                     if (Parent == GrandParent.left)
	                     {
	                    	 rightChildAsParent(z, z.parent);
	                    	 leftChildAsParent(z, z.parent);
	                     } 
	                     else 
	                     {
	                    	 rightChildAsParent(Parent, GrandParent);
	                    	 rightChildAsParent(z, Parent);
	                     }
	                 }
	             }
	         }
	         root = z;
	     }
	 
	     public void delete(int ele)
	     {
	    	 SplayNode node = findNode(ele);
	    	 delete(node);
	     }
	 
	     private void delete(SplayNode node)
	     {
	         if (node == null)
	             return;
	 
	         Splay(node);
	         if( (node.left != null) && (node.right !=null))
	         { 
	        	 SplayNode min = node.left;
	             while(min.right!=null)
	                 min = min.right;
	 
	             min.right = node.right;
	             node.right.parent = min;
	             node.left.parent = null;
	             root = node.left;
	         }
	         else if (node.right != null)
	         {
	             node.right.parent = null;
	             root = node.right;
	         } 
	         else if( node.left !=null)
	         {
	             node.left.parent = null;
	             root = node.left;
	         }
	         else
	         {
	             root = null;
	         }
	         node.parent = null;
	         node.left = null;
	         node.right = null;
	         node = null;
	         count--;
	     }
	 
	     public int countNodes()
	     {
	         return count;
	     }
	 
	     public boolean find(int val)
	     {
	         return findNode(val) != null;
	     }
	     private SplayNode findNode(int ele)
	     {
	    	 SplayNode z = root;
	         while (z != null)
	         {
	             if (ele < z.element)
	                 z = z.right;
	             else if (ele > z.element)
	                 z = z.left;
	             else
	                 return z;
	         }
	         return null;
	     }    
	          
	 }
	 
	 public class splaytreejava{
	   
	    public static void main(String[] args){
	    	//long lStartTime = new Date().getTime();
	    	long startTime = System.nanoTime();
	    	
	        Scanner scan = new Scanner(System.in);
	        SplayTree spt = new SplayTree(); 
	        try{
	    		BufferedReader br = new BufferedReader(new FileReader("C:\\Kartik\\NCAT\\inputfile_large.txt"));
	    		String sCurrentLine; 
	    		while ((sCurrentLine = br.readLine()) != null) {
	    				if(sCurrentLine.startsWith("I")){
	    					
	    					spt.insert(Integer.parseInt(sCurrentLine.substring(2)));
	    					
	    					System.out.println("Inserted: "+sCurrentLine.substring(2));
	    					
	    				
	    				}
	    				else if(sCurrentLine.startsWith("D")){
	    					spt.delete(Integer.parseInt(sCurrentLine.substring(2)));
	    					System.out.println("Deleted: "+Integer.parseInt(sCurrentLine.substring(2)));
	    					    				
	    				}
	    				else if(sCurrentLine.startsWith("F")){
	    					spt.find(Integer.parseInt(sCurrentLine.substring(2)));
	    					if(spt.find(Integer.parseInt(sCurrentLine.substring(2)))==true){
	    						System.out.println("Found: "+sCurrentLine.substring(2));
	    					}else{
	    						System.out.println("Not Found: "+sCurrentLine.substring(2));
	    					}
	    				
	    				}
	    				
	    			}br.close();
	    		
	    		}
	    		catch(NullPointerException np){
	    		}
	    		catch (FileNotFoundException e) {
	                System.err.println("Unable to find the file: fileName");
	            } catch (IOException e) {
	                System.err.println("Unable to read the file: fileName");
	            }catch (NumberFormatException nfe) {
	                System.err.println("Incorrect format");
	            }
	        long endTime = System.nanoTime();
			long duration = endTime - startTime;
			//System.out.println("Duration(ns)= " + duration);
			double seconds = (double)duration / 1000000000.0;
			System.out.println("Duration(s)= " + seconds);
	        
	                 
	    }
	 
}

