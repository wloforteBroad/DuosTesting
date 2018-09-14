package stepDefinitions;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.MongoCollection;

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

	public void insertDocument(String objectId, boolean manualReview) {
		open();
		MongoCollection<Document> collection = db.getCollection("dataAccessRequest");

		String dar = "{\"investigator\":\"Test Investigator\",\"institution\":\"Belax\",\"department\":\"QA\",\"division\":null,\"address1\":\"123 Fake St.\",\"address2\":null,\"city\":\"Mendoza\",\"zipcode\":\"55000\",\"country\":\"Argentina\",\"state\":\"Mendoza\",\"projectTitle\":\"TEST PROJECT TITLE\",\"rus\":\"TEST RUS\",\"non_tech_rus\":\"NON TECH RUS\",\"diseases\":true,\"forProfit\":false,\"onegender\":false,\"pediatric\":false,\"illegalbehave\":false,\"addiction\":false,\"sexualdiseases\":false,\"stigmatizediseases\":false,\"vulnerablepop\":false,\"popmigration\":false,\"psychtraits\":false,\"nothealth\":false,\"userId\":3333,\"sortDate\":ISODate(\"2018-09-06T13:11:26.505Z\"),\"datasetId\":[\""+objectId+"\"],\"datasetDetail\":[{\"datasetId\":\""+objectId+"\",\"name\":\"Test Name\"}],\"dar_code\":\"DAR-1000\"}";
		
		String darMR = "{\"investigator\":\"Test Investigator\",\"institution\":\"Belax\",\"department\":\"QA\",\"division\":null,\"address1\":\"123 Fake St.\",\"address2\":null,\"city\":\"Mendoza\",\"zipcode\":\"55000\",\"country\":\"Argentina\",\"state\":\"Mendoza\",\"projectTitle\":\"TEST PROJECT TITLE\",\"rus\":\"TEST RUS\",\"non_tech_rus\":\"NON TECH RUS\",\"diseases\":true,\"forProfit\":false,\"onegender\":false,\"pediatric\":false,\"illegalbehave\":false,\"addiction\":false,\"sexualdiseases\":false,\"stigmatizediseases\":false,\"vulnerablepop\":false,\"popmigration\":false,\"psychtraits\":false,\"nothealth\":false,\"userId\":3333,\"sortDate\":ISODate(\"2018-09-06T13:11:26.505Z\"),\"restriction\":{\"operands\":[{\"operands\":[{\"name\":\"http://purl.obolibrary.org/obo/DUO_0000015\",\"type\":\"named\"},{\"operand\":{\"name\":\"http://purl.obolibrary.org/obo/DUO_0000011\",\"type\":\"named\"},\"type\":\"not\"},{\"operand\":{\"name\":\"http://www.broadinstitute.org/ontologies/DUOS/control\",\"type\":\"named\"},\"type\":\"not\"}],\"type\":\"and\"},{\"name\":\"http://purl.obolibrary.org/obo/DOID_162\",\"type\":\"named\"},{\"name\":\"http://purl.obolibrary.org/obo/DUO_0000018\",\"type\":\"named\"}],\"type\":\"and\"},\"datasetId\":[\""+objectId+"\"],\"datasetDetail\":[{\"datasetId\":\""+objectId+"\",\"name\":\"Test Name\"}],\"dar_code\":\"DAR-2000\"}";
 
		Document doc = Document.parse(dar.toString());
		Document doc1 = Document.parse(darMR.toString());

		if (manualReview) {
			collection.insertOne(doc);
			ObjectId id = (ObjectId) doc.get("_id");
			System.out.println(id);
		} else {
			collection.insertOne(doc1);
			ObjectId id = (ObjectId) doc1.get("_id");
			System.out.println(id);
		}
	}

	public void deleteDocument(boolean manualReview) {
		open();
		MongoCollection<Document> collection = db.getCollection("dataAccessRequest");
		if (manualReview) {
			collection.deleteOne(new Document("dar_code", "DAR-1000"));
		} else {
			collection.deleteOne(new Document("dar_code", "DAR-2000"));
		}
	}

	public String getDocumentId(boolean manualReview) {
		open();
		MongoCollection<Document> collection = db.getCollection("dataAccessRequest");
		Document document;
		
		if (manualReview) {
			document = collection.find(new BasicDBObject("dar_code", "DAR-1000"))
					.projection(Projections.fields(Projections.include("_id"))).first();
		} else {
			document = collection.find(new BasicDBObject("dar_code", "DAR-2000"))
					.projection(Projections.fields(Projections.include("_id"))).first();
		}		
		
		ObjectId id = document.getObjectId("_id");	
		return id.toHexString();
	}

}
