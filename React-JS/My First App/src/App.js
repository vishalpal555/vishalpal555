import './App.css';
import Header from './components/Header';
import Items from './components/Items';
import React, {useEffect, useState} from 'react';
import Footer from './components/Footer';
import Cart from './components/Cart';
import Register from './components/Register'
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import Login from './components/Login';
import axios from 'axios';
export const API_BASE_URL = "http://localhost:8080";
function App() {
 
  const [itemList, setItemList] = useState([]);
  const [status, setStatus] = useState();
  useEffect(() => {
    console.log("getting from server api ::::");
    axios.get(API_BASE_URL+"/items").then(res => {
      setItemList(res.data)
  }).catch(err => console.log(err));
  }, [])
  let totalCost = 0;
  const [cartItems, addToCart] = useState([]);

  

  cartItems.map((itemId) => {
    totalCost += itemList.find(i => i.id === itemId).price;
  });
  

  return (
    <>
    <Router>
    <Header cartValue={cartItems.length} status={status} setStatus={setStatus} />
    <Switch>
          <Route path="/cart" render={()=>{
            return (
              <>
                <Cart cartItems={cartItems} itemList={itemList} addToCart={addToCart} totalCost={totalCost} />
              </>
            );
          }}>
            
          </Route>
          <Route exact path="/" render={()=>{
            return (
              <Items items={itemList} addToCart={addToCart} totalCost={totalCost}/>
            );
          }}>
          </Route>
          <Route exact path="/register" render={()=>{
            return (
              <Register setStatus={setStatus} />
            );
          }}>
          </Route>
          <Route exact path="/login" render={()=>{
            return (
              <Login />
            );
          }}>  
          </Route>
    </Switch>
    
    <Footer />
    </Router>
    </>
    
  );
}

export default App;
