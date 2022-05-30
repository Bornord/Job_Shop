import React, { useState, useEffect } from 'react';
import './Offer.scss';


export default function Offer( {Contract,description,endDate,id,idealProfile,recruiter,salary,startDate,subTitle,term,title,status} ){
    console.log(status);
    return <div className='offer'>
        <h1>{title}</h1>
        <h2>{subTitle}</h2>
        <p>{description}</p>
        <h3>Etudiants correspondant à votre recherche</h3>
        {status.map((s)=>{
            return <span key={s} >
                <p>{s.student.name} {s.student.surname}</p>
            </span>
        })}  
    </div>
} 