package simulator.wrapper;

import java.util.ArrayList;

import simulator.control.Simulator;
import simulator.network.Link;
import simulator.wrapper.wrappers.Adder;

public class Sub32 extends Wrapper {
	static Link [] one = {
			Simulator.falseLogic,Simulator.falseLogic,Simulator.falseLogic,Simulator.falseLogic,Simulator.falseLogic,
			Simulator.falseLogic,Simulator.falseLogic,Simulator.falseLogic,Simulator.falseLogic,Simulator.falseLogic,
			Simulator.falseLogic,Simulator.falseLogic,Simulator.falseLogic,Simulator.falseLogic,Simulator.falseLogic,
			Simulator.falseLogic,Simulator.falseLogic,Simulator.falseLogic,Simulator.falseLogic,Simulator.falseLogic,
			Simulator.falseLogic,Simulator.falseLogic,Simulator.falseLogic,Simulator.falseLogic,Simulator.falseLogic,
			Simulator.falseLogic,Simulator.falseLogic,Simulator.falseLogic,Simulator.falseLogic,Simulator.falseLogic,
			Simulator.falseLogic,Simulator.trueLogic};
	
	
	public Sub32(String label, String stream, Link... links) {
		super(label, stream, links);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize() {
		//System.out.printf("this is Sub , %d\n" , inputSize);
		// TODO Auto-generated method stub
		//[63-32] -> operand 2 , [31 - 0] -> operand 1 ::  sub = operand 1 - operand 2, first operand1 added
		
		//put operands in Array list
		ArrayList<Link> op = new ArrayList<Link>() ;
		for (int i = 0 ; i < inputSize ; i++)
			op.add(i, getInput(i));
		
		//Separating operand 1 from op to an array
		Link [] op1 = new Link[32] ;
		op1 = (Link[]) op.subList(0,inputSize/2 ).toArray(op1) ;
		
		//Separating operand 2 from op into an array
		Link [] op2 = new Link [32] ;
		op2 = (Link[]) op.subList(inputSize/2,inputSize).toArray(op2) ;
		
		
		
		// one's complete of operand 2 
		Not32 nt1 =  new Not32("NOT1", "32X32", op2);
		op2 = (Link []) nt1.getOutputs().toArray(op2) ;
		
		// add 1 to our New operand 2
		Adder ad1 = new Adder("ADD1", "64X32", op2  ) ;
		ad1.addInput(one);
		op2 = (Link []) ad1.getOutputs().toArray(op2) ;

		//Operand 2 converted to (- operand 2 ) , Now we just need to add them :)
		Adder ad2 = new Adder("ADD2", "64X32");
		ad2.addInput(op1);
		ad2.addInput(op2);
		Link[] out = new Link[32] ;
		out = (Link[]) ad2.getOutputs().toArray(out) ; 
		for (int i = 0; i < out.length ; i++)
			addOutput(out[i]);
		
		//Simulator.debugger.addTrackItem(nt1,ad1,ad2);
		
	}

}
