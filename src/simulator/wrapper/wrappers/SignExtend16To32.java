package simulator.wrapper.wrappers;

import simulator.network.Link;
import simulator.wrapper.Wrapper;

public class SignExtend16To32 extends Wrapper {

	public SignExtend16To32(String label, String stream, Link... links) {
		super(label, stream, links);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		//detecting sign Bit
		Link sign = getInput(0) ;
		
		//Repeat sign bit as much as needed
		for (int i = (32-inputSize) ; i > 0 ; i--) {
			addOutput(sign);
		}
		//adding our input to output
		for (int i = 0 ; i < inputSize ; i++)
			addOutput(getInput(i));

	}

}
