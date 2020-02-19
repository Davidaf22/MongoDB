package mongodb_1;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import org.bson.Document;
/**
 * @author David
 */

public class MongoDB_1 {
    
    public static void InserirPedidos(String id, String codcli, String codpro, double cantidade, String data){
        
        //1.inserir este  documento : "p4",c1","pro3",5,"02/02/2019"
        //CONSULTA: db.pedidos.insert({"_id":"p4","codcli":"c1","codpro":"pro3","cantidade":5,"data":"02/02/2019"})
        
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("tenda");
        MongoCollection collection = database.getCollection("pedidos");
        
        //BasicDBObject document = new BasicDBObject();
        Document document = new Document();
        
        document.put("_id", id);
        document.put("codcli", codcli);
        document.put("codpro", codpro);
        document.put("cantidade", cantidade);
        document.put("data", data);
        
        collection.insertOne(document);
        System.out.println("Pedido inserido");
        
        mongoClient.close();
    }
    
    public static void ActualizarPedidosCodpro(String id, String codpro){
        
       //2) actualizar un par clave valor por clave do documento : exemplo actualizar o campo codpro do pedido  p3 a o valor pro4
       //db.pedidos.update({_id:"p4"},{$set:{codpro:"pro4"}})

        
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("tenda");
        MongoCollection collection = database.getCollection("pedidos");
                
        BasicDBObject query = new BasicDBObject();
        query.put("_id", id);
 
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("codpro", codpro);
 
        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);
 
        collection.updateOne(query, updateObject);
        
         System.out.println("Pedido actualizado");
        
        mongoClient.close();
    }
    
    public static void IncrementarPedidos(String id, double aumento){           
        //3)incrementar un par clave valor por clave. exemplo: aumentar en 7 a cantidade correspondente ao pedido p2.
        //db.pedidos.update({"_id":"p2"},{$inc:{"cantidade":7}})
        
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("tenda");
        MongoCollection collection = database.getCollection("pedidos");
        
        BasicDBObject query = new BasicDBObject();
        query.put("_id", id);
 
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("cantidade",aumento);
 
        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$inc", newDocument);
 
        collection.updateOne(query, updateObject);
        
         System.out.println("Pedido aumentado");
        
        mongoClient.close();
    }
    
    public static void amosarPedido(String id){
        //4) amosar o documento correspondente ao pedido p3
        //db.pedidos.find({"_id":"p3"})
        
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("tenda");
        MongoCollection collection = database.getCollection("pedidos");
        
        BasicDBObject query = new BasicDBObject();
        query.put("_id", id);
        
        FindIterable<Document> cursornovo = collection.find(query);
        MongoCursor<Document> iterator = cursornovo.iterator();
        while (iterator.hasNext()){
            Document next = iterator.next();
                String j1 = next.getString("_id");
                String j2 = next.getString("codcli");
                String j3 = next.getString("codpro");
                Double j4 = next.getDouble("cantidade");
                String j5 = next.getString("data");
                
                System.out.println(j1);
                System.out.println(j2);
                System.out.println(j3);
                System.out.println(j4);
                System.out.println(j5);  
            }
        
        mongoClient.close();        
    }
    
    public static void amosarPedido2(String id){
        //5) amosar o codigo do cliente, o codigo do producto e a cantidade correspondentes ao pedido p1
        //db.pedidos.find({"_id":"p1"},{"codcli":1,"codpro":1,"cantidade":1,"_id":0})
        
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("tenda");
        MongoCollection collection = database.getCollection("pedidos");
        
        
        BasicDBObject query = new BasicDBObject();
        query.put("_id", id);
        
        FindIterable<Document> cursornovo = collection.find(query);
        MongoCursor<Document> iterator = cursornovo.iterator();
        while (iterator.hasNext()){
            Document next = iterator.next();
                String j2 = next.getString("codcli");
                String j3 = next.getString("codpro");
                Double j4 = next.getDouble("cantidade");
                
                System.out.println(j2);
                System.out.println(j3);
                System.out.println(j4);
            }
        
        mongoClient.close();                
    }
    
    public static void amosarPedido3(double cantidad){
        //6) amosar  o codigo do cliente e  o codigo do producto correspondentes aos pedidos que teñan no campo cantidade un valor superior a 2
        //db.pedidos.find({cantidade:{$gt:2}},{"codcli":1,"codpro":1,"_id":0})
        
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("tenda");
        MongoCollection collection = database.getCollection("pedidos");
        
        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$gt", cantidad);
        
        BasicDBObject query = new BasicDBObject();
        query.put("cantidade", updateObject);        
        
        FindIterable<Document> cursornovo = collection.find(query);
        MongoCursor<Document> iterator = cursornovo.iterator();
        while (iterator.hasNext()){
            Document next = iterator.next();
                String j2 = next.getString("codcli");
                String j3 = next.getString("codpro");
                
                System.out.println(j2);
                System.out.println(j3);
                System.out.println();
            }
        
        mongoClient.close();                 
    }
    
    public static void amosarPedido4(double cantidadMin, double cantidadMax){
        //7) amosar  o codigo do cliente e  o codigo do producto correspondentes aos pedidos que teñan no campo cantidade un valor superior a 2 e inferior a 5
        //db.pedidos.find({cantidade:{$gt:2,$lt:5}},{"codcli":1,"codpro":1,"_id":0})
        
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("tenda");
        MongoCollection collection = database.getCollection("pedidos");
        
        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$gt", cantidadMin);
        updateObject.put("$lt", cantidadMax);      
        
        BasicDBObject query = new BasicDBObject();
        query.put("cantidade", updateObject);        
        
        FindIterable<Document> cursornovo = collection.find(query);
        MongoCursor<Document> iterator = cursornovo.iterator();
        while (iterator.hasNext()){
            Document next = iterator.next();
                String j2 = next.getString("codcli");
                String j3 = next.getString("codpro");
                
                System.out.println(j2);
                System.out.println(j3);
                System.out.println();
            }        
        mongoClient.close();
    }
    
    public static void amosarPedido5(){
        //8) amosar   o codigo do cliente e  o codigo do producto correspondentes a todos os pedidos.
        //db.pedidos.find({_id:{$exists:true}},{"codcli":1,"codpro":1,"_id":0})
        
       
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("tenda");
        MongoCollection collection = database.getCollection("pedidos");
        
        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$exists", true);
   
        
        BasicDBObject query = new BasicDBObject();
        query.put("_id", updateObject);        
        
        FindIterable<Document> cursornovo = collection.find(query);
        MongoCursor<Document> iterator = cursornovo.iterator();
        while (iterator.hasNext()){
            Document next = iterator.next();
                String j2 = next.getString("codcli");
                String j3 = next.getString("codpro");
                
                System.out.println(j2);
                System.out.println(j3);
                System.out.println();
            }        
        mongoClient.close();
    }
    
    public static void duplicarPedidos(String id,double multiplicador){
        //9) aumentar  no seu dobre a cantidade correspondente ao pedido p4 .
        //db.pedidos.update({"_id":"p4"},{$mul:{"cantidade":2}})
        
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("tenda");
        MongoCollection collection = database.getCollection("pedidos");
        
        BasicDBObject query = new BasicDBObject();
        query.put("_id", id);
 
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("cantidade", multiplicador);
 
        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$mul", newDocument);
 
        collection.updateOne(query, updateObject);
        
         System.out.println("Pedido duplicado");
        
        mongoClient.close();
        
    }
    
    public static void main(String[] args) {
        
        //MongoDB_1.InserirPedidos("p4","c1","pro3",5,"02/02/2019");
        //MongoDB_1.ActualizarPedidosCodpro("p3","pro4");
        //MongoDB_1.IncrementarPedidos("p2", 7);
        //MongoDB_1.amosarPedido("p3");
        //MongoDB_1.amosarPedido2("p1");
        //MongoDB_1.amosarPedido3(2);
        //MongoDB_1.amosarPedido4(2,5);
        //MongoDB_1.amosarPedido5();
        //MongoDB_1.duplicarPedidos("p4",2);
    }
    
}
