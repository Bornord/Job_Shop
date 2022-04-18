import React from 'react';
import './SelectButton.scss';

const SelectButton = ({className,onClick,children,type}) => {
    return (<button type = {type} className={`${className} button button--calypso`} onClick={onClick}><span>{children}</span></button>)
    
}
export default SelectButton;