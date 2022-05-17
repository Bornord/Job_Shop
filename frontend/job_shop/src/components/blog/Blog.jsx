import React, { useState } from 'react';
import './Blog.scss';


function Blog(title,subtitle,author,date,content) {


	return <div className='blog'>
        <h1>{title}</h1>
        <h2>{subtitle}</h2>
        <h3>{author}</h3>
        <h4>{date}</h4>
        <p>{content}</p>
	</div>
}

export default Blog;