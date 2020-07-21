package simulator.wrapper;

import simulator.gates.combinational.*;
import simulator.network.Link;

public class Xor32 extends Wrapper {

	public Xor32(String label, String stream, Link... links) {
		super(label, stream, links);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		//[63-32] -> operand 2 , [31 - 0] -> operand 1   first operand1 added
		
		for (int i = 0 ; i < (inputSize/2) ; i++) {
			Xor xo1 = new Xor("XOR",  getInput(i) , getInput(i+(inputSize/2)));
			addOutput(xo1.getOutput(0));
		}
		
	}

}
