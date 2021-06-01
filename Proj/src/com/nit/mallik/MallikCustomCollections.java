package com.nit.mallik;

import java.util.Arrays;

public class MallikCustomCollections {
	private Object[] elementData;
	private int size;
	public MallikCustomCollections() {
		elementData=new Object[10];
		size=0;
	}
	
	public void add(Object obj) {
		if(size()==capacity())
			grow();
		elementData[size++]=obj;
	}
	
	public int capacity() {
		return elementData.length;
	}
	
	public int size() {
		return size;
	}
	
	public void grow() {
		Object[] nextArray=new Object[capacity()*2];
		for(int i=0;i<elementData.length;i++) {
			nextArray[i]=elementData[i];
		}
		elementData=nextArray;
	}
	
	private boolean isEmpty() {		
		return size==0;
	}
	
	public String toString() {
		if(isEmpty())
			return "[]";
		String str=new String();
		str=str.concat("[");
		for(int i=0;i<size;i++) {
			Object obj=elementData[i];
			str=str.concat(obj==null?"null":obj.toString());
			str=str.concat(", ");
		}
		str=str.substring(0,str.length()-2);
		str=str.concat("]");
		return str;
	}
}
