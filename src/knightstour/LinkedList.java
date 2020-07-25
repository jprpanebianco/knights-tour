package knightstour;

public class LinkedList<T>{
    
    private Node<T> first;
    private Node<T> current;
    private int size;

    public LinkedList(){
        first = null;
        current = first;
        size = 0;
    }

    public boolean empty(){
        return (size == 0);   
    }

    public int size(){
        return size;
    }     

    public void insertFirst(T newData){      
        Node newFirst = new Node(newData);
        newFirst.setNext(first);
        first = newFirst;
        current = first;
        size++;
    }

    public Node deleteFirst(){
        Node temp = first;
        first = first.getNext();
        size--;
        return temp;   
    }

    public boolean search(T key){
        boolean result = false;
        Node current = first;
        while(current != null){
            if(current.getData() == key){
                result = true;
                return result;  
            }
            else{
                current = current.getNext();
            }
        }
        return result;    
    }

    public void traverse(){
        Node curr = first;
        while(curr != null){
            curr.displayNode ();
            System.out.print ("-");
            curr = curr.getNext();  
        }
        System.out.println ();   
    }
    
    public boolean hasNext(){
        if (current.getNext() !=null){
            return true;
        } 
        else{
            current = first;
            return false;
        }
    }
    
    public T next(){
        Node n = current;
        current = current.getNext();
        return (T) n.getData();
    }
}

