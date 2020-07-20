//Dedicated to Goli

package simulator;

import java.util.List;

import simulator.control.Simulator;
import simulator.gates.combinational.And;
import simulator.gates.combinational.Memory;
import simulator.gates.combinational.Not;
import simulator.gates.sequential.Clock;
import simulator.gates.sequential.flipflops.DFlipFlop;
import simulator.network.Link;
import simulator.wrapper.wrappers.*;

public class Sample {
	//control Signals 
    static Link RegDst;
	static Link ALUSrc;
	static Link MemToReg;
	static Link RegWrite;
	static Link MemRead;
	static Link MemWrite;
	static Link Branch;
	static Link ALUop1;
	static Link ALUop0;
	
	
	
    public static Link getRegDst() {
		return RegDst;
	}



	public static void setRegDst(Link regDst) {
		RegDst = regDst;
	}



	public static Link getALUSrc() {
		return ALUSrc;
	}



	public static void setALUSrc(Link aLUSrc) {
		ALUSrc = aLUSrc;
	}



	public static Link getMemToReg() {
		return MemToReg;
	}



	public static void setMemToReg(Link memToReg) {
		MemToReg = memToReg;
	}



	public static Link getRegWrite() {
		return RegWrite;
	}



	public static void setRegWrite(Link regWrite) {
		RegWrite = regWrite;
	}



	public static Link getMemRead() {
		return MemRead;
	}



	public static void setMemRead(Link memRead) {
		MemRead = memRead;
	}



	public static Link getMemWrite() {
		return MemWrite;
	}



	public static void setMemWrite(Link memWrite) {
		MemWrite = memWrite;
	}



	public static Link getBranch() {
		return Branch;
	}



	public static void setBranch(Link branch) {
		Branch = branch;
	}



	public static Link getALUop1() {
		return ALUop1;
	}



	public static void setALUop1(Link aLUop1) {
		ALUop1 = aLUop1;
	}



	public static Link getALUop0() {
		return ALUop0;
	}



	public static void setALUop0(Link aLUop0) {
		ALUop0 = aLUop0;
	}



	public static void main(String[] args) {
		
    	Clock clk = new Clock("CLOCK",1000);
    	
		//Dflipflop for cycle and controler
    	DFlipFlop d1 = new DFlipFlop("d1",clk.getOutput(0),Simulator.trueLogic);
    	Not n1 = new Not("NOT1",d1.getOutput(0));
    	Link controler = n1.getOutput(0);
    	
    	//making pc 
    	Link[] l = new Link[32];
    	for(int i =0; i < 27; i++)
    		l[i] = Simulator.trueLogic;
    	for(int i=27;i<32;i++)
    		l[i] = Simulator.falseLogic;
    	for(int i=0;i<3;i++)
    		l[i]=Simulator.falseLogic;

  
    	Register pc = new Register("PC","33X32",clk.getOutput(0));
    	pc.addInput(l);
    	
    	
    	//giving the address to instruction memory    	
    	Memory InstructionMem = new Memory("InstructionMem");
    	InstructionMem.addInput(controler);
    	
    	Link [] instructionMemoryInput = new Link[16]; 
    	for(int i=16,j=0 ;i<32 && j<16; i++) 
    		instructionMemoryInput[j++] = pc.getOutput(i);
    	InstructionMem.addInput(instructionMemoryInput);
    	
    	Link [] data = new Link[32];
    	for(int i =0; i < 32 ; i++)
    		data[i] = Simulator.trueLogic;
    	InstructionMem.addInput(data);
    	
    	
    	//sepration of bits of instruction
    	Link[] opcode = new Link[6];
    	Link[] rs = new Link[5];
    	Link[] rt = new Link[5];
    	Link[] rd = new Link[5];
    	Link[] addressOfsset = new Link[16];
    	Link[] funct = new Link[6];
    	Link[] shamt = new Link[5];
    	
    	for(int i =0 ;i<6 ;i++) {
    		opcode[i]=InstructionMem.getOutput(i);
    	}
    	for(int i =4 ; i>=0; i--) {
    		rs[4-i]=InstructionMem.getOutput(i+6);
    	}
    	for(int i =4 ; i>=0; i--) {
    		rt[4-i]=InstructionMem.getOutput(i+11);
    	}
    	for(int i =4 ; i>=0; i--) {
    		rd[4-i]=InstructionMem.getOutput(i+16);
    	}
    	for(int i =0 ; i<16;i++) {
    		addressOfsset[i]=InstructionMem.getOutput(i+16);
    	}
    	for(int i =0; i<5; i++) {
    		shamt[i]=InstructionMem.getOutput(i+21);
    	}
    	for(int i =0 ; i<6 ; i++) {
    		funct[i]=InstructionMem.getOutput(i+26);
    	}
    	
        //data of 32 register    

//    	Link [] links= new Link[1024];
//    	for(int i=0; i<32;++i) {
//    		links[i]=Simulator.trueLogic;
//    	}
//    	links[0]=Simulator.falseLogic;
//    	for(int i=32;i<1024;++i) {
//    		links[i]=Simulator.falseLogic;
//    	}
//
//    	
//    	Wide32Mux32x1 mp= new Wide32Mux32x1("hello","1029X32",Simulator.trueLogic,Simulator.falseLogic,Simulator.falseLogic,Simulator.falseLogic,Simulator.falseLogic);
//    	for(int i=0;i<1024;++i) {
//    		mp.addInput(links[i]);
//    	}
//    	
//    	Link[] regs = new Link[64];
//    	for(int i=0;i<64;i++) {
//    		regs[i]=Simulator.trueLogic;
//    	}
//    	regs[1]=Simulator.falseLogic;
//    	regs[32]=Simulator.falseLogic;
    	
    	

    	//control unit 
    	MainControl cu = new MainControl("cu","6X9");
    	cu.addInput(opcode);
    	
		setRegDst(cu.getOutput(0));
		setMemToReg(cu.getOutput(1));
		setMemRead(cu.getOutput(2));
		setMemWrite(cu.getOutput(3));
		setBranch(cu.getOutput(4));
		setALUop1(cu.getOutput(5));
		setALUop0(cu.getOutput(6));
		setALUSrc(cu.getOutput(7));
		Not notregwrite = new Not("not",cu.getOutput(8));
		setRegWrite(notregwrite.getOutput(0));
		

    	//register file     	
    	Register [] Reg= new Register[32];
    	Link[] WriteData= new Link[32];
    	for(int i=0;i<32;i++) {
    		WriteData[i]=Simulator.falseLogic;
    	}
    	Wide5Mux2x1 secondregmux = new Wide5Mux2x1("mux5","11X5",RegDst);
    	secondregmux.addInput(rt);
    	secondregmux.addInput(rd);
    	And[] ands= new And[32];
    	Decoder decoder= new Decoder("DEC","5X32",rd[0],rd[1],rd[2],rd[3],rd[4]);
    	for(int i=0;i<32;++i) {
    		ands[i]= new And("a"+i,decoder.getOutput(i),RegWrite);
    	}
    	for(int i=0; i<32;++i) {
    		Reg[i]= new Register("h"+i, "33X32",ands[i].getOutput(0));
    		for(int j=0; j<32;++j) {
    			Reg[i].addInput(WriteData[j]);
    		}
    	}
    	Wide32Mux32x1 MUX1 = new Wide32Mux32x1("MUX1","1029X32",rs[0],rs[1],rs[2],rs[3],rs[4]);
    	
    	Wide32Mux32x1 MUX2 = new Wide32Mux32x1("MUX2","1029X32",secondregmux.getOutput(0),secondregmux.getOutput(1),secondregmux.getOutput(2),secondregmux.getOutput(3),secondregmux.getOutput(4));
    	for(int i=0; i<32;++i) {
    		for(int j=0; j<32;++j) {
    			MUX1.addInput(Reg[i].getOutput(j));
    		}
    	}
    	for(int i=0; i<32; ++i) {
    		for(int j=0; j<32; ++j) {
    			MUX2.addInput(Reg[i].getOutput(j));
    		}
    	}
    	
    	
    	


        Simulator.debugger.addTrackItem(clk,InstructionMem);
        System.out.println();
        Simulator.debugger.setDelay(500);
        Simulator.circuit.startCircuit();

    }



}