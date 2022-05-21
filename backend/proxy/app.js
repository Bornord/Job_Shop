const express = require('express');
const axios = require("axios");
const app = express();

app.use(express.json());
api = axios.create({
	baseURL: 'http://localhost:3500',
});
const Post = async (path,data,success,error)=>{
	try{
		const res = await api.post("/Server?op="+path,data)
		success(res)		
	}catch(e){
		error(e)
	}
}
const Get = async (path,success,error)=>{
	try{
		const res = await api.get("/Server?op="+path)
		if(success!=null)
			success(res)		
	}catch(e){
		if(error!=null)
			error(e)
	}
}
/* 
Déclaration des permissions CORS
- Cela inclut l'origine des utilisateurs qui peuvent faire des requêtes.
- (jsp)
- Les méthodes autorisées.
*/
app.use((req, res, next) => {
	res.setHeader('Access-Control-Allow-Origin', '*');
	res.setHeader(
		'Access-Control-Allow-Headers',
		'Origin, X-Requested-With, Content, Accept,Content-Type, Authorization'
	);
	res.setHeader(
		'Access-Control-Allow-Methods',
		'GET, POST, PUT, DELETE, PATCH, OPTIONS'
	);
	next();
});
/*
Fonction principale qui conditionne les appels et leur traitement dans l'environnement de test
*/
app.route('/Proxy')
	.get((req, res, next) => {
		console.log(req.query);
		if(req.query.op != null){
			console.log(req.query.op);
			Get(req.query.op,
				(data)=>{
					res.status(201).json(data);
				},
				()=>{
					res.status(404).json({ msg: 'error on server' });
				}
				)
		}else{
			res.status(404).json({ msg: 'error on proxy' });
		}
	})
	.post((req, res, next) => {
		console.log(req.query);
		if(req.query.op != null){
			Post(req.query.op,
				(data)=>{
					res.status(201).json(data);
				},
				()=>{
					res.status(404).json({ msg: 'error' });
				}
				)
		}else{
			res.status(404).json({ msg: 'error' });
		}
	})

app.use('/', (req, res, next) => {
	console.log('Bien reçu, bien reçu.');
	res.status(201).json({ msg: 'hello world back' });
});


module.exports = app;
