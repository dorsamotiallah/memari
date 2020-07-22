package simulator.wrapper;

import simulator.network.Link;

public class SignExtend16 extends Wrapper {

	public SignExtend16(String label, String stream, Link[] links) {
		super(label, stream, links);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		for(int i = inputSize-1; i < 32 ;i ++ ) {
			setOutput(i, getInput(inputSize-1));
		}
	}

}
