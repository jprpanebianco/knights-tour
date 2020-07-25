package knightstour;

public class Stack<T>{
    
    private Node top;
    private int size;

    public Stack(){
        top = null;
        size = 0;
    }          

    public boolean empty(){
        return (top == null);
    }

    public void push(T s){
        Node<T> newNode = new Node(s);
        newNode.setNext(top);
        top = newNode;
        size++;     
    }

    public T pop(){
        T s;
        s =(T) top.getData();
        top = top.getNext();
        size--;
        return s;
    }

    public T ontop(){
        T s = pop();
        push(s);
        return s;
    }

    public int size(){
        return size;
    }
    
    public void traverse(){
        LinkedList<T> list = new LinkedList();
        int end = size;
        while(this.top != null){
            list.insertFirst(this.pop());
        }
        list.traverse();
    }

}
