import React, { useState, useEffect } from 'react';
import './ListStatus.scss';
import {api,get,post} from '../../../logic/api/api'
import { useDispatch, useSelector } from 'react-redux';
import Status from "../status/Status"

export default function ListStatus(){
    const [status,setStatus] = useState([]) 
    const user = useSelector((state) => state.user.value)
    const [error,setError]  = useState('') 

    useEffect(()=>{
        post("getStatusFromUserId",{id:user.id},(res)=>{
            setStatus(res.data)
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
           status.map((s)=>{
               const {step,offer,student} = s
               return <Status key={s} step={step} offer={offer} student={student} />   
           } )
       } 
    </div>
}
