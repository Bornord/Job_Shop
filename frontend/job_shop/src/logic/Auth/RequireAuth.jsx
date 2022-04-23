import { useLocation, Navigate, Outlet } from 'react-router-dom';
import { useSelector } from 'react-redux';
import {isAuthentifated} from '../features/user'

function RequireAuth() {
	const user = useSelector((state) => state.user.value);
	const location = useLocation();

	return isAuthentifated(user) ? 
		<Outlet />
	: 
		<Navigate to={`/Welcome`} state={{ from: location }} replace />
	
}

export default RequireAuth;
