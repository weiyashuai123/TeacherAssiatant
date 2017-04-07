package com.teacherassistant.util;

public class GetDistance {
	public static String getdistance(
			double latitude1, double longitude1,
			double latitude2, double longitude2) {
		// TODO Auto-generated method stub
		double la1 = latitude1 * Math.PI/180.0;
		double la2 = latitude2 * Math.PI/180.0;
		double ln1 = longitude1 * Math.PI/180.0;
		double ln2 = longitude2 * Math.PI/180.0;
		
		double a = la1-la2;
		double b = ln1-ln2;
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
				    Math.cos(la1)*Math.cos(la2)*Math.pow(Math.sin(b/2),2)));

		s = s * 6378.137;//乘以地球半径得到距离
		
//		s = Math.round( s * 10000)/10000;
		int res = (int) Math.round(s * 1000)+1;
		String unit = " m";
		
		if(res>1000){
			res=res/1000;
			unit=" km";
		}
		
		String value = res+unit;
		System.out.println(value);
		return value;
	}

}
