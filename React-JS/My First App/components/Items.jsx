import React from "react";
import Item from "./Item";
import PropTypes from 'prop-types';

export default function Items(props) {
    return(
        <>
            <h3 className="text-center my-4">ITEMS</h3>
            <div className="container">
                {props.items.map((item)=>{
                    return <Item item={item} key={item.id} addToCart={props.addToCart} totalCost={props.totalCost}/>
                })}
            </div>
        </>
    )
}

Items.propTypes = {
    itemID : PropTypes.string,
    itemName : PropTypes.string,
    itemThumbnail : PropTypes.string,
    itemRating : PropTypes.number,
    itemPrice : PropTypes.number,
}