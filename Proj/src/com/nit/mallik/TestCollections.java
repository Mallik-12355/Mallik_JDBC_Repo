package com.nit.mallik;

public class TestCollections {

	public static void main(String[] args) {
		MallikCustomCollections col=
				new MallikCustomCollections();
		System.out.println("Capacity :" +col.capacity());
		System.out.println("Size  	 :" +col.size());
		System.out.println(col);
		System.out.println();
		
		col.add("a");
		col.add("b");
		col.add(1);
		col.add(null);
		col.add(3);
		col.add(3);
		col.add(6.5);
		col.add("a");
		col.add(6);
		col.add(5);
		col.add(5);
		
		System.out.println("Capacity :" +col.capacity());
		System.out.println("Size  	 :" +col.size());
		System.out.println("ele: "+col);
		System.out.println();
	}
}
