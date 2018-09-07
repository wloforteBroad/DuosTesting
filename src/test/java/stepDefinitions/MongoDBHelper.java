package stepDefinitions;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;
import org.bson.types.ObjectId;

public class MongoDBHelper {
	private MongoDatabase db = null;

	public MongoDBHelper() {
	}

	private void open() {
		MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
		MongoClient mongoClient = new MongoClient(connectionString);
		db = mongoClient.getDatabase("consent");
	}

	public void insertDocument(String objectId) {
		open();
		MongoCollection<Document> collection = db.getCollection("dataAccessRequest");

		String jsonlist = "{\n" + 
				"    \"investigator\" : \"Walter Lo Forte\",\n" + 
				"    \"institution\" : \"Belax\",\n" + 
				"    \"department\" : \"QA\",\n" + 
				"    \"division\" : null,\n" + 
				"    \"address1\" : \"123 Fake St.\",\n" + 
				"    \"address2\" : null,\n" + 
				"    \"city\" : \"Mendoza\",\n" + 
				"    \"zipcode\" : \"55000\",\n" + 
				"    \"country\" : \"Argentina\",\n" + 
				"    \"state\" : \"Mendoza\",\n" + 
				"    \"projectTitle\" : \"sfefse\",\n" + 
				"    \"rus\" : \"sefesfe\",\n" + 
				"    \"non_tech_rus\" : \"fsef\",\n" + 
				"    \"diseases\" : true,\n" + 
				"    \"forProfit\" : false,\n" + 
				"    \"onegender\" : false,\n" + 
				"    \"pediatric\" : false,\n" + 
				"    \"illegalbehave\" : false,\n" + 
				"    \"addiction\" : false,\n" + 
				"    \"sexualdiseases\" : false,\n" + 
				"    \"stigmatizediseases\" : false,\n" + 
				"    \"vulnerablepop\" : false,\n" + 
				"    \"popmigration\" : false,\n" + 
				"    \"psychtraits\" : false,\n" + 
				"    \"nothealth\" : false,\n" + 
				"    \"userId\" : 3333,\n" + 
				"    \"sortDate\" : ISODate(\"2018-09-06T13:11:26.505Z\"),\n" + 
				"    \"datasetId\" : [ \n" + 
				"        \""+objectId+"\"\n" + 
				"    ],\n" + 
				"    \"datasetDetail\" : [ \n" + 
				"        {\n" + 
				"            \"datasetId\" : \""+objectId+"\",\n" + 
				"            \"name\" : \"Test Name\"\n" + 
				"        }\n" + 
				"    ],\n" + 
				"    \"dar_code\" : \"DAR-1000\"\n" + 
				"}";

		Document doc = Document.parse(jsonlist.toString());

		collection.insertOne(doc);
		ObjectId id = (ObjectId) doc.get("_id");
		System.out.println(id);
	}

	public void deleteDocument() {
		open();
		MongoCollection<Document> collection = db.getCollection("dataAccessRequest");
		collection.deleteOne(new Document("dar_code", "DAR-1000"));
	}

	public String getDocumentId() {
		open();
		MongoCollection<Document> collection = db.getCollection("dataAccessRequest");
		Document document = collection.find(new BasicDBObject("dar_code", "DAR-1000"))
				.projection(Projections.fields(Projections.include("_id"))).first();
		ObjectId id = document.getObjectId("_id");
		return id.toHexString();
	}

}
