package simulator.wrapper;

import simulator.gates.combinational.*;
import simulator.network.Link;

public class Nor32 extends Wrapper {

	public Nor32(String label, String stream, Link... links) {
		super(label, stream, links);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize() {
		//System.out.printf("this is Nor\n");
		// TODO Auto-generated method stub
		//[63-32] -> operand 2 , [31 - 0] -> operand 1  first operand1 added 
		
		for (int i = 0 ; i < (inputSize/2) ; i++) {
			Nor no1 = new Nor("NOR",  getInput(i) , getInput(i+(inputSize/2)));
			addOutput(no1.getOutput(0));
		}
	
	}

}
