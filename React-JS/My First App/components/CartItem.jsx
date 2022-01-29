import React from "react";

export default function CartItem({itemList, itemID, itemQuantity, deleteItem}){
    let iconUrl = itemList.find(i => i.id === itemID).iconUrl;
    let itemName = itemList.find(i => i.id === itemID).name;
    return (
        <div className="cart-item">
            <div className="cart-content">
            <div className="item-content">
                <img src={iconUrl} alt="item_thumbnail" />
                <div >
                    <p><b>{itemName}</b></p>
                    <p><b>Quantity : &nbsp;</b>{itemQuantity}</p>
                </div>
            </div>
                </div>
                
                <button type="button" className="btn btn-danger px-4 mx-3" onClick={() => {deleteItem(itemID)}}>Delete</button>
            
            
        </div>
    );
}