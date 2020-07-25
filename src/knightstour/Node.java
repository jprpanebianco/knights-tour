package knightstour;

public class Node<T>{
   
    private T data;
    private Node<T> next;

    public Node(T s){
        data = s;
    }

    public Node(T s, Node n){
        data = s;
        next = n;
    }

    public void setData(T newData){
        data = newData;
    }

    public void setNext(Node newNext){
        next = newNext;
    } 

    public T getData(){
        return data;
    }

    public Node getNext(){
        return next;
    }

    public void displayNode(){
        System.out.print(data);
    } 

}
