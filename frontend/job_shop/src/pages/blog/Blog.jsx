import React, { useState, useEffect } from 'react';
import './EditSurvey.scss';
import { TiTimes } from 'react-icons/ti';
import {api,get,post} from '../../logic/api/api';


function BlogPage() {
    const [blogs,setBlogs] = useState([])
    useEffect(() => {
		get("getBlogs",(res)=>setBlogs(res.data),(e)=>console.log(e))
	}, []);

	return <div className='blog-page'>
        <h1>Articles :</h1>
        {blogs.map(
            (blog)=>{
                return <Blog 
                    title={blog.title} 
                    subtitle={blog.subtitle} 
                    author={blog.author} 
                    date={blog.date}
                    content={blog.content}/>})}
	</div>
}

export default BlogPage;