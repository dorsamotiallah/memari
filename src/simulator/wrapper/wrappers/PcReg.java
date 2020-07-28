package simulator.wrapper.wrappers;

import simulator.gates.combinational.Nand;
import simulator.wrapper.Wrapper;
import simulator.network.Link;
import java.util.stream.Stream;

public class PcReg extends Wrapper {
        public PcReg(String label, String stream, Link ... links){
            super(label,stream,links);
        }

    @Override
    public void initialize() {
        Nand n1 = new Nand("NAND1");
        Nand n2 = new Nand("NAND2");
        Nand n3 = new Nand("NAND3");
        Nand n4 = new Nand("NAND4");
        Nand n5 = new Nand("NAND5");
        Nand n6 = new Nand("NAND6");

        n1.setLatch(false);
        n2.setLatch(false);
        n3.setLatch(false);
        n4.setLatch(false);
        n5.setLatch(false);
        n6.setLatch(false);

        n1.addInput(n4.getOutput(0), n2.getOutput(0));
        n2.addInput(getInput(0), n1.getOutput(0));
        n3.addInput(n2.getOutput(0), getInput(0), n4.getOutput(0));
        n4.addInput(n3.getOutput(0), getInput(1));
        n5.addInput(n2.getOutput(0), n6.getOutput(0));
        n6.addInput(n3.getOutput(0), n5.getOutput(0));

        addOutput(n5.getOutput(0), n6.getOutput(0));
    }
}
