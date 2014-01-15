/**
 * Esta classe define cada AFD.
 * Atributos:
 *   start: estado inicial
 *   trans: lista de transições
 *   end  : lista de estados finais
 */
import java.util.Vector;
public class Afd {
	private int start;
	private Vector<Transition> trans = new Vector<Transition>();
	private Vector<Integer> end = new Vector<Integer>();
	public Afd(int start, Vector<Transition> trans, Vector<Integer> end) {
		this.start = start;
		this.trans = trans;
		this.end = end;
	}
	/**
	 * Adiciona uma nova transição ao AFD
	 * @param stateA estado partida
	 * @param stateB estado chegada
	 * @param label símbolo de transição
	 */
	public void addTransition(int stateA, int stateB, char label){
		trans.add(new Transition(stateA, stateB, label));
	}
	/**
	 * Adiciona um novo estado final ao AFD
	 * @param end estado final
	 */
	public void addEnd(int end){
		this.end.add(end);
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public Vector<Transition> getTrans() {
		return trans;
	}
	public void setTrans(Vector<Transition> trans) {
		this.trans = trans;
	}
	public Vector<Integer> getEnd() {
		return end;
	}
	public void setEnd(Vector<Integer> end) {
		this.end = end;
	}
	public String toString(){
		String res = "Inicial: " + start;
		for(Transition objTrans : trans)
			res += "\n" + objTrans.toString();
		res += "\nFinal: ";
		for(int objEnd : end)
			res += objEnd + " ";
		return res;
	}
	public String verifyWordToString(String word){
		if(verifyWord(word))
			return "aceita";
		else
			return "rejeita";
	}
	public boolean verifyWord(String word){
		int e = start;
		char s;
		if(word.length()>0){
			for(int i = 0; i < word.length(); i ++){
				s = word.charAt(i);
				e = processTransition(e, s);
			}
		}
		if(end.contains(e))
			return true;
		else
			return false;
	}
	private int processTransition(int e, char s){
		int res = -1;
		for(Transition objTrans : trans)
			if(objTrans.getStateA() == e && objTrans.getLabel() == s)
				return objTrans.getStateB();
		return res;
	}
}
