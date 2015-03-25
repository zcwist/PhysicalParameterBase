package dao;

import interpreter.JSONUtil;

import java.net.UnknownHostException;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;


public class MongoWrapper {
	private static MongoWrapper mongoWrapper;
	private MongoClient mongoClient = null;
	public DB db = null;
	private String ip = "127.0.0.1";
//	private String ip = "166.111.55.99";
	private int port = 27017;
	private String dbname = "PhysicalParameterInATM";
	
	private MongoWrapper() throws UnknownHostException{
		mongoClient = new MongoClient(ip, port);
		db = mongoClient.getDB(dbname);
	}
	
	public static MongoWrapper getInstance(){
		if (mongoWrapper == null){
			try {
				mongoWrapper = new MongoWrapper();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mongoWrapper;
	}
	
	public void destroy(){
		mongoClient.close();
		if (mongoClient != null){
			mongoClient = null;
		}
		if (db != null){
			db = null;
		}
	}
	
	public void insertDatatoCollection(BasicDBObject data, String collName){
		if (!db.collectionExists(collName)){
			db.createCollection(collName, null);
		}
		DBCollection coll = db.getCollection(collName);
		coll.insert(data);
	}
	public void insertDatatoCollection(JSONObject data, String collName){
		BasicDBObject dbObj = (BasicDBObject) JSON.parse(data.toString());
		insertDatatoCollection(dbObj,collName);
	}
	
	public DBCursor getObjectFromColl(BasicDBObject query, String collName){
		DBCollection coll = db.getCollection(collName);
		return coll.find(query);
	}
	
	public BasicDBObject getOneObjectFromColl(BasicDBObject query, String collName){
		DBCollection coll = db.getCollection(collName);
		return (BasicDBObject) coll.findOne(query);
	}
	

	
	public DBCursor getValueFromColl(BasicDBObject ref, BasicDBObject keys, String collName){
		DBCollection coll = db.getCollection(collName);
		return coll.find(ref,keys);
	}
	
	public BasicDBObject getSelectedValueFromColl(BasicDBObject query, BasicDBObject fields, String collName){
		DBCollection coll = db.getCollection(collName);
		return (BasicDBObject) coll.findOne(query,fields);
	}
	
	public void update(JSONObject query, JSONObject value, String collName){
		if (!db.collectionExists(collName)){
			db.createCollection(collName, null);
		}
		DBCollection coll = db.getCollection(collName);

		System.out.println(query);
		System.out.println(value);
		coll.update(JSONUtil.JSONObject2BasicDBObject(query), JSONUtil.JSONObject2BasicDBObject(value), true, false);
	}
	public void update(BasicDBObject query, BasicDBObject value, String collName){
		if (!db.collectionExists(collName)){
			db.createCollection(collName, null);
		}
		DBCollection coll = db.getCollection(collName);

		coll.update(query, value, true, false);
	}
}