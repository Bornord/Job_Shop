import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './AddQuestion.scss';
import SelectButton2 from '../../components/buttons/selectButton2/SelectButton2';
import { TiTimes } from 'react-icons/ti';
import LineText from '../../components/lineText/LineText';
import { api, get, post } from '../../logic/api/api';

export default function AddQuestion({handleSubmit,submitText}){
    const [question, setQuestion] = useState('');
	const [isAText, setOnText] = useState(true);
	const [responses, setResponses] = useState([]);
	const [currentResponse, setCurrentResponse] = useState('');
    
    return  <div className='addSurvey'>
    <div className="input-container ic1">
            <input
                id="question"
                className={'question-input'}
                type="text"
                value={question}
                onChange={(e) =>  setQuestion(e.target.value)}
                onKeyDown={(e) => {
                    if (e.key === 'Enter') {
                        e.preventDefault();
                    }
                }}
                placeholder=" "
            />
            <div className="cut-question"></div>
            <label htmlFor="question" className="placeholder">
                Question :
            </label>
        </div>

        <p>Réponse attendu :</p>
        <div className="choice-switch">
            <div className="choice-switch-item">
                <input
                    type="radio"
                    id="text"
                    name="choice"
                    value="text"
                    onChange={(e) => setOnText(true)}
                />
                <label htmlFor="text">Texte</label>
            </div>
            <div className="choice-switch-item">
                <input
                    type="radio"
                    id="responses"
                    name="choice"
                    value="responses"
                    onChange={(e) => {
                        setOnText(false);
                    }}
                />
                <label htmlFor="responses">Liste de réponse</label>
            </div>
        </div>
        {!isAText && (
            <>
                {responses.map((response) => {
                    return (
                        <div key={response.placeholder} className="response">
                            {response.placeholder}
                            <div
                                onClick={(e) =>
                                    setResponses((prev) =>
                                        prev.filter(
                                            (r) => r !== response
                                        )
                                    )
                                }
                            >
                                <TiTimes />
                            </div>
                        </div>
                    );
                })}

                <div className="input-container ic1">
                    <input
                        id="response"
                        className={'question-input'}
                        type="text"
                        value={currentResponse}
                        onChange={(e) =>
                            setCurrentResponse(e.target.value)
                        }
                        placeholder=" "
                        onKeyDown={(e) => {
                            if (e.key === 'Enter') {
                                e.preventDefault()
                                setResponses((prev) => {
                                    const newResponse ={
                                        placeholder:currentResponse
                                    } 
                                    console.log(newResponse);
                                    console.log(prev.includes(newResponse));
                                    if (
                                        prev.includes(
                                            newResponse
                                        )
                                    ){
                                        return prev;
                                    } 
                                    let next = [...prev,newResponse]
                                    console.log(next); 
                                    return next
                                });
                                setCurrentResponse('');
                            }
                        }}
                    />
                    <div className="cut-question"></div>
                    <label
                        htmlFor="response"
                        className="placeholder"
                    >
                        Réponse :
                    </label>
                </div>
            </>
        )}
        <br />
        <SelectButton2
            onClick={(e) => {
                handleSubmit(question,isAText,responses);
            }}
        >
           {submitText} 
        </SelectButton2>
    </div>
} 
