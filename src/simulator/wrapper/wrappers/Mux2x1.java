package simulator.wrapper.wrappers;

import simulator.gates.combinational.And;
import simulator.gates.combinational.Not;
import simulator.gates.combinational.Or;
import simulator.network.Link;
import simulator.wrapper.Wrapper;

public class Mux2x1 extends Wrapper {
    public Mux2x1(String label, String stream, Link... links) {
        super(label, stream, links);
    }
    // input Order : (i=0)first = select  , (i=1)second = operand 1 , (i=2)third = operand 2
    @Override
    public void initialize() {
    	//System.out.printf("this is MUx 2 to 1\n");
    	
        Not not0 = new Not("N0", getInput(0));
        
        And and1 = new And("AND1" , getInput(1) , not0.getOutput(0)) ;
        And and2 = new And("AND2" , getInput(2) ,  getInput(0) ) ;

        Or or = new Or("Or1", and1.getOutput(0) , and2.getOutput(0) );

        addOutput(or.getOutput(0));
    }
}
