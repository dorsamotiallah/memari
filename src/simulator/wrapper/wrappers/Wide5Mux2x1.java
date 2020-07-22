package simulator.wrapper.wrappers;

import simulator.network.Link;
import simulator.wrapper.Wrapper;

public class Wide5Mux2x1 extends Wrapper{

	public Wide5Mux2x1(String label, String stream, Link... links) {
		super(label, stream, links);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		int j=1;
		int k=7;
		int b=1;
		Mux2To1 [] mux = new Mux2To1[5];
		for(int i=0 ;i<5;++i) {
			mux[i]= new Mux2To1("m"+i, "3X1",getInput(0));
			while(b<k) {
				mux[i].addInput(getInput(b));
				b=b+5;
			}
			j=j+1;
			b=j;
			k=k+1;
		}
		for(int i=0;i<5;++i) {
			addOutput(mux[i].getOutput(0));
		}
		
	}

	
}
