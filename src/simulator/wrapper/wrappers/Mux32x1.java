package simulator.wrapper.wrappers;

import simulator.gates.combinational.And;
import simulator.gates.combinational.Not;
import simulator.gates.combinational.Or;
import simulator.network.Link;
import simulator.wrapper.Wrapper;

public class Mux32x1 extends Wrapper {

	public Mux32x1(String label, String stream, Link... links) {
		super(label, stream, links);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		Not not0 = new Not("N0", getInput(0));
        Not not1 = new Not("N1", getInput(1));
        Not not2 = new Not("N2", getInput(2));
        Not not3 = new Not("N3", getInput(3));
        Not not4 = new Not("N4", getInput(4));
        And[] ands = new And[32];
        for (int i = 0; i < 32; ++i)
            ands[i] = new And("A" + i, getInput(i + 5));
        ands[0].addInput(not0.getOutput(0),not1.getOutput(0),not2.getOutput(0),not3.getOutput(0),not4.getOutput(0));
        ands[1].addInput(getInput(0),not1.getOutput(0),not2.getOutput(0),not3.getOutput(0),not4.getOutput(0));
        ands[2].addInput(not0.getOutput(0),getInput(1),not2.getOutput(0),not3.getOutput(0),not4.getOutput(0));
        ands[3].addInput(getInput(0),getInput(1),not2.getOutput(0),not3.getOutput(0),not4.getOutput(0));
        ands[4].addInput(not0.getOutput(0),not1.getOutput(0),getInput(2),not3.getOutput(0),not4.getOutput(0));
        ands[5].addInput(getInput(0),not1.getOutput(0),getInput(2),not3.getOutput(0),not4.getOutput(0));
        ands[6].addInput(not0.getOutput(0),getInput(1),getInput(2),not3.getOutput(0),not4.getOutput(0));
        ands[7].addInput(getInput(0),getInput(1),getInput(2),not3.getOutput(0),not4.getOutput(0));
        ands[8].addInput(not0.getOutput(0),not1.getOutput(0),not2.getOutput(0),getInput(3),not4.getOutput(0));
        ands[9].addInput(getInput(0),not1.getOutput(0),not2.getOutput(0),getInput(3),not4.getOutput(0));
        ands[10].addInput(not0.getOutput(0),getInput(1),not2.getOutput(0),getInput(3),not4.getOutput(0));
        ands[11].addInput(getInput(0),getInput(1),not2.getOutput(0),getInput(3),not4.getOutput(0));
        ands[12].addInput(not0.getOutput(0),not1.getOutput(0),getInput(2),getInput(3),not4.getOutput(0));
        ands[13].addInput(getInput(0),not1.getOutput(0),getInput(2),getInput(3),not4.getOutput(0));
        ands[14].addInput(not0.getOutput(0),getInput(1),getInput(2),getInput(3),not4.getOutput(0));
        ands[15].addInput(getInput(0),getInput(1),getInput(2),getInput(3),not4.getOutput(0));
        ands[16].addInput(not0.getOutput(0),not1.getOutput(0),not2.getOutput(0),not3.getOutput(0),getInput(4));
        ands[17].addInput(getInput(0),not1.getOutput(0),not2.getOutput(0),not3.getOutput(0),getInput(4));
        ands[18].addInput(not0.getOutput(0),getInput(1),not2.getOutput(0),not3.getOutput(0),getInput(4));
        ands[19].addInput(getInput(0),getInput(1),not2.getOutput(0),not3.getOutput(0),getInput(4));
        ands[20].addInput(not0.getOutput(0),not1.getOutput(0),getInput(2),not3.getOutput(0),getInput(4));
        ands[21].addInput(getInput(0),not1.getOutput(0),getInput(2),not3.getOutput(0),getInput(4));
        ands[22].addInput(not0.getOutput(0),getInput(1),getInput(2),not3.getOutput(0),getInput(4));
        ands[23].addInput(getInput(0),getInput(1),getInput(2),not3.getOutput(0),getInput(4));
        ands[24].addInput(not0.getOutput(0),not1.getOutput(0),not2.getOutput(0),getInput(3),getInput(4));
        ands[25].addInput(not0.getOutput(0),not1.getOutput(0),not2.getOutput(0),getInput(3),getInput(4));
        ands[26].addInput(not0.getOutput(0),not1.getOutput(0),not2.getOutput(0),getInput(3),getInput(4));
        ands[27].addInput(not0.getOutput(0),not1.getOutput(0),not2.getOutput(0),getInput(3),getInput(4));
        ands[28].addInput(not0.getOutput(0),not1.getOutput(0),getInput(2),getInput(3),getInput(4));
        ands[29].addInput(not0.getOutput(0),not1.getOutput(0),getInput(2),getInput(3),getInput(4));
        ands[30].addInput(not0.getOutput(0),not1.getOutput(0),getInput(2),getInput(3),getInput(4));
        ands[31].addInput(not0.getOutput(0),not1.getOutput(0),getInput(2),getInput(3),getInput(4));
        Or or = new Or("Or");
        for (int i = 0; i < 32; ++i)
            or.addInput(ands[i].getOutput(0));

            addOutput(or.getOutput(0));
        
		
	}

}
