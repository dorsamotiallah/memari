//Dedicated to Goli

package simulator;

import java.util.List;

import simulator.control.Simulator;
import simulator.gates.combinational.And;
import simulator.gates.combinational.ByteMemory;
import simulator.gates.combinational.Memory;
import simulator.gates.combinational.Not;
import simulator.gates.combinational.Or;
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
    	
    	
//		//Dflipflop for cycle and controler
//    	DFlipFlop d1 = new DFlipFlop("d1","2X2",clk.getOutput(0),Simulator.falseLogic);
//    	DFlipFlop d2 = new DFlipFlop("d2","2X2",clk.getOutput(0),d1.getOutput(0));
//    	Not n1 = new Not("NOT1",d2.getOutput(0));
//    	Link controler = n1.getOutput(0);

    
    	PcRegister pc = new PcRegister("PC","33X32",clk.getOutput(0));//starts with zero
		And pcAnd= new And("a");
		for(int i=0;i<32;i++){
			pcAnd.addInput(pc.getOutput(0));
		}
    	//adding 32 to pc 
    	Adder pcadder = new Adder("adder","64X32");
    	for(int i=0;i<32;i++)
    		pcadder.addInput(pc.getOutput(i));
    	Link[] four = new Link[32];
    	/*if(pcAnd.getOutput(0).getSignal()){
    		for(int i=0; i<32;i++){
    			thirtytwo[i]=Simulator.falseLogic;
			}
    		thirtytwo[31]=Simulator.trueLogic;
		}
    	else {
			for (int i = 0; i < 32; i++ ){
				thirtytwo[i] = Simulator.falseLogic;
			}
			thirtytwo[26]=Simulator.trueLogic;
		}*/

		for (int i = 0; i < 32; i++ ){
			four[i] = Simulator.falseLogic;
		}
		four[29]=Simulator.trueLogic;

    	pcadder.addInput(four);
    	
    	//initalizing instruction memory 
    	Boolean[][] initinstruction = new Boolean[65536][8];//false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,false,false,false,false,true,false,false,false,true,true,false,true,false,false,true,false,true,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,false,false,false,true,true,false,true,false,true,false,false,true,false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,false,false,false,false,false,false,false,true,false,false,true,false,true,false,true,false,false,true,false,false,false,false,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,true,false,false,true,false,true,false,true,false,false,true,false,false,false,false,false,false,false,false,true,false,false,true,false,false,false,false,false,true,false,false,false,true,false,false,false,false,true,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,false,false,false,false,false,false,false,true,false,false,true,false,true,false,true,false,false,true,false,false,false,false,false,false,false,false,true,false,false,true,false,true,false,false,false,false,false,false,false,true,false,false,true,false,true,false,true,false,false,true,false,false,false,false,false,false,false,false,true,false,true,false,true,false,false,false,false,false,false,false,false,true,false,false,true,false,true,false,true,false,false,true,false,false,false,false,false,false,false,false,true,false,false,false,true,false,false,false,false,false,false,false,false,true,false,false,true,false,true,false,true,false,false,true,false,false,false,false,false,false,false,false,true,false,false,false,true,false
    	//two lw
    	Boolean[][] instructions= {{false,false,false,false,false,false,false,true},{false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false},
    			    /*j 4*/        {false,false,false,false,true,false,false,false},{false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false},{false,false,false,false,false,true,false,false},
    			                   {false,false,false,false,false,false,false,true},{false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false},
    			                   {false,false,false,false,false,false,false,true},{false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false},
    			                   {false,false,false,false,false,false,false,true},{false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false},
          /*beq $t1,$t2,1*/        {false,false,false,true,false,false,false,true},{false,false,true,false,true,false,true,false},{false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,true},
    			                   {false,false,false,false,false,false,false,true},{false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false},
    	 /* lw $t1,0x0($t1)*/	   {true,false,false,false,true,true,false,true},{false,false,false,false,true,false,false,true},{false,false,false,false,true,false,false,true},{false,false,false,false,false,false,false,false},
    	 /*lw $t2,0x1($t2)*/	   {true,false,false,false,true,true,false,true},{false,false,false,false,true,false,true,false},{false,false,false,false,true,false,true,false},{false,false,false,false,false,false,false,true},
    	 /* sw $t1,0x1($t2)*/      {true,false,true,false,true,true,false,true},{false,true,false,false,true,false,false,true},{false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,true}};						
    	
    	
    	for(int i=0;i<40;i++) {
    		for(int j=0;j<8;j++)
    		initinstruction[i][j]=instructions[i][j];
    	}
    	
    	// nop instruction 
    	//false,false,false,false,false,false,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,false,false,false,false,false,

    		
    	//giving the address to instruction memory for reading from it   	
    	ByteMemory InstructionMem = new ByteMemory("InstructionMem");
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
    	Shift shiftleft2 = new Shift("shl2_28bit","26X28",jumpaddress);//put 2 zero bits at the end of 26 bits 
    	
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

    	Wide5Mux2x1 secondregmux = new Wide5Mux2x1("mux5","11X5",RegDst);
    	secondregmux.addInput(rt);
    	secondregmux.addInput(rd);
    	And[] ands= new And[32];
    	Or[] ors= new Or[32];
     	Decoder decoder= new Decoder("DEC","5X32",secondregmux.getOutput(0),secondregmux.getOutput(1),secondregmux.getOutput(2),secondregmux.getOutput(3),secondregmux.getOutput(4));
    	for(int i=0;i<32;++i) {
    		ands[i]= new And("a"+i,decoder.getOutput(i),RegWrite,clk.getOutput(0));
    	}
    	
    	for(int i=0; i<32;++i) {
    		Reg[i]= new Register("h"+i, "33X32",ands[i].getOutput(0));
    		
    	}
  
    	Wide32Mux32x1 MUX1 = new Wide32Mux32x1("MUX1","1029X32",rs[0],rs[1],rs[2],rs[3],rs[4]);
    	
    	Wide32Mux32x1 MUX2 = new Wide32Mux32x1("MUX2","1029X32",rt[0],rt[1],rt[2],rt[3],rt[4]);
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


    		//preparing 32 bit ofsset*4
    	SignExtend16To32 signEx = new SignExtend16To32("extend","16X32");
    	signEx.addInput(ofssetImmediate);
    	Link[] addressofsset = new Link[32];
    	for(int i=0;i<32;i++)
    		addressofsset[i]=signEx.getOutput(i);
    	Multiply4 shiftleft2_2 = new Multiply4("multiply32","32X32",addressofsset);
    	
    	
    	AluControl alucontrol = new AluControl("alucontrol","6X4",ALUop0,ALUop1,funct[0],funct[1],funct[2],funct[3]);
    	
      	Wide32Mux2x1 EXmux = new Wide32Mux2x1("Exmux","65X32",ALUSrc);
       	for(int i=0;i<32;i++)
       		EXmux.addInput(MUX2.getOutput(i));  	
       	for(int i=0;i<32;i++)
       		EXmux.addInput(shiftleft2_2.getOutput(i));
    	

    	ALU alu = new ALU("alu","68X33");
    	for(int i=0;i<4;i++)
    		alu.addInput(alucontrol.getOutput(3-i));
    	for(int i=0;i<32;i++)
    		alu.addInput(MUX1.getOutput(i));
    	for(int i=0;i<32;i++)
    		alu.addInput(EXmux.getOutput(i));
    	
    	//address of branch target
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
    	Boolean[][] initdata = new Boolean[65536][8];
    	Boolean[][] tempdata = {{false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,true},
    			                {false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false}};

    	
    	for(int i=0;i<8;i++) {
    		for(int j=0;j<8;j++)
        		initdata[i][j]=tempdata[i][j];
    	}
    	
    	for(int i=8;i<65536;i++) {
    		for(int j=0;j<8;j++)
        		initdata[i][j]=false;
    	}
    	
    	//access data in memory
    	ByteMemory datamemory = new ByteMemory("DataMem");
    	datamemory.setMemory(initdata);
    	datamemory.addInput(MemWrite);//cause in Memory class we just get one control signal and if it is true we write otherwise we read so i just put MemWrite to it
    	for(int i=16;i<32;i++)
    		datamemory.addInput(alu.getOutput(i));
    	for(int i=0;i<32;i++)
    		datamemory.addInput(MUX2.getOutput(i));
    	
    	
    	//WB       	
       	Wide32Mux2x1 WBmux = new Wide32Mux2x1("WBmux","65X32",MemToReg);
       	for(int i=0;i<32;i++)
       		WBmux.addInput(alu.getOutput(i));  	
       	for(int i=0;i<32;i++)
       		WBmux.addInput(datamemory.getOutput(i));
       	
       	for(int i=0 ;i<32;i++)
       		WriteData[i]=WBmux.getOutput(i);
       	for(int i=0;i<32;++i) {
       	for(int j=0; j<32;++j) {
			Mux2x1 Store= new Mux2x1("s", "3X1",ands[i].getOutput(0),Reg[i].getOutput(j),WriteData[j]);
			Reg[i].addInput(Store.getOutput(0));
		}
       	}
       	
       	//puting next pc in pc 
       	for(int i=0;i<32;i++)

       		pc.addInput(jumpmux.getOutput(i));
       	

    	
    	

        Simulator.debugger.addTrackItem(clk,pc,InstructionMem,Reg[8],Reg[9],Reg[10],datamemory,EXmux,MUX1,alu,WBmux);
        Simulator.debugger.setDelay(500);
        Simulator.circuit.startCircuit();

    }



}
