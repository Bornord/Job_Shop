import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from "react-redux";
import { BsCircle,BsCircleFill} from "react-icons/bs";
import SelectButton2 from '../../components/buttons/selectButton2/SelectButton2'
import { useNavigate } from 'react-router-dom';
import {signupAsRecruiter,signupAsStudent} from "../../logic/features/user";
import './Signup.scss'

function Signup({status,inputs,title,subtitle,isValid}) {
    const numberOfInputPerPage = 5
    let _pages = [{start:0,end:numberOfInputPerPage,isCurrent : true}]
    for(let i = 1; i < inputs.length/numberOfInputPerPage ; i++){
        _pages.push({
            start:numberOfInputPerPage*i,
            end:numberOfInputPerPage*(i+1),
            isCurrent : false
        })
    }

    const [state,setState] = useState(inputs.map((input)=>{return {id:input.id,value:""}}))
    const [pages,setPages] = useState(_pages)
	const dispatch = useDispatch()
    let navigate = useNavigate()

    const handleInputs= (id,value)=>{
        setState((prevState)=>{
            const i = prevState.findIndex((input)=>input.id == id)
            let nextState = [...prevState]
            nextState[i].value = value
            return nextState
        })
    }
    const handlePageChange = (i) => {
        setPages((prevPages)=>{
            return prevPages.map(
                (page)=>{
                    const j = prevPages.indexOf(page)
                    return {
                        ...page,
                        isCurrent : j == i ? true : false  
                    }
                }
            )
        })
    }
    const handleSubmit = () => {
        if(isValid(state)){
            const f = status == 2000 ? signupAsStudent: signupAsRecruiter
            dispatch(f(state))
            navigate('/');
        }
	}
	return <div className='signup'
        onWheel={(e)=>{
            if(inputs.length > numberOfInputPerPage){
                const y = Math.sign(e.deltaY)
                const i = pages.indexOf(pages.find((p)=> p.isCurrent))
                handlePageChange(Math.abs((i+y)%pages.length))
            }
        }}
    >
        <h1 className="title">{title}</h1>
        <h2 className="subtitle">{subtitle}</h2>
        <form 
            onSubmit={e => {
				e.preventDefault()
				handleSubmit()
            }}
        >
            {inputs.filter((input)=>{
                const i = inputs.indexOf(input)
                const page = pages.find((p)=> p.isCurrent)
                return (i >= page.start && i < page.end) && input
            }).map(
                (input)=>{
                    const i = inputs.indexOf(input)
                    return <div key={input.id}className="input-container ic1">
                        <input id={input.id} type={input.type} className="signup-input" value={state[i].value} placeholder=" "
                        onChange={(e)=>{handleInputs(input.id,e.target.value)}}
                        />
                        <div className="signup-cut"></div>
                        <label htmlFor={input.id} className="placeholder" > {input.label}</label>
                    </div>
                }
            )}

            <SelectButton2
                className={"submit-button"}
                type='submit'
            >
                    S'inscire
            </SelectButton2>

        </form>
        <div className="pages">
            {inputs.length > numberOfInputPerPage && pages.map(
                (page)=>{
                    const i = pages.indexOf(page)
                    return <div onClick={()=>{handlePageChange(i)}} key={page.start} className="page">
                        {page.isCurrent ? 
                           <BsCircleFill /> : 
                           <BsCircle />
                        }
                    </div>
                }
            )}
        </div>
    </div>;
}

export default Signup;
