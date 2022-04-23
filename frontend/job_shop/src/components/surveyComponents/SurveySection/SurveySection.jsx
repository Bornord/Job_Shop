import React, { useState, useEffect } from 'react';
import Question from '../question/Question';
import "./SurveySection.scss";


const SurveySection = ()=>{

    const handleNext = ()=>{
        console.log("next");
    }

    return <>
        <Question question={"bla bla bla bla ?"} answer={""} callback={handleNext}/>
    </>
}

export default SurveySection;