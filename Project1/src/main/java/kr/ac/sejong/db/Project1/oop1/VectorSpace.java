package kr.ac.sejong.db.Project1.oop1;

public class VectorSpace extends Position {
	int z;
	
	public VectorSpace() {super(0, 0); z = 0;}
	public VectorSpace(int x, int y, int z) {super(x, y); this.z = z;}
	
	public void setXYZ(int x, int y, int z) {setXY(x, y); this.z = z;}
	public int getZ() {return z;}
}
