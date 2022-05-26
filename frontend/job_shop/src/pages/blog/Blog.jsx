import React, { useState, useEffect } from 'react';
import './Blog.scss';
import { TiTimes } from 'react-icons/ti';
import Blog from '../../components/blog/Blog'
import {api,get,post} from '../../logic/api/api';


function BlogPage() {
    const [blogs,setBlogs] = useState([])
    useEffect(() => {
        get("getBlogs",(res)=>{
             setBlogs((prev)=>{
                 const next = res.data.map((data) =>{
                     const {id,date,idAuthor,content,title,subtitle} = data
                     return {
                        id:id,
                        date:date,
                        idAuthor:idAuthor,
                        content:content,
                        title:title,
                        subtitle:subtitle
                     } 
                 } )
                 return next
             } )
            }  ,(e)=>console.log(e))
	}, []);

	return <div className='blog-page'>
        <h1>Articles :</h1>
        <ul>

        {blogs.map(
            (blog)=>{
                const {id,date,idAuthor,content,title,subtitle} = blog

                return <li key={id}>
                             <Blog 
                    key ={id} 
                    title={title} 
                    subtitle={subtitle} 
                    author={idAuthor} 
                    date={date}
                    content={content}/>
                    
                    </li>
           }
        )}
        </ul>
	</div>
}

export default BlogPage;