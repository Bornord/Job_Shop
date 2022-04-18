import { useNavigate } from 'react-router-dom';

function Login() {
	let navigate = useNavigate();
	return (
		<div>
			<input
				type="button"
				value="J'ai déjà un comtpe"
				onClick={() => {
					navigate('/login');
				}}
			/>
		</div>
	);
}

export default Login;
