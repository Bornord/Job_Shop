import { useContext } from 'react-dom';

const useAuth = () => {
	// Reducer à appeler
	const user = useSelector((state) => state.user.value);
	return ();
};

export default useAuth;
