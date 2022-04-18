import React from 'react';
import './FancyButton.scss';

const FancyButton = ({className,onClick,children,type}) => {
    return <button type = {type} class={`${className} button button--janus`} onClick={onClick}><span>{children}</span></button>
}
export default FancyButton;