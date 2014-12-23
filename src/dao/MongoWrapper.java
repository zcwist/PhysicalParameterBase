package dao;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;


public class MongoWrapper {
	private static MongoWrapper mongoWrapper;
	private MongoClient mongoClient = null;
	public DB db = null;
	private String ip = "127.0.0.1";
//	private String ip = "166.111.55.99";
	private int port = 27017;
	private String dbname = "PhysicalParameterInATM";
	
	public MongoWrapper() throws UnknownHostException{
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
	
	public DBCursor getAObjectFromColl(BasicDBObject query, String collName){
		DBCollection coll = db.getCollection(collName);
		return coll.find(query);
	}
	
	public DBCursor getValueFromColl(BasicDBObject ref, BasicDBObject keys, String collName){
		DBCollection coll = db.getCollection(collName);
		return coll.find(ref,keys);
	}
	public void update(BasicDBObject query, BasicDBObject value, String collName){
		DBCollection coll = db.getCollection(collName);
		coll.update(query, value, true, true);
	}
}