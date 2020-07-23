package simulator.wrapper.wrappers;

import simulator.Sample;
import simulator.gates.combinational.And;
import simulator.gates.combinational.Not;
import simulator.gates.combinational.Or;
import simulator.network.Link;
import simulator.wrapper.Wrapper;

public class MainControl extends Wrapper{

	public MainControl(String label, String stream, Link... links) {
		super(label, stream, links);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void initialize() {
		Not not0 = new Not("not0",getInput(0));
		Not not1 = new Not("not1",getInput(1));
		Not not2 = new Not("not2",getInput(2));
		Not not3 = new Not("not3",getInput(3));
		Not not4 = new Not("not4",getInput(4));
		Not not5 = new Not("not5",getInput(5));
		And and3 = new And("and3",not0.getOutput(0),not1.getOutput(0),not2.getOutput(0),not3.getOutput(0),not4.getOutput(0),not5.getOutput(0));
		And and2 = new And("and2",getInput(0),not1.getOutput(0),not2.getOutput(0),not3.getOutput(0),getInput(4),getInput(5));
		And and1 = new And("and1",getInput(0),not1.getOutput(0),getInput(2),not3.getOutput(0),getInput(4),getInput(5));
		And and0 = new And("and0",not0.getOutput(0),not1.getOutput(0),not2.getOutput(0),getInput(3),not4.getOutput(0),not5.getOutput(0));
		Or or1 = new Or("or1",and2.getOutput(0),and1.getOutput(0));
		Or or0 = new Or("or0",and3.getOutput(0),and2.getOutput(0));
		And and5 = new And("and5",not0.getOutput(0),not1.getOutput(0),not2.getOutput(0),not3.getOutput(0),getInput(4),not5.getOutput(0));//jump
		Not notj = new Not("notj",and5.getOutput(0));
		And RWJ = new And("rwj",notj.getOutput(0),or0.getOutput(0));
		
		addOutput(and3.getOutput(0));
		addOutput(and2.getOutput(0));
		addOutput(and2.getOutput(0));
		addOutput(and1.getOutput(0));
		addOutput(and0.getOutput(0));
		addOutput(and3.getOutput(0));
		addOutput(and0.getOutput(0));
		addOutput(or1.getOutput(0));
		addOutput(RWJ.getOutput(0));
		addOutput(and5.getOutput(0));
		
	}
	

}
