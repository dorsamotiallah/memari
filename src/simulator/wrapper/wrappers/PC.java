package simulator.wrapper.wrappers;

import simulator.network.Link;
import simulator.wrapper.Wrapper;

public class PC extends Wrapper{

	public PC(String label, String stream, Link... links) {
		super(label, stream, links);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		PcDflipflop [] df= new PcDflipflop[32];
		for(int i=0;i<32;++i) {
			df[i]=new PcDflipflop("hello","2X2",getInput(0),getInput(i+1));
		}
		for(int i=0;i<32;++i) {
			addOutput(df[i].getOutput(0));
		}
	}

}
