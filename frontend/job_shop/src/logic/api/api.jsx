import axios from 'axios';

export const post = async (path,data,success,error)=>{
	try{
		const res = await api.post("/Server?op="+path,data)
		success(res)		
	}catch(e){
		error(e)
	}
}
export const get = async (path,success,error)=>{
	try{
		const res = await api.get("/Server?op="+path)
		if(success!=null)
			success(res)		
	}catch(e){
		if(error!=null)
			error(e)
	}
}

export default api = axios.create({
	baseURL: 'http://localhost:3500',
});

