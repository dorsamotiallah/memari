package simulator.wrapper.wrappers;

import simulator.control.Simulator;
import simulator.network.Link;
import simulator.wrapper.Wrapper;

public class Shiftleft5 extends Wrapper{

	public Shiftleft5(String label, String stream, Link... links) {
		super(label, stream, links);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub


		
		int j=0;
		for (int i = 5 ; i < inputSize ; i++) {
			addOutput(getInput(i));
			++j;
		}
		
		
		addOutput(Simulator.falseLogic);
		addOutput(Simulator.falseLogic);
		addOutput(Simulator.falseLogic);
		addOutput(Simulator.falseLogic);
		addOutput(Simulator.falseLogic);
		j=j+5;

		
	}
}
