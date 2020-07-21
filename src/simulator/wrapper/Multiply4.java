package simulator.wrapper;

import simulator.control.Simulator;
import simulator.network.Link;

public class Multiply4 extends Wrapper {

	public Multiply4(String label, String stream, Link[] links) {
		super(label, stream, links);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

		
		for (int i = 2 ; i < inputSize ; i++) {
			addOutput(getInput(i));
		}
		
		
		addOutput(Simulator.falseLogic);
		addOutput(Simulator.falseLogic);
	}

}
