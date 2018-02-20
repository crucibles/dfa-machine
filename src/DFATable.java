import java.util.Vector;

public class DFATable {
    private Vector<DFAState> dfaTable;

    public DFATable() {
        dfaTable = new Vector<DFAState>();
    }

    public void addState(DFAState newState) {
        dfaTable.add(newState);
    }

    public Vector<DFAState> getTable() {
        return dfaTable;
    }

    public int getNumberOfRows() {
        return dfaTable.size();
    }

    public DFAState getStateAt(int index) {
        return dfaTable.get(index);
    }

    public boolean isValidString(String string) {
        System.out.println("checking out: " + string);
        DFAState currState = getStartState();
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

    public DFAState getStartState() {
        for (int i = 0; i < dfaTable.size(); i++) {
            DFAState currState = dfaTable.get(i);
            if (currState.getStateCategory().equals("-")) {
                return currState;
            }
        }
        return null;
    }

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

    private DFAState getStateOfName(String name) {
        for (int i = 0; i < dfaTable.size(); i++) {
            DFAState currState = dfaTable.get(i);
            if (currState.getStateName().equals(name)) {
                return currState;
            }
        }
        return null;
    }
}