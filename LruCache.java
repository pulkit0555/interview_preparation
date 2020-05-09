import java.util.*;

class Node{
  Node prev;
  Node next;
  int value;
  int key;

  Node(int key,int value){
    this.key=key;
    this.value=value;
    this.next=null;
    this.prev=null;
  }
}


class Dll{
  Node front;
  Node tail;

  public Dll(){
    this.front=null;
    this.tail=null;
  }

}



class LRUCache {

  HashMap<Integer,Node> map;
  Dll dll;
  int capacity;
  public LRUCache(int capacity) {
    this.capacity=capacity;
    this.dll=new Dll();
    this.map=new HashMap<Integer,Node>();
  }

  public void removeNode(Node find){
    if(find.prev!=null){
      find.prev.next=find.next;
    }
    else{
      dll.front=find.next;
    }

    if(find.next!=null){
      find.next.prev=find.prev;
    }
    else{
      dll.tail=find.prev;
    }

  }

  public void moveToFront(Node find){
    find.next=dll.front;
    find.prev=null;


    if(dll.front!=null){
      dll.front.prev=find;
    }
    dll.front=find;

    if(dll.tail==null){
      dll.tail=find;
    }
  }


  public int get(int key) {
    if(!map.containsKey(key)){
      return -1;
    }

    Node find=map.get(key);
    removeNode(find);
    moveToFront(find);

    return find.value;


  }

  public void put(int key, int value) {

    if(map.containsKey(key)){

      Node find=map.get(key);
      find.value=value;

      removeNode(find);
      moveToFront(find);


    }
    else{

      Node node=new Node(key,value);

      if(map.size()==capacity){

        Node last=dll.tail;
        map.remove(last.key);
        removeNode(last);

      }
      moveToFront(node);
      map.put(key,node);

    }



  }

}

public class LruCache{

  public static void main(String args[]){
    LRUCache obj = new LRUCache(3);

    obj.put(1,2);
    obj.put(4,4);
    obj.put(1,5);
    obj.put(2,9);
    int param_1 = obj.get(1);
    System.out.println(param_1);
    param_1 = obj.get(2);
    System.out.println(param_1);

  }

}