package simulator.wrapper.wrappers;

import simulator.network.Link;
import simulator.wrapper.Wrapper;
import simulator.gates.sequential.*;
import simulator.gates.sequential.flipflops.DFlipFlop;
public class Register extends Wrapper {

	public Register(String label, String stream, Link... links) {
		super(label, stream,links);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		DFlipFlop [] df= new DFlipFlop[32];
		for(int i=0;i<32;++i) {
			df[i]=new DFlipFlop("hello",getInput(0),getInput(i+1));
		}
		for(int i=0;i<32;++i) {
			addOutput(df[i].getOutput(0));
		}
		
	}

}
