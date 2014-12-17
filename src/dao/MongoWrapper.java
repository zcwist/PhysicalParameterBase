package dao;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;


public class MongoWrapper {
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
}