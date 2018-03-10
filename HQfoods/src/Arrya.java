class Buffer1 {
    private int size;
    private String[] buffer;
    private int node;

    Buffer1(int size) {
        this.size = size;
        node = 0;
        buffer = new String[size];
    }

    public boolean Insert(String guy) {
        if (node <= size) {
            buffer[node++] = guy;
            return true;
        } else {
            return false;
        }
    }

    public void print() {
        for (int i = 0; i < node; i++) System.out.println(buffer[i]);
    }
}

class Array{
    public static void  main(String [] args){
        Buffer1 buf = new Buffer1(4);
        buf.Insert("abc");
        buf.Insert("qwe");
        buf.Insert("wer");
        buf.print();
    }
}