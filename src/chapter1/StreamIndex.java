package chapter1;

public class StreamIndex{
    private int index;
    StreamIndex(int index){
        this.index = index;
    }
    public int incrementAndGet(){
        int result = this.index++;
        return result;
    }
}
