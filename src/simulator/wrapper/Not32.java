package simulator.wrapper;

import simulator.gates.combinational.*;
import simulator.network.Link;

public class Not32 extends Wrapper {

	public Not32(String label, String stream, Link... links) {
		super(label, stream, links);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		//System.out.println("this is not "+inputSize);
		for (int i = 0 ; i < inputSize ; i++ ) {
			Not nt1 = new Not("NOT1" , getInput(i)) ;
			
			addOutput(nt1.getOutput(0));
		}
		
		
		
		
	}

}
