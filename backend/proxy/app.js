const express = require('express');
const axios = require('axios');
const app = express();

app.use(express.json());
api = axios.create({
	baseURL: 'http://localhost:8080/JobShop_WebProject/',
});
const Post = async (path, data, success, error) => {
	console.log("POST "+path);
	console.log("data ");
	console.log(data);
	try {
		const res = await api.post('/Server?op=' + path, data);
		console.log(res.data);
		if (success != null) success(res.data);
	} catch (e) {
		//console.log(e);
		if (error != null) error(e);
	}
};
const Get = async (path, success, error) => {
	console.log("GET "+path);
	try {
		const res = await api.get('/Server?op=' + path);
		if (success != null) success(res.data);
	} catch (e) {
		if (error != null) error(e);
	}
};
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
		 if (req.query.op != null) {
		 	console.log(req.query.op);
		 	Get(
		 		req.query.op,
		 		(data) => {
					 console.log(data);
		 			res.status(201).json(data);
		 		},
		 		(e) => {
					console.log("error on server " + e);
		 			res.status(404).json({ msg: 'error on server' + e});
		 		}
		 	);
		 } else {
			 console.log("error on proxy");
		 	res.status(404).json({ msg: 'error on proxy' });
		 }
		//next();
	})
	.post((req, res, next) => {
		console.log(req.query);
		 if (req.query.op != null) {
			 console.log(req.body);
		 	Post(
		 		req.query.op,
				 req.body,
		 		(data) => {
		 			res.status(201).json(data);
		 		},
		 		() => {
		 			res.status(404).json({ msg: 'error' });
				}
		 	);
		 } else {
		 	res.status(404).json({ msg: 'error' });
		 }
		//next();
	});

app.use('/', (req, res, next) => {
	console.log('Bien reçu, bien reçu.');
	console.log('le paquet vaut :');
	console.log(req.body);
	res.status(201).json(req.body);
});

module.exports = app;
