import React, {useEffect, useState} from 'react';
import StarRatingComponent from 'react-star-rating-component';

export default function Item({item, addToCart, totalCost}) {
    function addedToCart(itemID){
        addToCart(cartItems => [...cartItems, itemID]);
    }
    return(
        <div className="item">
            <div className="item-content">
                <img src={item.iconUrl} alt="item_thumbnail" />
                <div >
                    <p><b>{item.name}</b></p>
                    <p>{item.price} Rs</p>
                </div>            
                <div><StarRatingComponent name='' value={item.rating} starCount={5} editing={false}/></div>
            </div>
            <div className="item-add">
                <div>
                    <button type="button" className="btn-primary btn my-1">Buy</button>
                </div>
                <div>
                    <button type="button" className="btn btn-warning my-1" onClick={() => {addedToCart(item.id)}}>Add to Cart</button>
                </div>
            </div>
        </div>
    )
}
