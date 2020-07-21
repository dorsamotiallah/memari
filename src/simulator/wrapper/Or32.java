package simulator.wrapper;

import simulator.gates.combinational.*;
import simulator.network.Link;

public class Or32 extends Wrapper {

	public Or32(String label, String stream, Link... links) {
		super(label, stream, links);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize() {
		//System.out.printf("this is or\n");
		// TODO Auto-generated method stub
		//[63-32] -> operand 2 , [31 - 0] -> operand 1  first operand1 added
		
		for (int i = 0 ; i < (inputSize/2) ; i++) {
			Or o1 = new Or("OR",  getInput(i) , getInput(i+(inputSize/2)));
			addOutput(o1.getOutput(0));
		}
	}

}
