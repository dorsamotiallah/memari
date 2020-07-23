//Dedicated to Goli

package simulator;

import java.util.List;

import simulator.control.Simulator;
import simulator.gates.combinational.And;
import simulator.gates.combinational.Memory;
import simulator.gates.combinational.Not;
import simulator.gates.sequential.Clock;
import simulator.network.Link;
import simulator.wrapper.Multiply4;
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
	static Link Jump;
	
	
    public static Link getJump() {
		return Jump;
	}



	public static void setJump(Link jump) {
		Jump = jump;
	}



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
    	DFlipFlop d1 = new DFlipFlop("d1","2X2",clk.getOutput(0),Simulator.falseLogic);
    	DFlipFlop d2 = new DFlipFlop("d2","2X2",clk.getOutput(0),d1.getOutput(0));
    	Not n1 = new Not("NOT1",d2.getOutput(0));
    	Link controler = n1.getOutput(0);

    
    	Register pc = new Register("PC","33X32",clk.getOutput(0));//starts with zero

    	
    	//adding 32 to pc 
    	Adder pcadder = new Adder("adder","64X32");
    	for(int i=0;i<32;i++)
    		pcadder.addInput(pc.getOutput(i));
    	Link[] thirtytwo = new Link[32];
    	for(int i=0;i<32;i++)
    		thirtytwo[i]=Simulator.falseLogic;
    	thirtytwo[26]=Simulator.trueLogic;
    	pcadder.addInput(thirtytwo); 
    	
    	//initalizing instruction memory 
    	Boolean[] initinstruction = new Boolean[65536];
    	Boolean[] instructions= {true,false,false,false,true,true,false,true,false,false,true,false,true,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,false,false,false,true,true,false,true,false,true,false,false,true,false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,false,false,false,false,false,false,false,true,false,false,true,false,true,false,true,false,false,true,false,false,false,false,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,true,false,false,true,false,true,false,true,false,false,true,false,false,false,false,false,false,false,false,true,false,false,true,false,false,false,false,false,true,false,false,false,true,false,false,false,false,true,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,false,false,false,false,false,false,false,true,false,false,true,false,true,false,true,false,false,true,false,false,false,false,false,false,false,false,true,false,false,true,false,true,false,false,false,false,false,false,false,true,false,false,true,false,true,false,true,false,false,true,false,false,false,false,false,false,false,false,true,false,true,false,true,false,false,false,false,false,false,false,false,true,false,false,true,false,true,false,true,false,false,true,false,false,false,false,false,false,false,false,true,false,false,false,true,false,false,false,false,false,false,false,false,true,false,false,true,false,true,false,true,false,false,true,false,false,false,false,false,false,false,false,true,false,false,false,true,false};
    	for(int i=0;i<288;i++) {
    		initinstruction[i]=instructions[i];
    	}
    	for(int i=288 ;i<65536;i++) {
    		initinstruction[i]=false;
    	}
    	
    	//giving the address to instruction memory for reading from it   	
    	Memory InstructionMem = new Memory("InstructionMem");
    	InstructionMem.setMemory(initinstruction);//seting the instructions
    	InstructionMem.addInput(Simulator.falseLogic);//we only read from instruction memory
    	
    	Link [] instructionMemoryaddress = new Link[16]; 
    	for(int i=16,j=0 ;i<32 && j<16; i++) 
    		instructionMemoryaddress[j++] = pc.getOutput(i);
    	InstructionMem.addInput(instructionMemoryaddress);
    	
  
    	//sepration of bits of instruction
    	Link[] opcode = new Link[6];
    	Link[] rs = new Link[5];
    	Link[] rt = new Link[5];
    	Link[] rd = new Link[5];
    	Link[] ofssetImmediate = new Link[16];
    	Link[] funct = new Link[6];
    	Link[] shamt = new Link[5];
    	Link[] jumpaddress = new Link[26];
    	
    	for(int i =0 ;i<6 ;i++) {
    		opcode[i]=InstructionMem.getOutput(i);
    	}
    	for(int i=0 ;i<26;i++)
    		jumpaddress[i]=InstructionMem.getOutput(i+6);
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
    		ofssetImmediate[i]=InstructionMem.getOutput(i+16);
    	}
    	for(int i =0; i<5; i++) {
    		shamt[i]=InstructionMem.getOutput(i+21);
    	}
    	for(int i =0 ; i<6 ; i++) {
    		funct[5-i]=InstructionMem.getOutput(i+26);
    	}
    	
    	//Jump
    	Link[] nextpcJ = new Link[32];
    	Shift shiftleft2 = new Shift("shl2","26X28",jumpaddress);
    	
    	for(int i=0;i<4;i++)
    		nextpcJ[i]=pc.getOutput(i);
    	for(int i=0;i<28;i++)
    		nextpcJ[i+4]=shiftleft2.getOutput(i);

    	//main control unit 
    	MainControl cu = new MainControl("cu","6X10");
    	cu.addInput(opcode);

    	
		setRegDst(cu.getOutput(0));
		setMemToReg(cu.getOutput(1));
		setMemRead(cu.getOutput(2));
		setMemWrite(cu.getOutput(3));
		setBranch(cu.getOutput(4));
		setALUop1(cu.getOutput(5));
		setALUop0(cu.getOutput(6));
		setALUSrc(cu.getOutput(7));
		setRegWrite(cu.getOutput(8));
		setJump(cu.getOutput(9));

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
    	
    	

    	
    	//EX 
    	
    	AluControl alucontrol = new AluControl("alucontrol","6X4",ALUop0,ALUop1,funct[0],funct[1],funct[2],funct[3]);
    	
    	SignExtend16To32 signEx = new SignExtend16To32("extend","16X32");
    	signEx.addInput(ofssetImmediate);
    	
      	Wide32Mux2x1 EXmux = new Wide32Mux2x1("m","65X32",ALUSrc);
       	for(int i=0;i<32;i++)
       		EXmux.addInput(MUX2.getOutput(i));  	
       	for(int i=0;i<32;i++)
       		EXmux.addInput(signEx.getOutput(i));
    	

    	ALU alu = new ALU("alu","68X33");
    	for(int i=0;i<4;i++)
    		alu.addInput(alucontrol.getOutput(i));
    	for(int i=0;i<32;i++)
    		alu.addInput(MUX1.getOutput(i));
    	for(int i=0;i<32;i++)
    		alu.addInput(EXmux.getOutput(i));
    	
    	//address of branch target
    	Link[] addressofsset = new Link[32];
    	for(int i=0;i<32;i++)
    		addressofsset[i]=signEx.getOutput(i);
    	Shiftleft5 shiftleft2_2 = new Shiftleft5("multiply4","32X32",addressofsset);
    	Adder branchadder = new Adder("adder","64X32");
    	for(int i=0;i<32;i++)
    		branchadder.addInput(pcadder.getOutput(i));
    	for(int i=0;i<32;i++)
    		branchadder.addInput(shiftleft2_2.getOutput(i));
    	And branchand = new And("and",alu.getOutput(32),Branch);
    	Wide32Mux2x1 branchmux = new Wide32Mux2x1("mux","65X32",branchand.getOutput(0));
    	for(int i=0 ;i<32;i++)
    		branchmux.addInput(pcadder.getOutput(i));
    	for(int i=0;i<32;i++)
    		branchmux.addInput(branchadder.getOutput(i));
    	
    	//jump mux
    	Wide32Mux2x1 jumpmux = new Wide32Mux2x1("jumpmux","65X32",Jump);
    	for(int i=0;i<32;i++)
    		jumpmux.addInput(branchmux.getOutput(i));
    	for(int i=0;i<32;i++)
    		jumpmux.addInput(nextpcJ[i]);
    	
    	//initalizing datad memory 
    	Boolean[] initdata = new Boolean[65536];
    	Boolean[] tempdata = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true};
    	for(int i=0;i<64 ;i++)
    		initdata[i]=tempdata[i];
    	for(int i=64;i<65536 ;i++)
    		initdata[i]=false;
    	
    	
    	//access data in memory
    	Memory datamemory = new Memory("DataMem");
    	datamemory.setMemory(initdata);
    	datamemory.addInput(MemWrite);//cause in Memory class we just get one control signal and if it is true we write otherwise we read so i just put MemWrite to it
    	for(int i=0;i<16;i++)
    		datamemory.addInput(alu.getOutput(i+16));
    	for(int i=0;i<32;i++)
    		datamemory.addInput(MUX2.getOutput(i));
    	
    	
    	//WB
       	Wide32Mux2x1 WBmux = new Wide32Mux2x1("m","65X32",MemToReg);
       	for(int i=0;i<32;i++)
       		WBmux.addInput(alu.getOutput(i));  	
       	for(int i=0;i<32;i++)
       		WBmux.addInput(Simulator.falseLogic);
       	
       	for(int i=0 ;i<32;i++)
       		WriteData[i]=WBmux.getOutput(i);
       	
       	//puting next pc in pc 
       	for(int i=0;i<32;i++)
       		pc.addInput(jumpmux.getOutput(i));
       	
       	
    	
    	

        Simulator.debugger.addTrackItem(clk,pc,InstructionMem);
        Simulator.debugger.setDelay(500);
        Simulator.circuit.startCircuit();

    }



}