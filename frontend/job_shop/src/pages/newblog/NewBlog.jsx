import React, { useState, useEffect  } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import "./NewBlog.scss";
import SelectButton2 from '../../components/buttons/selectButton2/SelectButton2';
import {api,get,post} from '../../logic/api/api'

function NewBlog(){
	let navigate = useNavigate();
	const user = useSelector((state) => state.user.value);
	const [title,setTitle] = useState("");
	const [subtitle,setSubtitle] = useState("");
    const [content,setContent] = useState("");
    const [error,setError] = useState(""); 
    const validate = () => {
        return user.status === 2002 
            && title !== ""
            && subtitle !== ""
            && content !== ""
    } 
    const handleSubmit=()=> {
        if(!validate()) return
        const data ={
            title:title,
            subtitle:subtitle,
            idAuthor:user.id,
            content:content,
        } 
        post("addBlog",data,(res) => {
            console.log(res)
            navigate("/")
        },(e)=>{
            console.log(e);
            setError(e)
        } )
    } 
    return <div className="new-blog">
        <h1>Nouveau blog</h1>
        {error && 
            <p>{error}</p>
        }	
        <div className="new-blog-form">
					<div className="input-container ic1">
						<input
							id="title"
							className={'new-blog-input'}
							type="text"
							value={title}
							onChange={(e) => setTitle(e.target.value)}
							placeholder=" "
						/>
						<div className="cut"></div>
						<label htmlFor="title" className="placeholder">
							Titre :
						</label>
					</div>
					<div className="input-container ic2">
						<input
							id="subtitle"
							className={'new-blog-input'}
							type="text"
							value={subtitle}
							onChange={(e) => setSubtitle(e.target.value)}
							placeholder=" "
						/>
						<div className="cut"></div>
						<label htmlFor="subtitle" className="placeholder">
							Sous titre :
						</label>
					</div>
					<div className="input-container ic2">
						<textarea
							id="content"
							className={'new-blog-input'}
							type="text"
							value={content}
							onChange={(e) => setContent(e.target.value)}
							placeholder=" "
						/>
						<div className="cut"></div>
						<label htmlFor="content" className="placeholder">
							Article :
						</label>
					</div>
					
					<SelectButton2 className={'submit_button'} type="submit" onClick={handleSubmit} >
						Ajouter le blog
					</SelectButton2>
				</div>
    </div>
} 

export default NewBlog;