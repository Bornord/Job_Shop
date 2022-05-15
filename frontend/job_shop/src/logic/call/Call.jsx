import axios from 'axios';
import React, { useState } from 'react';

function Call() {
	const [result, setResult] = useState('');
	axios
		.get(`http://localhost:8000/api`)
		.then((res) => {
			console.log(res.data.msg);
			setResult(res.data.msg);
		})
		.catch((error) => console.log(error));
	return (
		<div>
			<h1>Hello World.</h1>
			<p>{result}</p>
		</div>
	);
}

export default Call;
