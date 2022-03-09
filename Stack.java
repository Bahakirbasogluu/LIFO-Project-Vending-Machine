import java.util.*;

public class Stack<Items> {

    private int size;    private String Name;    private final ArrayList<Items> data;

    public String getName() {
        return Name;
    }

    public Stack(String Name){ this.size=0;    this.Name = Name;    data = new ArrayList<>(); }

    public Stack() { this.size=0;    data = new ArrayList<>(); }

    public void push(Items obj){ data.add(obj);    size ++; }

    public Items pop(){
        if (data.size()==0) throw new EmptyStackException();
        else { size--;    return data.remove(data.size()-1); } }

    public int size(){ return size; }

    public  Items get(int index){
        if (data.size()==0) throw new EmptyStackException();
        else{ return data.get(index); } }

    public boolean isEmpty()
    {
        return data.size() == 0;
    }
}