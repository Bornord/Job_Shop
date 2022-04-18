	
import React from 'react';
import './SelectButton2.scss';

const SelectButton2 = ({className,onClick,children,type}) => {
    return 	<button type = {type} class={`${className} button button--bestia`} onClick={onClick}>
        <div class="button__bg"></div><span>{children}</span>
    </button>

    
}
export default SelectButton2;        