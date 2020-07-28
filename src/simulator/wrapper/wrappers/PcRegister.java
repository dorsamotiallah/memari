package simulator.wrapper.wrappers;
import simulator.network.Link;
import simulator.wrapper.Wrapper;
import simulator.gates.sequential.*;
public class PcRegister extends Wrapper {

    public PcRegister(String label, String stream, Link... links) {
        super(label, stream,links);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        PcReg [] df= new PcReg[32];
        for(int i=0;i<32;++i) {
            df[i]=new PcReg("h"+i,"2X2",getInput(0),getInput(i+1));
        }
        for(int i=0;i<32;++i) {
            addOutput(df[i].getOutput(0));
        }

    }

}

