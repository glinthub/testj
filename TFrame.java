import java.awt.Frame;
import java.awt.Panel;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;

class B {
	B() {System.out.println("B()");}
	B(int i) {
	    System.out.println("B(i)");
	    m_i = i;
	}
	protected void copyTo(B tgt) {tgt.m_i = m_i;}
	private
	    int m_i = 0;
}

class D extends B {
    D() {
        super(3);
        System.out.println("D()");
    }
}

public class TFrame {
    public static void main(String[] args) {
        @SuppressWarnings("unused")
        D d = new D();
        B b1 = new B(1);
        B b2 = new B(3);
        b1.copyTo(b2);
    }
}




