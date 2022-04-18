import "./Navbar.scss";
import { Link, useLocation, useParams } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from "react-redux";
import {goto} from "../../logic/features/navigation";
import LineText from "../lineText/LineText";


const Navbar = () => {
  const user = useSelector((state) => state.user.value)

  const links = useSelector((state) => state.navigation.value)
  const dispatch = useDispatch()

  return (
    <nav className="navbar">
        <div className="title">
          <h1>JobShop</h1>
        </div>
        <ul className="navigation-list">
          {links.filter(
            link=>link.visible
          ).map((link) => {
            let active = link.active ? "active" : ""
            return (
              <li>
                <Link 
                  key={link.id} 
                  className={`separator ${active}`} 
                  to={link.to}
                  onClick={
                    ()=>{
                      dispatch(goto({id:link.id,arg:user.name}))
                    }
                }>
                  <LineText>{link.title}</LineText>
                </Link>
              </li>
          )
          })}
        </ul>
    </nav>
  );
};

export default Navbar;
