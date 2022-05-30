import React, { useState, useEffect } from 'react';
import './Status.scss';


export default function Status( {step,offer,student} ){
    console.log(offer);
    return <div  className='status'>
        <h2>Offre :</h2>
        <h1>{offer.title} </h1>
        <h2>{offer.subTitle} </h2>
        <h2>{step}</h2>
    </div>
} 