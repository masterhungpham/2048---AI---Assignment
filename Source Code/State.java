

import java.util.*;

public class State {
  int[][]index;
  String path;
  public String getPath() {
	return path;
}
public void setPath(String path) {
	this.path = path;
}

  private String howithP;
  private int height;
  
  public int[][] getIndex() {
	return index;
}
public void setIndex(int[][] index) {
	this.index = index;
}
public List<State> gettotien() {
	return totien;
}
public void settotien(List<State> totien) {
	this.totien = totien;
}

public List<State> totien;   // danh sach to tien 
private State parentState;
  public void coppyAscentor(State child)  {
	  int i=0;
	  while(i<this.totien.size()){
		  child.totien.add(this.totien.get(i));
		  i++;
	  }
	  
	  
  }     // kiem tra random

  // so sanh voi trang thai con
  public boolean compareWithChildState(State parent){
	  boolean result =true;
	  for(int i=0;i<4;i++)
		  for(int j=0;j<4;j++)
			  if(this.index[i][j]!=parent.index[i][j])
				  result=false;
	  return result;
  }
  

  
  public State(){
	  this.path="";
	  this.index= new int[4][4];
	  for(int i=0;i<4;i++){
		  this.index[i] = new int[4];  //tao ra mot mang ngang 4 phan tu 
		  for(int j=0;j<4;j++) this.index[i][j]=0;
	  }
	  this.sethowithP("");
	  this.setHeight(0);
	  this.totien=new ArrayList<State>();   // khoi tao mang 4 phan tu 
	  
  }
  public void writePath(){
	  int i=0;
	  while(i<this.totien.size()){
		  this.totien.get(i).printState();
		  System.out.println("=====");
		  i++;
	  }
  }
  
  public boolean checkGoal(int goal){  //kiem tra index co trang thai
	  boolean result =false;
	  for(int i=0;i<4;i++)
		  for(int j=0;j<4;j++) 
			  if(index[i][j]==goal) result=true;
	  
	  return result;
  }
  public void ramdom(){
	
	  boolean exit=true;
	  while(exit){
		   Random rand = new Random(); 
	       int row = rand.nextInt(4); 
	       int colum=rand.nextInt(4);
	       int value =rand.nextInt(3);
	       if(value==0) value=1;
	       value*=2;
	       if(index[row][colum]==0){
	    	   index[row][colum]=value;
	    	   exit=false;
	       }
	  }
	 
	  
  }
  
  public boolean checkUpwipeable(){
	  boolean result=false;
	  for(int i=3;i>0;i--)
		  for(int j=0;j<4;j++){
			  if(this.index[i][j]!=0 && this.index[i-1][j]==0 ) 
				 result=true;
		      if(this.index[i][j]>0 && this.index[i-1][j]>0 && this.index[i][j]==this.index[i-1][j])
		    	  result=true;
	  }
	  return result;
  }
  

  
  public static State copyState(State oldState, State newState){
	  for(int i =0;i <4;i++)
		  for(int j=0;j<4;j++)
			  newState.index[i][j]=oldState.index[i][j];
	  
	  return newState;
  }
  public State upwipe(State newstate){
	int k;
	int j=0;
	while(j<4){
		int i=0;
		while(i<3){
			if(this.index[i][j]==0){
				k =i+1;
				while(k<=3){
					if(index[k][j]!=0){
  						this.index[i][j]=index[k][j];
  						index[k][j]=0;
  						break;
  					}
					k++;
				}
			}
			i++;
		}
		j++;
	}
	j=0;
	while(j<4){
		int i=0;
		while(i<3){
			int l;
			if(this.index[i][j]==this.index[i+1][j]){
  				this.index[i][j]+=this.index[i+1][j];
  				this.index[i+1][j]=0;
			    l =i+1;
			    while(l<3){
			    	index[l][j]=index[l+1][j];
	  				if(l<=3) index[l][j]=0;
			    	l++;
			    }
			}
			i++;
		}
		j++;
	}
	
	  	
	  	newstate.index=this.index;
	  	return newstate;
  }
  public State downwipe(State newstate){
	
	 int j = 0;
	  while(j<4){
		  int i =3;
		  while(i>=0){
			  if(this.index[i][j]==0){
				  int k=i-1;
				  while(k>=0){
					  if(index[k][j]!=0){
	  						this.index[i][j]=index[k][j];
	  						index[k][j]=0;
	  						break;
	  					} 
					  k--;
				  }
			  }
			  i--;
		  }
	   i =3;
	  while (i>0){    
		  if(this.index[i][j]==this.index[i-1][j]){
				this.index[i][j]+=this.index[i-1][j];
				this.index[i-1][j]=0;
				int l=i-1;
				while(l>0){
					index[l][j]=index[l-1][j];
	  				if(l>=0)this.index[l][j]=0;
					l--;
				}
		  }
		  i--;
	  } 
	  j++;
	  }
	  
	  
		newstate.index=this.index;
	  	return newstate;
  }  
  public State leftwipe(State newstate){
	  boolean tst;
	  int j;
	  int i =0;
	 
	  while (i<=3)
	  {
		  j=0;
		  while (j<=3){
			  if(this.index[i][j]==0){
				  int k=j;
				  tst=false;
				  while(k<=3 && !tst){
					  if(index[i][k]!=0){
						  index[i][j]=index[i][k];
						  index[i][k]=0;tst=true;
						  break;
					  }
					  k++;
				  }
			  }
			  j++;
			  }
		  i++;
	}
	  i=0;
	  while (i<=3){
		   j=0;
		  while(j<3){
			  int l;
			  if(this.index[i][j]==this.index[i][j+1]){
				  index[i][j]+=index[i][j+1];
				   index[i][j+1]=0;
				   l=j+1;
				   while(l<3){
					   index[i][l]=index[i][l+1];
					   if(l<=3) index[i][l]=0;
					   l++;
				   }
			  }
			  j++;
		  }
	  i++;
	  }
	  
	  
	  
	
		newstate.index=this.index;
	  	return newstate;
  }
  public State rightwipe(State newstate){
	
	   boolean tst;
	 int i =3;
	 int j;
	  while(i>=0){
		  j=3;
		 while(j>=0){
			 if(this.index[i][j]==0){
				int k=j;
				tst=false;
				 while(k>=0){
					 if(index[i][k]!=0){
						  index[i][j]=index[i][k];
						  index[i][k]=0;tst=true;
						  break;
						  }
					 k--;
				 }
			 }
			 j--;
		 }
		  i--;
	 }
	  i =3;
	  while(i>=0){
		  j=3;
		  while(j>0){
			  int l;
			  if(this.index[i][j]==this.index[i][j-1]){
				  index[i][j]+=index[i][j-1];
				   index[i][j-1]=0;
				   l = j-1;
				   while(l>0){
					   index[i][l]=index[i][l-1];
					   if(l>=0) index[i][l]=0;
					   l--;
					   }
			  }
			  j--;
		  }
		  i--;
	  } 
	  
		newstate.index=this.index;
	  	return newstate;
  }
   
  
  public void printState(){
	  System.out.println(this.howithP);
	  for(int i =0; i<4 ; i++){
		  for(int j=0; j<4;j++) System.out.print(this.index[i][j]+" ");	
		  System.out.println();
	  }
	  
		  
  }
public State getParentState() {
	return parentState;
}
public void setParentState(State parentState) {
	this.parentState = parentState;
}

public String gethowithP() {
	return howithP;
}

public void sethowithP(String howithP) {
	this.howithP = howithP;
}


public int getHeight() {
	return height;
}

public void setHeight(int height) {
	this.height = height;
}
  
 

  
}
