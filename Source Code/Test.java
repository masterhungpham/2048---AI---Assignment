import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {
	public static  LinkedBlockingQueue<State> enQueue(State sd,LinkedBlockingQueue<State>fi){
		State up = new State();
		up.copyState(sd, up);
		up.upwipe(up);
		up.setParentState(sd);
		up.setHeight(sd.getHeight()+1);
		up.setPath(sd.getPath()+"->up");
		fi.offer(up);
		State down = new State();
		down.copyState(sd, down);
		down.downwipe(down);
		down.setParentState(sd);
		down.setHeight(sd.getHeight()+1);
		down.setPath(sd.getPath()+"->down");
		fi.offer(down);
		State right = new State();
		right.copyState(sd, right);
		right.rightwipe(right);
		right.setParentState(sd);
		right.setHeight(sd.getHeight()+1);
		right.setPath(sd.getPath()+"->right");
		fi.offer(right);
		State left = new State();
		left.copyState(sd, left);
		left.leftwipe(left);
		left.setParentState(sd);
		left.setHeight(sd.getHeight()+1);
		left.setPath(sd.getPath()+"->left");
		fi.offer(left);
		return fi;
	}
    public static void BFS(LinkedBlockingQueue<State> st,int goal, int height){
    	 State first = new State();
    	    while(st.size()>0){
    	    	first = st.poll();
    	    	System.out.println();
    	    	if(first.checkGoal(goal)==true){
    	    		System.out.println("success");
    	    		System.out.print(first.getPath());
    	    		first.printState();
    	    		while(first.getParentState()!=null){
    	    			first=first.getParentState();
    	    			System.out.print(first.getPath());
    	        		first.printState();
    	    		}
    	    		break;
    	    	}
    	    	else if(first.getHeight()<height)
    	    		if(first.compareWithChildState(first.getParentState())==false){
    	    			first.ramdom();
    	    			enQueue(first,st);
    	    			
    	    		}
    	    	if(st.size()==1) System.out.println("Not Found");
    	    	
    	    }
    }
	public static Stack<State> pushStack(Stack<State> t, State sd){
	State up = new State();
	up.copyState(sd, up);
	up.upwipe(up);
	up.setParentState(sd);
	up.setHeight(sd.getHeight()+1);
	up.setPath(sd.getPath()+"->up");
	t.push(up);
	State down = new State();
	down.copyState(sd, down);
	down.downwipe(down);
	down.setParentState(sd);
	down.setHeight(sd.getHeight()+1);
	down.setPath(sd.getPath()+"->down");
	t.push(down);
	State right = new State();
	right.copyState(sd, right);
	right.rightwipe(right);
	right.setParentState(sd);
	right.setHeight(sd.getHeight()+1);
	right.setPath(sd.getPath()+"->right");
	t.push(right);
	State left = new State();
	left.copyState(sd, left);
	left.leftwipe(left);
	left.setParentState(sd);
	left.setHeight(sd.getHeight()+1);
	left.setPath(sd.getPath()+"->left");
	t.push(left);
	return t;
}

public static void DFS(Stack<State>st, int goal,int height){
    State first = new State();
    while(st.size()>0){
    	first = st.pop();
    	System.out.println();
    	if(first.checkGoal(goal)==true){
    		System.out.println("success");
    		System.out.print(first.getPath());
    		first.printState();
    		while(first.getParentState()!=null){
    			first=first.getParentState();
    			System.out.print(first.getPath());
        		first.printState();
    		}
    		break;
    	}
    	else if(first.getHeight()<height)
    		if(first.compareWithChildState(first.getParentState())==false){
    			first.ramdom();
    			pushStack(st,first);
    			
    		}
    	if(st.size()==1) System.out.println("Not Found");
    	
    }
}
	public static void main(String[] args) {
		System.out.println("Program starting...");
		final long startTime = System.nanoTime();
		Stack<State> st = new Stack<State>();
		 LinkedBlockingQueue<State> q = new  LinkedBlockingQueue<State>();
		State p = new State();
		p.ramdom();
		p.ramdom();
		p.printState();
		System.out.println("DFS");
		
		//testcase DFS tuong ung testcase "a"
	    // pushStack(st,p);
		//DFS(st,32,8);
		//end testcase DFS
		System.out.println("BFS");
	    //testcase BFS tuong ung testcas "b"
		enQueue(p,q);
		BFS(q,8,3);
		//end testcase BFS 
		final long duration = System.nanoTime() - startTime;
	       double seconds = (double)duration / 1000000000.0;
	       System.out.println("Running Time :" +seconds+" s");   
	       System.out.print("Memory used :");
	       System.out.printf("%.3fMB", (Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()) / (1024.0 * 1024.0));
		
		

	}

}
