package simulator.wrapper.wrappers;


import java.util.*;

import simulator.control.Simulator;
import simulator.gates.combinational.*;
import simulator.network.Link;
import simulator.wrapper.And32;
import simulator.wrapper.Nor32;
import simulator.wrapper.Or32;
import simulator.wrapper.Slt32;
import simulator.wrapper.Sub32;
import simulator.wrapper.Wrapper;

public class ALU extends Wrapper {

	public ALU(String label, String stream, Link... links) {
		super(label, stream, links);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		//[67-36] -> operand 2 , [35 - 4] -> operand 1 , [3 - 0] -> ALU Control input ::So first ALU Control added , Second operand1 , last operand 2
		
		//Separating input bits 

		//put operands in Array list
		ArrayList<Link> op = new ArrayList<Link>() ; 
		for (int i = 0 ; i < inputSize ; i++)
			op.add(i, getInput(i));
		
		//Separating ALU 4 bits Control inputs from op to an array
		
		Link[] ALU4input = new Link[4] ;
		ALU4input =  (Link[]) op.subList(0,4).toArray(ALU4input);
			
		
		//Separating operand 1 from op into an array
		Link [] op1 = new Link[32] ;
		op1 = (Link[]) op.subList(4,36).toArray(op1) ;
		
		//Separating operand 2 from op into an array
		Link [] op2 = new Link[32] ;
		op2 = (Link[]) op.subList(36,68).toArray(op2) ;
		
		//System.out.println(op1.toString());
		//:::: Operations :::: 
		
		//And operation
		And32 AND = new And32("AND", "64X32") ;
		
		AND.addInput(op1);
		AND.addInput(op2);

		//Or operation
		Or32 OR = new Or32("OR", "64X32") ;
		OR.addInput(op1);
		OR.addInput(op2);
		
		//Add operation
		Adder ADD = new Adder("ADD", "64X32") ;
		ADD.addInput(op1);
		ADD.addInput(op2);
		
		//Sub operation
		Sub32 SUB = new Sub32("SUB", "64X32") ;
		SUB.addInput(op1);
		SUB.addInput(op2);
		
		//Slt operation
		Slt32 SLT = new Slt32("SLT", "64X32") ;
		SLT.addInput(op1);
		SLT.addInput(op2);
		
		//Nor operation
		Nor32 NOR = new Nor32("NOR", "64X32") ;
		NOR.addInput(op1);
		NOR.addInput(op2);
		
		//:::: Multiplexers ::::

		Link zero = Simulator.falseLogic ;
		
		for (int i = 0 ; i < 32 ; i++) {
			
			// first multiplexer for (and , or , add )
			Multiplexer mux1 = new Multiplexer("MUX1", "6X1") ;
			
			//selectLines adding
			mux1.addInput(  ALU4input[3] ,ALU4input[2] );
			
			//operation bits adding
			mux1.addInput(AND.getOutput(i) , OR.getOutput(i) ,ADD.getOutput(i) , Simulator.falseLogic);
			
			
			//second multiplexer for (sub , slt , nor )
			Multiplexer  mux2 = new Multiplexer ("MUX2", "6X1") ;
			
			//selectLines adding
			mux2.addInput( ALU4input[3] , ALU4input[0]  );
			
			//operation bits adding
			mux2.addInput(SUB.getOutput(i) , SLT.getOutput(i) , NOR.getOutput(i) , Simulator.falseLogic);
			
			
			//third multiplexer for choosing between upside muxes :)
			Mux2x1 mux3 = new Mux2x1("MUX3", "3X1") ;
			
			//selectLine adding
			mux3.addInput(ALU4input[1]);
			
			//Multiplexers bits adding
			mux3.addInput(mux1.getOutput(0) , mux2.getOutput(0)  );
			
			//Zero detection
			Or or1 = new Or("OR1!1", mux3.getOutput(0) , zero ) ;
			zero = or1.getOutput(0) ;
			
			// :::: It's time for Output bits to take off from ALU Airport :D ::::
			 
			addOutput(mux3.getOutput(0));
			
			//System.out.printf("this is %d and res is %b\n--------------------\n" , i , mux3.getOutput(0).getSignal());
		}
		Not nt1 = new Not("NOT1!1", zero) ; 
		addOutput(nt1.getOutput(0));
		
		
		
	}

}
