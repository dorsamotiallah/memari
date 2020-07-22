package simulator.wrapper.wrappers;

import simulator.control.Simulator;
import simulator.network.Link;
import simulator.wrapper.Wrapper;

public class Shift extends Wrapper {

	public Shift(String label, String stream, Link... links) {
		super(label, stream, links);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		for (int i = 0 ; i < inputSize ; i++) {
			addOutput(getInput(i));
		}
		
		
		addOutput(Simulator.falseLogic);
		addOutput(Simulator.falseLogic);
	}

}
