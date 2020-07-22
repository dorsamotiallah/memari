package simulator.wrapper;

import java.util.ArrayList;
//import java.util.List;

import simulator.control.Simulator;
//import simulator.control.Simulator;
//import simulator.gates.combinational.*;
import simulator.network.Link;

public class Slt32 extends Wrapper {

	public Slt32(String label, String stream, Link... links) {
		super(label, stream, links);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		//[63-32] -> operand 2 , [31 - 0] -> operand 1
		//System.out.printf("this is SLT and input size is %d\n" , inputSize);

		
		//::::: Deprecated Solution
//		Or o1 = new Or ("OR1") ;
//		addOutput(Simulator.falseLogic);
//		
//		Link firstBitofResult = Simulator.trueLogic ;
//		
//		for (int i = 0 ; i < (inputSize/2) ; i++) {
//			
//			
//			Not nt1 = new Not("NOT1", getInput(i) );
//			And a1 = new And( "AND1",  getInput(i+(inputSize/2)) , nt1.getOutput(0) );
//			//And a2 = new And ("AND2" , a1.getOutput(0) , Simulator.trueLogic ) ;
//
//			o1.addOutput(a1.getOutput(0));
//			o1.addOutput(getOutput(0));
//			firstBitofResult = o1.getOutput(0) ;
//			setOutput(0, firstBitofResult);
//			
//		}
		
		
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
				
				
		Sub32 sb = new Sub32("SUB", "64X32") ;
		sb.addInput(op1);
		sb.addInput(op2);
		
		ArrayList<Link> res = new ArrayList<Link>();
		
		 for (int i = 0 ; i <  (inputSize/2) ; i++)
			 res.add(Simulator.falseLogic) ;
		 
		 res.set(res.size()-1, sb.getOutput(0) ) ;
		 
		 for (int i = 0 ; i < res.size() ; i++)
			 addOutput(res.get(i));	
	}

}
