const express = require('express');
const app = express();

app.use(express.json());

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
app.use('/api', (req, res, next) => {
	console.log('Bien reçu, bien reçu.');
	res.status(201).json({ msg: 'hello world back' });
});

module.exports = app;
