// require = import en python ou en java
// serveur principal qui gère le backend
const http = require('http');

// server backend qui gère les requêtes
const app = require('./app.js');

// amélioration du code
const normalizePort = (val) => {
	const port = parseInt(val, 10);
	if (isNaN(port)) {
		return val;
	} else if (port >= 0) {
		return val;
	} else {
		return false;
	}
};

const port = normalizePort(process.env.PORT || 8000);

// traitement des errors
const errorHandler = (error) => {
	if (error.syscall !== 'listen') {
		throw error;
	}
	const address = server.address();
	const bind =
		typeof address === 'string' ? 'pipe' + address : 'port:' + port;
	switch (error.code) {
		case 'EACCES':
			console.error(bind + ' requires elevated privileges.');
			process.exit(1);
			break;

		case 'EADDRINUSE':
			console.error(bind + ' is already in use.');
			process.exit(1);
			break;

		default:
			throw error;
	}
};

// createServer : prend en argument une FONCTION (requête, résultat)
// app contient une fonction qui prend en charge ce rôle.
const server = http.createServer(app);

// on laisse le serveur écouter.
server.on('error', errorHandler);
server.on('listening', () => {
	const address = server.address();
	const bind = typeof add === 'string ' ? 'pipe ' + address : 'port ' + port;
	console.log("J'écoute sur le " + bind);
});

server.listen(port);
