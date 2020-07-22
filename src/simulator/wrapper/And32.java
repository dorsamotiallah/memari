package simulator.wrapper;

import simulator.gates.combinational.*;
import simulator.network.Link;

public class And32 extends Wrapper {

	public And32(String label, String stream, Link... links) {
		super(label, stream, links);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize() {
		//System.out.printf("this is And\n");
		// TODO Auto-generated method stub
		//[63-32] -> operand 2 , [31 - 0] -> operand 2 , first operand 1 must be added
		
		for (int i = 0 ; i < (inputSize/2) ; i++) {
			And a1 = new And("AND",  getInput(i) , getInput(i+(inputSize/2)));
			addOutput(a1.getOutput(0));
		}
	}

}
