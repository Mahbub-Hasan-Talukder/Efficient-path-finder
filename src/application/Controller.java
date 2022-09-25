package application;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.stage.Stage;


public class Controller{
	private int indicator=0, fn=0,tn=0, chk=0; //fn-fromeNode,tn-toNode
	private String mode="Quick Delivery Mode",sfn="",stn="";
	int lastSource, nodes = 15, inf = 1000005, lastInserted = 0;
	int[] parOf = new int[15];
	int[] notDelivered = new int[100];
	int[] alreadyVisited = new int[100];
	double[] cost = new double[15];
	double[] cost2 = new double[15];
	double secondCost = 0;
	PriorityQueue<pair> pq = new PriorityQueue<>(new Comparator<pair>() {
		@Override
		public int compare(pair o1, pair o2) {
			return Double.compare(o1.cost, o2.cost);
		}
	});
	
	ArrayList <Integer> priority = new ArrayList<>();
	ArrayList <Integer> normal = new ArrayList<>();
	Stack<Integer>sk = new Stack<>();
	Queue<Integer>q = new LinkedList<>();
	@FXML
	Line e12,e19,e23,e34,e35,e48,e37,e56,e67,e78,e89,e810,e711,e612,e914,e1011,e1112,e1113,e1314;
	@FXML
	public Button sd,rs,rtm,des1,dest2,dest3,dest4,dest5,dest6,dest7,dest8,dest9,dest10,dest11,dest12,dest13,dest14;
	@FXML
	private Parent root;
	@FXML
	private Stage stage;
	@FXML
	private Scene scene;
	@FXML
	private Button start;
	@FXML
	private TextField distance = new TextField("Distance");
	@FXML
	private TextField valocity = new TextField("Valocity");
	@FXML
	private TextField priorityDestinations = new TextField("Priority Destinations");
	@FXML
	private TextField normalDestinations = new TextField("Normal Destinations");
	@FXML
	private TextField shortestPath = new TextField("Shortest Paths");
	@FXML
	private Button Replace;
	public LinkedList<pair> list[];
	
	public void sda(ActionEvent e) {
		indicator++;
		indicator%=2;
		if(indicator==0) {
			sd.setText("Quick Delivery Mode");
			mode = "Quick Delivery Mode";
		}
		else if(indicator==1) {
			sd.setText("Normal Delivery Mode");
			mode = "Normal Delivery Mode";
		}
	}
	public void resetColors() {
		if(Matrix.mat[1][2].node<=2)e12.setStyle("-fx-stroke: #eb4634");
		else if(Matrix.mat[1][2].node<5)e12.setStyle("-fx-stroke: #ebba34");
		else e12.setStyle("-fx-stroke: #34baeb");
		if(Matrix.mat[1][9].node<=2)e19.setStyle("-fx-stroke: #eb4634");
		else if(Matrix.mat[1][9].node<5)e19.setStyle("-fx-stroke: #ebba34");
		else e19.setStyle("-fx-stroke: #34baeb");
		if(Matrix.mat[2][3].node<=2)e23.setStyle("-fx-stroke: #eb4634");
		else if(Matrix.mat[2][3].node<5)e23.setStyle("-fx-stroke: #ebba34");
		else e23.setStyle("-fx-stroke: #34baeb");
		if(Matrix.mat[3][4].node<=2)e34.setStyle("-fx-stroke: #eb4634");
		else if(Matrix.mat[3][4].node<5)e34.setStyle("-fx-stroke: #ebba34");
		else e34.setStyle("-fx-stroke: #34baeb");
		if(Matrix.mat[3][5].node<=2)e35.setStyle("-fx-stroke: #eb4634");
		else if(Matrix.mat[3][5].node<5)e35.setStyle("-fx-stroke: #ebba34");
		else e35.setStyle("-fx-stroke: #34baeb");
		if(Matrix.mat[4][8].node<=2)e48.setStyle("-fx-stroke: #eb4634");
		else if(Matrix.mat[4][8].node<5)e48.setStyle("-fx-stroke: #ebba34");
		else e48.setStyle("-fx-stroke: #34baeb");
		if(Matrix.mat[3][7].node<=2)e37.setStyle("-fx-stroke: #eb4634");
		else if(Matrix.mat[3][7].node<5)e37.setStyle("-fx-stroke: #ebba34");
		else e37.setStyle("-fx-stroke: #34baeb");
		if(Matrix.mat[5][6].node<=2)e56.setStyle("-fx-stroke: #eb4634");
		else if(Matrix.mat[5][6].node<5)e56.setStyle("-fx-stroke: #ebba34");
		else e56.setStyle("-fx-stroke: #34baeb");
		if(Matrix.mat[6][7].node<=2)e67.setStyle("-fx-stroke: #eb4634");
		else if(Matrix.mat[6][7].node<5)e67.setStyle("-fx-stroke: #ebba34");
		else e67.setStyle("-fx-stroke: #34baeb");
		if(Matrix.mat[7][8].node<=2)e78.setStyle("-fx-stroke: #eb4634");
		else if(Matrix.mat[7][8].node<5)e78.setStyle("-fx-stroke: #ebba34");
		else e78.setStyle("-fx-stroke: #34baeb");
		if(Matrix.mat[8][9].node<=2)e89.setStyle("-fx-stroke: #eb4634");
		else if(Matrix.mat[8][9].node<5)e89.setStyle("-fx-stroke: #ebba34");
		else e89.setStyle("-fx-stroke: #34baeb");
		if(Matrix.mat[8][10].node<=2)e810.setStyle("-fx-stroke: #eb4634");
		else if(Matrix.mat[8][10].node<5)e810.setStyle("-fx-stroke: #ebba34");
		else e810.setStyle("-fx-stroke: #34baeb");
		if(Matrix.mat[7][11].node<=2)e711.setStyle("-fx-stroke: #eb4634");
		else if(Matrix.mat[7][11].node<5)e711.setStyle("-fx-stroke: #ebba34");
		else e711.setStyle("-fx-stroke: #34baeb");
		if(Matrix.mat[6][12].node<=2)e612.setStyle("-fx-stroke: #eb4634");
		else if(Matrix.mat[6][12].node<5)e612.setStyle("-fx-stroke: #ebba34");
		else e612.setStyle("-fx-stroke: #34baeb");
		if(Matrix.mat[9][14].node<=2)e914.setStyle("-fx-stroke: #eb4634");
		else if(Matrix.mat[9][14].node<5)e914.setStyle("-fx-stroke: #ebba34");
		else e914.setStyle("-fx-stroke: #34baeb");
		if(Matrix.mat[10][11].node<=2)e1011.setStyle("-fx-stroke: #eb4634");
		else if(Matrix.mat[10][11].node<5)e1011.setStyle("-fx-stroke: #ebba34");
		else e1011.setStyle("-fx-stroke: #34baeb");
		if(Matrix.mat[11][12].node<=2)e1112.setStyle("-fx-stroke: #eb4634");
		else if(Matrix.mat[11][12].node<5)e1112.setStyle("-fx-stroke: #ebba34");
		else e1112.setStyle("-fx-stroke: #34baeb");
		if(Matrix.mat[11][13].node<=2)e1113.setStyle("-fx-stroke: #eb4634");
		else if(Matrix.mat[11][13].node<5)e1113.setStyle("-fx-stroke: #ebba34");
		else e1113.setStyle("-fx-stroke: #34baeb");
		if(Matrix.mat[13][14].node<=2)e1314.setStyle("-fx-stroke: #eb4634");
		else if(Matrix.mat[6][12].node<5)e1314.setStyle("-fx-stroke: #ebba34");
		else e1314.setStyle("-fx-stroke: #34baeb");
	}
	public void clearOnAction(ActionEvent e) {
		normal.clear();
		priority.clear();
		priorityDestinations.setText(null);
		normalDestinations.setText(null);
		shortestPath.setText(null);
		for(int i=0;i<100;i++) {
			alreadyVisited[i] = 0;
			notDelivered[i] = 0;
		}
		
		this.resetColors();
		
		dest2.setStyle("-fx-background-radius: 100;");
		dest3.setStyle("-fx-background-radius: 100;");
		dest4.setStyle("-fx-background-radius: 100;");
		dest5.setStyle("-fx-background-radius: 100;");
		dest6.setStyle("-fx-background-radius: 100;");
		dest7.setStyle("-fx-background-radius: 100;");
		dest8.setStyle("-fx-background-radius: 100;");
		dest9.setStyle("-fx-background-radius: 100;");
		dest10.setStyle("-fx-background-radius: 100;");
		dest11.setStyle("-fx-background-radius: 100;");
		dest12.setStyle("-fx-background-radius: 100;");
		dest13.setStyle("-fx-background-radius: 100;");
		dest14.setStyle("-fx-background-radius: 100;");
		
	}
	
	public void pointOnAction(ActionEvent e) {//input
		String digit = ((Button)e.getSource()).getText();
		int n = Integer.parseInt(digit);
		if(mode.charAt(0)=='Q') {
			priorityDestinations.appendText(n+", ");
			priority.add(n);
			if(n==3)dest3.setStyle("-fx-background-color: #fa4857");
			if(n==2)dest2.setStyle("-fx-background-color: #fa4857");
			else if(n==3)dest3.setStyle("-fx-background-color: #fa4857");
			else if(n==4)dest4.setStyle("-fx-background-color: #fa4857");
			else if(n==5)dest5.setStyle("-fx-background-color: #fa4857");
			else if(n==6)dest6.setStyle("-fx-background-color: #fa4857");
			else if(n==7)dest7.setStyle("-fx-background-color: #fa4857");
			else if(n==8)dest8.setStyle("-fx-background-color: #fa4857");
			else if(n==9)dest9.setStyle("-fx-background-color: #fa4857");
			else if(n==10)dest10.setStyle("-fx-background-color: #fa4857");
			else if(n==11)dest11.setStyle("-fx-background-color: #fa4857");
			else if(n==12)dest12.setStyle("-fx-background-color: #fa4857");
			else if(n==13)dest13.setStyle("-fx-background-color: #fa4857");
			else if(n==14)dest14.setStyle("-fx-background-color: #fa4857");
		}
		else {
			normalDestinations.appendText(n+", ");
			normal.add(n);
			Colorize clr = new Colorize();
			if(n==2)dest2.setStyle("-fx-background-color: #45a5ff");
			else if(n==3)dest3.setStyle("-fx-background-color: #45a5ff");
			else if(n==4)dest4.setStyle("-fx-background-color: #45a5ff");
			else if(n==5)dest5.setStyle("-fx-background-color: #45a5ff");
			else if(n==6)dest6.setStyle("-fx-background-color: #45a5ff");
			else if(n==7)dest7.setStyle("-fx-background-color: #45a5ff");
			else if(n==8)dest8.setStyle("-fx-background-color: #45a5ff");
			else if(n==9)dest9.setStyle("-fx-background-color: #45a5ff");
			else if(n==10)dest10.setStyle("-fx-background-color: #45a5ff");
			else if(n==11)dest11.setStyle("-fx-background-color: #45a5ff");
			else if(n==12)dest12.setStyle("-fx-background-color: #45a5ff");
			else if(n==13)dest13.setStyle("-fx-background-color: #45a5ff");
			else if(n==14)dest14.setStyle("-fx-background-color: #45a5ff");
		}
		
	}
	public void edgeInfo(int fn, int tn) {
		sfn=""+Matrix.mat[fn][tn].cost; 
		stn = ""+Matrix.mat[fn][tn].node;
		distance.setText(""+sfn); 
		valocity.setText(""+stn);
	}
	public void detectEdge12() {
		fn = 1; tn = 2;
		sfn=""+Matrix.mat[fn][tn].cost; 
		stn = ""+Matrix.mat[fn][tn].node;
		distance.setText(""+sfn); 
		valocity.setText(""+stn);
	}
	
	public void detectEdge19() {
		fn = 1; tn = 9;
		sfn=""+Matrix.mat[fn][tn].cost; 
		stn = ""+Matrix.mat[fn][tn].node;
		distance.setText(""+sfn); 
		valocity.setText(""+stn);
	}
	
	public void detectEdge23() {
		fn = 2; tn = 3;
		sfn=""+Matrix.mat[fn][tn].cost; 
		stn = ""+Matrix.mat[fn][tn].node;
		distance.setText(""+sfn); 
		valocity.setText(""+stn);
	}
	
	public void detectEdge34() {
		fn = 3; tn = 4;
		sfn=""+Matrix.mat[fn][tn].cost; 
		stn = ""+Matrix.mat[fn][tn].node;
		distance.setText(""+sfn); 
		valocity.setText(""+stn);
	}
	
	public void detectEdge35() {
		fn = 3; tn = 5;
		sfn=""+Matrix.mat[fn][tn].cost; 
		stn = ""+Matrix.mat[fn][tn].node;
		distance.setText(""+sfn); 
		valocity.setText(""+stn);
	}
	
	public void detectEdge48() {
		fn = 4; tn = 8;
		sfn=""+Matrix.mat[fn][tn].cost; 
		stn = ""+Matrix.mat[fn][tn].node;
		distance.setText(""+sfn); 
		valocity.setText(""+stn);
	}
	
	public void detectEdge37() {
		fn = 3; tn = 7;
		sfn=""+Matrix.mat[fn][tn].cost; 
		stn = ""+Matrix.mat[fn][tn].node;
		distance.setText(""+sfn); 
		valocity.setText(""+stn);
	}
	
	public void detectEdge56() {
		fn = 5; tn = 6;
		sfn=""+Matrix.mat[fn][tn].cost; 
		stn = ""+Matrix.mat[fn][tn].node;
		distance.setText(""+sfn); 
		valocity.setText(""+stn);
	}
	
	public void detectEdge67() {
		fn = 6; tn = 7;
		sfn=""+Matrix.mat[fn][tn].cost; 
		stn = ""+Matrix.mat[fn][tn].node;
		distance.setText(""+sfn); 
		valocity.setText(""+stn);
	}
	
	public void detectEdge78() {
		fn = 7; tn = 8;
		sfn=""+Matrix.mat[fn][tn].cost; 
		stn = ""+Matrix.mat[fn][tn].node;
		distance.setText(""+sfn); 
		valocity.setText(""+stn);
	}
	
	public void detectEdge89() {
		fn = 8; tn = 9;
		sfn=""+Matrix.mat[fn][tn].cost; 
		stn = ""+Matrix.mat[fn][tn].node;
		distance.setText(""+sfn); 
		valocity.setText(""+stn);
	}
	
	public void detectEdge810() {
		fn = 8; tn = 10;
		sfn=""+Matrix.mat[fn][tn].cost; 
		stn = ""+Matrix.mat[fn][tn].node;
		distance.setText(""+sfn); 
		valocity.setText(""+stn);
	}
	
	public void detectEdge711() {
		fn = 7; tn = 11;
		sfn=""+Matrix.mat[fn][tn].cost; 
		stn = ""+Matrix.mat[fn][tn].node;
		distance.setText(""+sfn); 
		valocity.setText(""+stn);
	}
	
	public void detectEdge612() {
		fn = 6; tn = 12;
		sfn=""+Matrix.mat[fn][tn].cost; 
		stn = ""+Matrix.mat[fn][tn].node;
		distance.setText(""+sfn); 
		valocity.setText(""+stn);
	}
	
	public void detectEdge914() {
		fn = 9; tn = 14;
		sfn=""+Matrix.mat[fn][tn].cost; 
		stn = ""+Matrix.mat[fn][tn].node;
		distance.setText(""+sfn); 
		valocity.setText(""+stn);
	}
	
	public void detectEdge1011() {
		fn = 10; tn = 11;
		sfn=""+Matrix.mat[fn][tn].cost; 
		stn = ""+Matrix.mat[fn][tn].node;
		distance.setText(""+sfn); 
		valocity.setText(""+stn);
	}
	
	public void detectEdge1112() {
		fn = 11; tn = 12;
		sfn=""+Matrix.mat[fn][tn].cost; 
		stn = ""+Matrix.mat[fn][tn].node;
		distance.setText(""+sfn); 
		valocity.setText(""+stn);
	}
	
	public void detectEdge1113() {
		fn = 11; tn = 13;
		sfn=""+Matrix.mat[fn][tn].cost; 
		stn = ""+Matrix.mat[fn][tn].node;
		distance.setText(""+sfn); 
		valocity.setText(""+stn);
	}
	
	public void detectEdge1314() {
		fn = 13; tn = 14;
		sfn=""+Matrix.mat[fn][tn].cost; 
		stn = ""+Matrix.mat[fn][tn].node;
		distance.setText(""+sfn); 
		valocity.setText(""+stn);
	}
	
	public void changeOnAction(ActionEvent e) {
		for(int i=0;i<=14;i++) {
			notDelivered[i] = 0;
			alreadyVisited[i] = 0;
			parOf[i] = 0;
		}
		
		double d = Double.parseDouble(distance.getText());
		int v = Integer.parseInt(valocity.getText());
		Matrix.mat[tn][fn]=new pair(d,v);
		Matrix.mat[fn][tn] = new pair(d,v);
		this.resetColors();
	}
	
	public void collectPaths(int start){
	  if(start==0){
		  while(!sk.empty()) {
			  if(q.isEmpty()) {
				  q.add(sk.peek());
				  lastInserted = sk.peek();
			  }
			  else if(lastInserted!=sk.peek()) {
				  q.add(sk.peek());
				  lastInserted = sk.peek();
			  }
			  
	    	  sk.pop();
	      }
	    return;
	  }
	  if(sk.empty()||sk.peek()!=start)sk.push(start);
	  alreadyVisited[start] = 1;
	  start = parOf[start];
	  collectPaths(start);
	}
	@SuppressWarnings("unchecked")
	public void calDis(int source) {
		/////
		for(int i=0;i<15;i++) {
			cost[i] = inf;
			cost2[i] = inf;
			parOf[i] = 0;
		}
		cost[source] = 0;
		cost2[source] = 0;
		while(!pq.isEmpty())pq.remove();
		//////
		int vertex = 15;
        list = new LinkedList[vertex+1];
        for (int i = 0; i <vertex ; i++) {
            list[i] = new LinkedList<>();
        }
        int i=1,j=2; list[i].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,j)); list[j].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,i));
        i=1;j=9; list[i].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,j)); list[j].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,i));
        i=2;j=3; list[i].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,j)); list[j].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,i));
        i=3;j=4; list[i].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,j)); list[j].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,i));
        i=3;j=5; list[i].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,j)); list[j].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,i));
        i=3;j=7; list[i].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,j)); list[j].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,i));
        i=4;j=8; list[i].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,j)); list[j].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,i));
        i=5;j=6; list[i].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,j)); list[j].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,i));
        i=6;j=7; list[i].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,j)); list[j].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,i));
        i=7;j=8; list[i].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,j)); list[j].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,i));
        i=8;j=9; list[i].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,j)); list[j].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,i));
        i=8;j=10; list[i].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,j)); list[j].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,i));
        i=7;j=11; list[i].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,j)); list[j].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,i));
        i=9;j=14; list[i].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,j)); list[j].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,i));
        i=6;j=12; list[i].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,j)); list[j].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,i));
        i=10;j=11; list[i].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,j)); list[j].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,i));
        i=11;j=12; list[i].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,j)); list[j].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,i));
        i=11;j=13; list[i].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,j)); list[j].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,i));
        i=13;j=14; list[i].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,j)); list[j].add(new pair(Matrix.mat[i][j].cost/Matrix.mat[i][j].node,i));
        
		
		pq.add(new pair(0.0,source));
		while(!pq.isEmpty()) {
			double Pcost = pq.peek().cost;
			int Pnode = pq.peek().node;
			pq.remove();
			for(i=0;i<list[Pnode].size();i++) {
				int Cnode = list[Pnode].get(i).node;
				Double EC = list[Pnode].get(i).cost;
				if(cost[Pnode]+EC<cost[Cnode]){
					cost2[Cnode] = cost[Cnode];
			        cost[Cnode] = cost[Pnode]+EC;
			        parOf[Cnode] = Pnode;
			        pq.add(new pair(cost[Cnode], Cnode));
			      }
				else if((cost[Pnode]+EC>cost[Cnode])&&(cost[Pnode]+EC<cost2[Cnode])) {
					cost2[Cnode] = cost[Pnode]+EC;
					pq.add(new pair(cost2[Cnode],Cnode));
				}
			}
		}
		
	}
	public void startOnAction(ActionEvent e) {
		
		shortestPath.setText(null);
		System.out.print("Quick Delivery: "); 
		for(int i=0;i<priority.size();i++)System.out.print(priority.get(i)+" ");
		System.out.println();
		System.out.print("Normal Delivery: "); 
		for(int i=0;i<normal.size();i++)System.out.print(normal.get(i)+" ");
		System.out.println();
		
		this.resetColors();
		
		
		calDis(1);
		lastSource = 1;
		
/////////////////// ----> Priority destinations <----- //////////////////////
		  int totalDeliveries = priority.size();
		  for(int i=0;i<totalDeliveries;i++){
		    notDelivered[priority.get(i)] = 1;
		  }
		  
		  for(int deliveries = 0; deliveries<totalDeliveries; deliveries++){
		      int nearestDestination=-1;
		      double nearestDestinationCost  = inf;
		      for(int i=0;i<totalDeliveries;i++){
		    if(notDelivered[priority.get(i)]==1 && nearestDestinationCost>cost[priority.get(i)]){
		      nearestDestinationCost = cost[priority.get(i)];
		      nearestDestination = priority.get(i);
		    }
		  }    
		  

		  secondCost+=cost2[nearestDestination];
		  collectPaths(nearestDestination);
		  lastSource = nearestDestination;
		  calDis(lastSource);
		  notDelivered[nearestDestination] = 0;	  
	      }
	      
/////////////////// ----> Normal destinations <----- //////////////////////
	      totalDeliveries = normal.size();
	      for(int i=0;i<totalDeliveries;i++){
	          if(alreadyVisited[normal.get(i)]==0)notDelivered[normal.get(i)] = 1;
	      }
	      for(int deliveries = 0; deliveries<totalDeliveries; deliveries++){
	          int nearestDestination=-1;
	          double nearestDestinationCost  = inf;
	         
	          for(int i=0;i<totalDeliveries;i++){
	            if(notDelivered[normal.get(i)]==1 && nearestDestinationCost>cost[normal.get(i)]){
	              nearestDestinationCost = cost[normal.get(i)];
	              nearestDestination = normal.get(i);
	            }
	          }
			if(nearestDestination==-1)break;
			secondCost+=cost2[nearestDestination];
            collectPaths(nearestDestination);

            lastSource = nearestDestination;
            calDis(lastSource);
            notDelivered[nearestDestination] = 0;
          }
	      
	      secondCost+=cost2[1];
	      System.out.println("--"+cost2[1]);
	      collectPaths(1);
	      
	      int lastNode = 0, currentNode =0;
		double nodeCost = 0;
	      while(!q.isEmpty()) {
	    	  if(lastNode==0)lastNode = q.peek();
	    	  else {
	    		  currentNode = q.peek();
	    		  
	    		  if(lastNode>currentNode) {
	    			  int tmp = lastNode; lastNode = currentNode; currentNode = tmp;
	    		  }
	    		  if(lastNode==1&&currentNode==2) {
	    			  
	    			  e12.setStyle("-fx-stroke: green;");
	    		  }
	    		  else if(lastNode==1&&currentNode==9) {
	    			  e19.setStyle("-fx-stroke: green;");
	    		  }
	    		  else if(lastNode==2&&currentNode==3) {
	    			  e23.setStyle("-fx-stroke: green;");
	    		  }
	    		  else if(lastNode==3&&currentNode==4) {
	    			  e34.setStyle("-fx-stroke: green;");
	    		  }
	    		  else if(lastNode==3&&currentNode==5) {
	    			  e35.setStyle("-fx-stroke: green;");
	    		  }
	    		  else if(lastNode==3&&currentNode==7) {
	    			  e37.setStyle("-fx-stroke: green;");
	    		  }
	    		  else if(lastNode==4&&currentNode==8) {
	    			  e48.setStyle("-fx-stroke: green;");
	    		  }
	    		  else if(lastNode==5&&currentNode==6) {
	    			  e56.setStyle("-fx-stroke: green;");
	    		  }
	    		  else if(lastNode==6&&currentNode==7) {
	    			  e67.setStyle("-fx-stroke: green;");
	    		  }
	    		  else if(lastNode==7&&currentNode==8) {
	    			  e78.setStyle("-fx-stroke: green;");
	    		  }
	    		  else if(lastNode==8&&currentNode==9) {
	    			  e89.setStyle("-fx-stroke: green;");
	    		  }
	    		  else if(lastNode==8&&currentNode==10) {
	    			  e810.setStyle("-fx-stroke: green;");
	    		  }
	    		  else if(lastNode==7&&currentNode==11) {
	    			  e711.setStyle("-fx-stroke: green;");
	    		  }
	    		  else if(lastNode==6&&currentNode==12) {
	    			  e612.setStyle("-fx-stroke: green;");
	    		  }
	    		  else if(lastNode==9&&currentNode==14) {
	    			  e914.setStyle("-fx-stroke: green;");
	    		  }
	    		  else if(lastNode==10&&currentNode==11) {
	    			  e1011.setStyle("-fx-stroke: green;");
	    		  }
	    		  else if(lastNode==11&&currentNode==12) {
	    			  e1112.setStyle("-fx-stroke: green;");
	    		  }
	    		  else if(lastNode==11&&currentNode==13) {
	    			  e1113.setStyle("-fx-stroke: green;");
	    		  }
	    		  else if(lastNode==13&&currentNode==14) {
	    			  e1314.setStyle("-fx-stroke: green;");
	    		  }
	    		  nodeCost+=Matrix.mat[lastNode][currentNode].cost/Matrix.mat[lastNode][currentNode].node;//node==valocity
	    		  lastNode = q.peek();
	    	  }
	    	  shortestPath.appendText(q.peek()+"("+ String.format("%.2f", nodeCost)+")");
	    	  System.out.print(q.peek()); 
	    	  q.remove();
	    	  if(!q.isEmpty())shortestPath.appendText("->");
	    	  if(!q.isEmpty())System.out.print("->"); 
	    	  else System.out.println();
	      }
	      System.out.println();
	      System.out.println(secondCost);
	}
}