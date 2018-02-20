public class DFAState {
	private String stateCategory;
	private String stateName;
    private String destination0;
    private String destination1;

	public DFAState() {

	}

	public DFAState(String stateCategory, String stateName, String destination0, String destination1) {
        this.stateCategory = stateCategory;
		this.stateName = stateName;
		this.destination0 = destination0;
		this.destination1 = destination1;
	}

	public String getStateCategory() {
		return this.stateCategory;
	}

	public String getStateName() {
		return this.stateName;
	}

	public String getDestination0() {
		return this.destination0;
    }
    
    public String getDestination1() {
		return this.destination1;
	}
}