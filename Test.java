import java.nio.ByteBuffer;



public class Test {
    public void test(String args[]) {
    }
    public static void main(String[] args) {
        byte[] ba = new byte[4];
        byte[] ba1 = new byte[4];
        ba[0] = 'a';
        ba[1] = 'b';
        ba[2] = 'c';
        ba[3] = 'd';
        ByteBuffer bb = ByteBuffer.wrap(ba);
        bb.get(ba1, 0, 2);
        bb.get(ba1, 0, 2);
        System.out.println("hellow test");
        bb = ByteBuffer.wrap(ba1);
        System.out.println(bb.toString());
    }
}
