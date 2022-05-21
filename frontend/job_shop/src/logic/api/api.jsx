import axios from 'axios';


export const api = axios.create({
	baseURL: 'http://localhost:8000',
});

export const post = async (path,data,success,error)=>{
	try{
		const res = await api.post("/Proxy?op="+path,data)
		success(res)		
	}catch(e){
		error(e)
	}
}
export const get = async (path,success,error)=>{
	try{
		const res = await api.get("/Proxy?op="+path)
		if(success!=null)
			success(res)		
	}catch(e){
		if(error!=null)
			error(e)
	}
}



