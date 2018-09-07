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

		String jsonlist = "{\"eraAuthorized\" : \"true\",\n" + "    \"nihUsername\" : \"PI NIH\",\n"
				+ "    \"investigator\" : \"PI NAME\",\n" + "    \"institution\" : \"BelaxNEW\",\n"
				+ "    \"department\" : \"QANEW\",\n" + "    \"division\" : null,\n"
				+ "    \"address1\" : \"san martin 972NEW\",\n" + "    \"address2\" : null,\n"
				+ "    \"city\" : \"mendozaNEW\",\n" + "    \"zipcode\" : \"5500\",\n"
				+ "    \"country\" : \"ArgentinaNEW\",\n" + "    \"state\" : \"mendozaNEW\",\n"
				+ "    \"eraDate\" : null,\n" + "    \"projectTitle\" : \"ERA 2\",\n" + "    \"rus\" : \"efsefse\",\n"
				+ "    \"non_tech_rus\" : \"sfefse\",\n" + "    \"diseases\" : true,\n" + "    \"forProfit\" : false,\n"
				+ "    \"onegender\" : false,\n" + "    \"pediatric\" : false,\n" + "    \"addiction\" : false,\n"
				+ "    \"illegalbehave\" : false,\n" + "    \"sexualdiseases\" : false,\n"
				+ "    \"stigmatizediseases\" : false,\n" + "    \"vulnerablepop\" : false,\n"
				+ "    \"popmigration\" : false,\n" + "    \"psychtraits\" : false,\n" + "    \"nothealth\" : false,\n"
				+ "    \"userId\" : 8889,\n" + "    \"restriction\" : {\n" + "        \"type\" : \"and\",\n"
				+ "        \"operands\" : [ \n" + "            {\n" + "                \"type\" : \"and\",\n"
				+ "                \"operands\" : [ \n" + "                    {\n"
				+ "                        \"type\" : \"not\",\n" + "                        \"operand\" : {\n"
				+ "                            \"type\" : \"named\",\n"
				+ "                            \"name\" : \"http://purl.obolibrary.org/obo/DUO_0000015\"\n"
				+ "                        }\n" + "                    }, \n" + "                    {\n"
				+ "                        \"type\" : \"not\",\n" + "                        \"operand\" : {\n"
				+ "                            \"type\" : \"named\",\n"
				+ "                            \"name\" : \"http://purl.obolibrary.org/obo/DUO_0000011\"\n"
				+ "                        }\n" + "                    }, \n" + "                    {\n"
				+ "                        \"type\" : \"not\",\n" + "                        \"operand\" : {\n"
				+ "                            \"type\" : \"named\",\n"
				+ "                            \"name\" : \"http://www.broadinstitute.org/ontologies/DUOS/control\"\n"
				+ "                        }\n" + "                    }\n" + "                ]\n"
				+ "            }, \n" + "            {\n" + "                \"type\" : \"named\",\n"
				+ "                \"name\" : \"http://purl.obolibrary.org/obo/DUO_0000018\"\n" + "            }\n"
				+ "        ]\n" + "    },\n" + "    \"valid_restriction\" : true,\n"
				+ "    \"translated_restriction\" : \"Samples will be used under the following conditions:<br>Data will be used for health/medical/biomedical research [HMB(CC)]<br>Data will not be used for commercial purpose<br>\",\n"
				+ "    \"sortDate\" : ISODate(\"2018-08-21T13:01:10.826Z\"),\n" + "    \"datasetId\" : [ \n"
				+ "        \"SC-20659\"\n" + "    ],\n" + "    \"datasetDetail\" : [ \n" + "        {\n"
				+ "            \"datasetId\" : \"" + objectId + "\",\n"
				+ "            \"name\" : \"(Bucienne Monco) - Muc-1 Kidney Disease\"\n" + "        }\n" + "    ],\n"
				+ "    \"dar_code\" : \"DAR-1000\"\n" + "}";

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
