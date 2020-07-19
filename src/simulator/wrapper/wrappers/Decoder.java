package simulator.wrapper.wrappers;

import simulator.gates.combinational.And;
import simulator.gates.combinational.Not;
import simulator.gates.combinational.Or;
import simulator.network.Link;
import simulator.wrapper.Wrapper;

public class Decoder extends Wrapper {

	public Decoder(String label, String stream,Link... links) {
		super(label, stream,links);
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
        addOutput(not0.getOutput(0));
        And[] ands = new And[32];
        ands[0]= new And("D0",not0.getOutput(0),not1.getOutput(0),not2.getOutput(0),not3.getOutput(0),not4.getOutput(0));
        ands[1]= new And("D1",getInput(0),not1.getOutput(0),not2.getOutput(0),not3.getOutput(0),not4.getOutput(0));
        ands[2]= new And("D2",not0.getOutput(0),getInput(1),not2.getOutput(0),not3.getOutput(0),not4.getOutput(0));
        ands[3]= new And("D3",getInput(0),getInput(1),not2.getOutput(0),not3.getOutput(0),not4.getOutput(0));
        ands[4]= new And("D4",not0.getOutput(0),not1.getOutput(0),getInput(2),not3.getOutput(0),not4.getOutput(0));
        ands[5]= new And("D5",getInput(0),not1.getOutput(0),getInput(2),not3.getOutput(0),not4.getOutput(0));
        ands[6]= new And("D6",not0.getOutput(0),getInput(1),getInput(2),not3.getOutput(0),not4.getOutput(0));
        ands[7]= new And("D7",getInput(0),getInput(1),getInput(2),not3.getOutput(0),not4.getOutput(0));
        ands[8]= new And("D8",not0.getOutput(0),not1.getOutput(0),not2.getOutput(0),getInput(3),not4.getOutput(0));
        ands[9]= new And("D9",getInput(0),not1.getOutput(0),not2.getOutput(0),getInput(3),not4.getOutput(0));
        ands[10]= new And("D10",not0.getOutput(0),getInput(1),not2.getOutput(0),getInput(3),not4.getOutput(0));
        ands[11]= new And("D11",getInput(0),getInput(1),not2.getOutput(0),getInput(3),not4.getOutput(0));
        ands[12]= new And("D12",not0.getOutput(0),not1.getOutput(0),getInput(2),getInput(3),not4.getOutput(0));
        ands[13]= new And("D13",getInput(0),not1.getOutput(0),getInput(2),getInput(3),not4.getOutput(0));
        ands[14]= new And("D14",not0.getOutput(0),getInput(1),getInput(2),getInput(3),not4.getOutput(0));
        ands[15]= new And("D15",getInput(0),getInput(1),getInput(2),getInput(3),not4.getOutput(0));
        ands[16]= new And("D16",not0.getOutput(0),not1.getOutput(0),not2.getOutput(0),not3.getOutput(0),getInput(4));
        ands[17]= new And("D17",getInput(0),not1.getOutput(0),not2.getOutput(0),not3.getOutput(0),getInput(4));
        ands[18]= new And("D18",not0.getOutput(0),getInput(1),not2.getOutput(0),not3.getOutput(0),getInput(4));
        ands[19]= new And("D19",getInput(0),getInput(1),not2.getOutput(0),not3.getOutput(0),getInput(4));
        ands[20]= new And("D20",not0.getOutput(0),not1.getOutput(0),getInput(2),not3.getOutput(0),getInput(4));
        ands[21]= new And("D21",getInput(0),not1.getOutput(0),getInput(2),not3.getOutput(0),getInput(4));
        ands[22]= new And("D22",not0.getOutput(0),getInput(1),getInput(2),not3.getOutput(0),getInput(4));
        ands[23]= new And("D23",getInput(0),getInput(1),getInput(2),not3.getOutput(0),getInput(4));
        ands[24]= new And("D24",not0.getOutput(0),not1.getOutput(0),not2.getOutput(0),getInput(3),getInput(4));
        ands[25]= new And("D25",not0.getOutput(0),not1.getOutput(0),not2.getOutput(0),getInput(3),getInput(4));
        ands[26]= new And("D26",not0.getOutput(0),not1.getOutput(0),not2.getOutput(0),getInput(3),getInput(4));
        ands[27]= new And("D27",not0.getOutput(0),not1.getOutput(0),not2.getOutput(0),getInput(3),getInput(4));
        ands[28]= new And("D28",not0.getOutput(0),not1.getOutput(0),getInput(2),getInput(3),getInput(4));
        ands[29]= new And("D29",not0.getOutput(0),not1.getOutput(0),getInput(2),getInput(3),getInput(4));
        ands[30]= new And("D30",not0.getOutput(0),not1.getOutput(0),getInput(2),getInput(3),getInput(4));
        ands[31]= new And("D31",not0.getOutput(0),not1.getOutput(0),getInput(2),getInput(3),getInput(4));
        for (int i=0; i<32;++i) {
        	addOutput(ands[i].getOutput(0));
        }

	}

}
