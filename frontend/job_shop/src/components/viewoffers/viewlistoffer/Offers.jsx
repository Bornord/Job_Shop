import React, { useState, useEffect } from 'react';
import './Offers.scss';
import {api,get,post} from '../../../logic/api/api'
import { useDispatch, useSelector } from 'react-redux';
import Offer from '../viewoffer/Offer'

export default function Offers(){
    const [offers,setOffers] = useState([])
    const user = useSelector((state) => state.user.value)
    const [error,setError]  = useState('') 

    useEffect(()=>{
        post("getOffersFromRecruiter",{id:user.id},(res)=>{
            console.log(res);
            setOffers(res.data)
        },(e)=>{
            if(e.message != null){
                setError(e.message);
            } else{
                setError(e)
            } 
        }  )
    },[] )
    return <div className='offers-list'>
        {error && 
            <p>{error}</p>
        }
        {
            offers.map((offer)=>{
                const {Contract,description,endDate,id,idealProfile,recruiter,salary,startDate,subTitle,term,title,status} = offer 
                return <Offer key={id}  Contract ={Contract} 
                description={description} 
                endDate={endDate} 
                id={id} 
                idealProfile={idealProfile} 
                recruiter={recruiter} 
                salary={salary} 
                startDate={startDate} 
                subTitle={subTitle} 
                term={term} 
                title={title}
                status={status}   />
            } )
        } 
    </div>
} 