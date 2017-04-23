package com.suhaib.db;

import com.mongodb.MongoClient;

public class DataSorce {

	private static MongoClient mongoClient;


	public static void createMongoClient(String host, String port) {
		try {
			mongoClient = new MongoClient(host, Integer.parseInt(port));
		} catch (Exception e) {
			System.out.println("MongoClient create instans error :" + e.getMessage());
		}
	}

	public static MongoClient getMongoClient() {
		return mongoClient;
	}
}
