package simulator.wrapper.wrappers;

import simulator.gates.combinational.And;
import simulator.gates.combinational.Not;
import simulator.gates.combinational.Or;
import simulator.network.Link;
import simulator.wrapper.Wrapper;

public class AluControl extends Wrapper {

	public AluControl(String label, String stream, Link... links) {
		super(label, stream, links);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		And and1= new And("A1");
		And and2= new And("A2");
		And and3= new And("A3");
		Or or1= new Or("O1");
		Or or2= new Or("O2");
		Or or3 = new Or("O3");
		Not not1= new Not("N1");
		Not not2= new Not("N2");
		Not not3= new Not("N3");
		not1.addInput(getInput(0));
		and1.addInput(not1.getOutput(0),getInput(0));//Operation3
		and2.addInput(getInput(1),getInput(3));
		or1.addInput(getInput(0),and2.getOutput(0));//Operation2
		not2.addInput(getInput(1));
		not3.addInput(getInput(4));
		or2.addInput(not2.getOutput(0),not3.getOutput(0));//Operation1
		or3.addInput(getInput(2),getInput(5));
		and3.addInput(getInput(1),or3.getOutput(0));//Operation0
		addOutput(and3.getOutput(0));
		addOutput(or2.getOutput(0));
		addOutput(or1.getOutput(0));
		addOutput(and1.getOutput(0));
		
		
	}
	
}
