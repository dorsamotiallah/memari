package simulator.wrapper.wrappers;

import simulator.network.Link;
import simulator.wrapper.Wrapper;

public class Wide32Mux32x1 extends Wrapper {

	public Wide32Mux32x1(String label, String stream, Link... links) {
		super(label, stream, links);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		int j=5 , b=5 ;
		int k=998;
		Mux32x1[] mx= new Mux32x1[32];
		for (int i=0; i<32;++i) {
			mx[i]= new Mux32x1("h"+i,"37X1",getInput(0),getInput(1),getInput(2),getInput(3),getInput(4));
			while(b<k) {
				mx[i].addInput(getInput(b));
				b+=32;
			}
			j=j+1;
			b=j;
			k=k+1;
			
		}
		for(int i=0;i<32;++i) {
			addOutput(mx[i].getOutput(0));
		}
		
		
	}

}
