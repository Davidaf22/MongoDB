1) inserir este  documento : "p4",c1","pro3",5,"02/02/2019"
db.pedidos.insert({"_id":"p4","codcli":"c1","codpro":"pro3","cantidade":5,"data":"02/02/2019"})

2) actualizar un par clave valor por clave do documento : exemplo actualizar o campo codpro do pedido  p3 a o valor pro4
db.pedidos.update({_id:"p4"},{$set:{codpro:"pro4"}})

3)incrementar un par clave valor por clave. exemplo: aumentar en 7 a cantidade correspondente ao pedido p2.
db.pedidos.update({"_id":"p2"},{$inc:{"cantidade":7}})

4) amosar o documento correspondente ao pedido p3
db.pedidos.find({"_id":"p3"})

5) amosar o codigo do cliente, o codigo do producto e a cantidade correspondentes ao pedido p1
db.pedidos.find({"_id":"p1"},{"codcli":1,"codpro":1,"cantidade":1,"_id":0})

6) amosar  o codigo do cliente e  o codigo do producto correspondentes aos pedidos que teñan no campo cantidade un valor superior a 2
db.pedidos.find({cantidade:{$gt:2}},{"codcli":1,"codpro":1,"_id":0})

7) amosar  o codigo do cliente e  o codigo do producto correspondentes aos pedidos que teñan no campo cantidade un valor superior a 2 e inferior a 5
db.pedidos.find({cantidade:{$gt:2,$lt:5}},{"codcli":1,"codpro":1,"_id":0})

8) amosar   o codigo do cliente e  o codigo do producto correspondentes a todos os pedidos.
db.pedidos.find({_id:{$exists:true}},{"codcli":1,"codpro":1,"_id":0})

9) aumentar  no seu dobre a cantidade correspondente ao pedido p4 .
db.pedidos.update({"_id":"p4"},{$mul:{"cantidade":2}})
