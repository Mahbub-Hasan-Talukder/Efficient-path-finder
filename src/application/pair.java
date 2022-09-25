package application;

class pair{
	double cost;
	int node;
	pair(double cost, int node){
		this.cost = cost;
		this.node = node;
	}
	@Override
	public String toString() {
		return String.format("%d %f",node,cost);
	}
}
