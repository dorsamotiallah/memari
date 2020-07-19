package simulator.wrapper.wrappers;

import simulator.gates.combinational.And;
import simulator.gates.combinational.Not;
import simulator.gates.combinational.Or;
import simulator.network.Link;
import simulator.wrapper.Wrapper;

public class Mux2To1 extends Wrapper {

	public Mux2To1(String label, String stream, Link... links) {
		super(label, stream, links);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		Not not0 = new Not("N0", getInput(0));
        And[] ands = new And[2];
        for (int i = 0; i < 2; ++i)
            ands[i] = new And("A" + i, getInput(i +1));
        ands[0].addInput(not0.getOutput(0));
        ands[1].addInput(getInput(0));
        Or or = new Or("Or");
        for (int i = 0; i < 2; ++i)
            or.addInput(ands[i].getOutput(0));

            addOutput(or.getOutput(0));
		
	}

}
