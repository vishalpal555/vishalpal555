import React from 'react'
import CartItem from './CartItem';

export default function Cart(props) {
    let cart = []
    let itemsPresent = false;
    if (props.cartItems){
        if (props.cartItems.length > 0){
            itemsPresent = true;        
        }
    }
    for (let index = 0; index < props.itemList.length; index++) {
        var count_item = props.cartItems.filter(i => i === props.itemList[index].id).length;
        if (count_item > 0){
            cart.push({id:props.itemList[index].id, quantity:count_item});
        }       
    }
    function deleteItem(itemID){
        props.addToCart(cartItems => cartItems.filter(x => x !== itemID));
                
    }
    return(
        <>
            <h3 className="text-center my-4">CART</h3>
            <div className='text-center'>
                <div className='cart'>
                    {itemsPresent? 
                        <>
                            {cart.map((item) => {
                                return (<CartItem key={item.id} itemList={props.itemList} itemID={item.id} itemQuantity={item.quantity} deleteItem={deleteItem} />);
                            })}
                            <b>{props.totalCost}</b>Rs<button type="button" className='px-4 my-2 mx-2 btn btn-success'>Buy All</button>
                        </>
                        :
                        <>
                            <div className='text-danger'><h2><b>Cart Empty</b></h2></div>
                        </>
                    }                
                </div>

            </div>
            
        </>
    )
}
