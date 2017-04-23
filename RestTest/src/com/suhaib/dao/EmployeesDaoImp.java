package com.suhaib.dao;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.suhaib.db.DataSorce;
import com.suhaib.util.ConstantClass;

public class EmployeesDaoImp implements EmployeesDao {
	MongoClient mongoClient = null;
	MongoDatabase mongoDatabase = null;
	MongoCollection<Document> mongCollection = null;

	public EmployeesDaoImp() {
		// TODO Auto-generated constructor stub
		mongoClient = DataSorce.getMongoClient();

	}

	@Override
	public String getAllEmployees() {
		mongoDatabase = mongoClient.getDatabase(ConstantClass.DATABASE_NAME);
		mongCollection = mongoDatabase.getCollection(ConstantClass.COLLECTION_NAME);
		JSONArray array = new JSONArray();
		for (Document document : mongCollection.find()) {
			ObjectId id =(ObjectId)document.remove("_id");
			document.append("id", id.toString());
			try {
				array.put(new JSONObject(document.toJson()));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return array.toString();
	}

	@Override
	public String getEmployeeById(String id) {
		mongoDatabase = mongoClient.getDatabase(ConstantClass.DATABASE_NAME);
		mongCollection = mongoDatabase.getCollection(ConstantClass.COLLECTION_NAME);
		Document document = mongCollection.find(new Document("_id", new ObjectId(id))).first();
		ObjectId id1 =(ObjectId)document.remove("_id");
		document.append("id", id1.toString());
		return document.toJson();
	}

	@Override
	public boolean insertEmployees(Document dcoument) {
		mongoDatabase = mongoClient.getDatabase(ConstantClass.DATABASE_NAME);
		mongCollection = mongoDatabase.getCollection(ConstantClass.COLLECTION_NAME);
		boolean returnState = false;
		try {
			mongCollection.insertOne(dcoument);
			returnState = true;

		} catch (Exception e) {
			e.printStackTrace();
			returnState = false;
		}
		return returnState;
	}

	@Override
	public boolean deleteEmployees(String id) {
		mongoDatabase = mongoClient.getDatabase(ConstantClass.DATABASE_NAME);
		mongCollection = mongoDatabase.getCollection(ConstantClass.COLLECTION_NAME);
		DeleteResult state = mongCollection.deleteOne(new Document("_id",new ObjectId(id)));
		return state.getDeletedCount() > 0;
	}

	@Override
	public boolean updateEmployees(Document dcoument) {
		mongoDatabase = mongoClient.getDatabase(ConstantClass.DATABASE_NAME);
		mongCollection = mongoDatabase.getCollection(ConstantClass.COLLECTION_NAME);
		String id =(String)dcoument.remove("id");
		dcoument.put("_id", new ObjectId(id));
		UpdateResult state = mongCollection.updateOne(new Document("_id", new ObjectId(id)),
				new Document("$set", dcoument));
		return state.getModifiedCount() > 0;
	}

}
