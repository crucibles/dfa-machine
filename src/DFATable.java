import java.util.Collections;
import java.util.Vector;

public class DFATable {
    private Vector<DFAState> dfaTable;

    /**
     * Constructor
     */
    public DFATable() {
        dfaTable = new Vector<DFAState>();
    }

    /**
     * Adds a new state to the DFA table.
     */
    public void addState(DFAState newState) {
        dfaTable.add(newState);
    }

    /**
     * Returns the DFA Table.
     * @return DFA Table of class 'Vector'
     */
    public Vector<DFAState> getTable() {
        return dfaTable;
    }

    /**
     * Returns the row count of the DFA table.
     * @return number of rows contained in the DFA table.
     */
    public int getNumberOfRows() {
        return dfaTable.size();
    }

    /**
     * Returns the state at the given index.
     * @param index index of the DFA table whose state is to be retrieved
     * 
     * @return DFA state of index 'index'
     */
    public DFAState getStateAt(int index) {
        return dfaTable.get(index);
    }

    /**
     * Determines if a received string is an accepted string based on the DFA table.
     * @param string string to be checked for its validity
     * 
     * @return true if it is a valid string; false if not.
     */
    public boolean isValidString(String string) {
        System.out.println("checking out: " + string);
        DFAState currState = getStartState();
        if(currState == null){
            return false;
        }

        for (int i = 0; i < string.length(); i++) {
            System.out.println(currState.getStateName());
            currState = getNextState(currState, string.charAt(i));
        }
        System.out.println("final: " + currState.getStateCategory());
        System.out.println("final: " + currState.getStateName());
        if(currState.getStateCategory().equals("+")){
            System.out.println("true");
            return true;
        } else {
            System.out.println("false");
            return false;
        }
    }

    /**
     * Returns the start state of the DFA table.
     * @return DFA start state
     */
    public DFAState getStartState() {
        for (int i = 0; i < dfaTable.size(); i++) {
            DFAState currState = dfaTable.get(i);
            if (currState.getStateCategory().equals("-")) {
                return currState;
            }
        }
        return null;
    }

    /**
     * Returns the next state based on the current state and the read input character.
     * @param currState the current state
     * @param input the character to be read
     * 
     * @return the next state; the state to transition to
     */
    private DFAState getNextState(DFAState currState, char input) {
        switch (input) {
        case '0':
            return getStateOfName(currState.getDestination0());
        case '1':
            return getStateOfName(currState.getDestination1());
        default:
            System.out.println("invalid input");
            return null;
        }
    }

    /**
     * Returns the state with the name contained in the parameter 'name'.
     * @param name name of the state that is to be retrieved
     * 
     * @return state with name 'name'
     */
    public DFAState getStateOfName(String name) {
        for (int i = 0; i < dfaTable.size(); i++) {
            DFAState currState = dfaTable.get(i);
            if (currState.getStateName().equals(name)) {
                return currState;
            }
        }
        return null;
    }

    public void clear(){
        dfaTable.clear();
    }

    // public int getStateOccurrence(String name){
    //     return Collections.frequency(dfaTable, name);
    // }
}